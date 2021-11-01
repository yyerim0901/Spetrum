package com.spectrum.controller;

import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.common.request.SBoardRegisterReq;
import com.spectrum.common.response.SBoardRes;
import com.spectrum.entity.SBoard;
import com.spectrum.entity.User;
import com.spectrum.service.SBoardService;
import com.spectrum.service.UserService;
import io.swagger.annotations.*;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@Api(value = "SNS API", tags = {"SBoard"})
@RestController
@RequestMapping("/api/sns")
public class SBoardController {


    @Autowired
    SBoardService sBoardService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    UserService userService;

    @GetMapping("/")
    @ApiOperation(value = "나의 sns 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })

    public ResponseEntity<List<SBoardRes>> searchAll(@ApiIgnore HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String userid = jwtUtil.getUsername(token);
        User user = userService.findUserByUserId(userid);
        List<SBoardRes> sboardList = sBoardService.getSBoardsByUser(user);
        return ResponseEntity.status(200).body(sboardList);
    }

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation(value = "게시글 작성")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "작성 오류"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<SBoardRes> createSBoard(
//            @ApiIgnore HttpServletRequest request,
            @RequestPart(value = "사진", required = false) List<MultipartFile> files,
            @ApiParam(value="sns 정보", required = true) SBoardRegisterReq sboardinfo) throws IOException {
//        String token1 = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = sboardinfo.getToken();
        String userid = jwtUtil.getUsername(token);
        User user = userService.findUserByUserId(userid);
        System.out.println(user.getUserId());
        System.out.println(sboardinfo.getContent());
        System.out.println(files+"??????????");
        SBoard sboard = sBoardService.createSBoard(user, sboardinfo, files);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
