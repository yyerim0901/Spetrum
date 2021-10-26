package com.spectrum.controller;

import com.spectrum.common.request.UserRegisterPostReq;
import com.spectrum.common.response.UserResponse;
import com.spectrum.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/users")
@Api(value = "유저 API", tags = {"User"})
public class UserController {

    @Autowired
    UserService userService;

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
}
