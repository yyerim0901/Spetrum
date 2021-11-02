package com.spectrum.controller;

import com.spectrum.common.jwt.CookieUtil;
import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.common.jwt.RedisUtil;
import com.spectrum.common.request.UserLoginReq;
import com.spectrum.common.request.UserRegisterPostReq;
import com.spectrum.common.request.UserUpdateReq;
import com.spectrum.common.response.UserResponse;
import com.spectrum.entity.User;
import com.spectrum.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/users")
@Api(value = "유저 API", tags = {"User"})
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    RedisUtil redisUtil;


    @PostMapping("/regist")
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<UserResponse> register(
            @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo,
            @RequestPart(value = "image", required = false) MultipartFile thumbnail)
    {
        userService.createUser(registerInfo,thumbnail);
        return ResponseEntity.ok(UserResponse.of(200, "Success"));
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 아이디"),
            @ApiResponse(code = 403, message = "계정정보 불일치"),
    })
    public ResponseEntity<UserResponse> login(@ApiParam(value="로그인", required = true) UserLoginReq loginInfo){
       String userId = loginInfo.getUserId();
       String password = loginInfo.getPassword();
       User userinfo = userService.findUserByUserId(userId);

       if(userinfo == null)
       {
           return ResponseEntity.ok(UserResponse.of(404, "존재하지 않는 아이디"));
       }
        if(passwordEncoder.matches(password, userinfo.getPassword())) {
//            JwtTokenUtil.getToken(userEmail);
            String token = jwtUtil.generateToken(userinfo);
            redisUtil.deleteData(token);
//            System.out.println(token);
//            System.out.println(token.getClass());
//            System.out.println(jwtUtil.getUsername(token)); //토큰 > userID
            return ResponseEntity.ok(UserResponse.of(200, "Success",token));
        }
        return ResponseEntity.ok(UserResponse.of(403, "아이디/비밀번호가 일치하지 않습니다."));
    }

    @PostMapping("/logout")
    @ApiOperation(value = "로그아웃", notes = "로그아웃한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<UserResponse> logout(@ApiIgnore HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        UUID value = UUID.randomUUID();
        redisUtil.setData(token,value.toString());

        return ResponseEntity.ok(UserResponse.of(200, "로그아웃 성공"));
    }

    @GetMapping("/search/{userid}")
    @ApiOperation(value = "회원정보 검색", notes = "회원정보를 조회한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<User> search(@PathVariable("userid") String userid){
        User user = userService.search(userid);
        if(user == null)
        {
            return null;
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/checkID/{userid}")
    @ApiOperation(value = "아이디 중복체크", notes = "회원 아이디 중복검사")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<UserResponse> checkID(@PathVariable("userid") String userid){
        User user = userService.search(userid);
        if(user == null)
        {
            return ResponseEntity.ok(UserResponse.of(200, "true"));
        }
        return ResponseEntity.ok(UserResponse.of(404, "false"));
    }

    @GetMapping("/checkNICK/{usernickname}")
    @ApiOperation(value = "닉네임 중복체크", notes = "회원 닉네임 중복검사")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<UserResponse> checkNICK(@PathVariable("usernickname") String usernickname){
        User user = userService.searchNick(usernickname);
        if(user == null)
        {
            return ResponseEntity.ok(UserResponse.of(200, "true"));
        }
        return ResponseEntity.ok(UserResponse.of(404, "false"));
    }

    @PutMapping("/{userid}")
    @ApiOperation(value = "회원정보 수정", notes = "회원 정보 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<UserResponse> userUpdate(@ApiParam(value="수정 정보", required = true) UserUpdateReq updateInfo,
                                                   @PathVariable("userid") String userid) throws IOException {
        userService.updateUser(updateInfo,userid);

        return ResponseEntity.ok(UserResponse.of(200, "회원정보수정 완료"));
    }
}
