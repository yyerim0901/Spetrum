package com.spectrum.controller;

import com.spectrum.common.request.SBoardRegisterReq;
import com.spectrum.common.response.SBoardRes;
import com.spectrum.entity.QUser;
import com.spectrum.service.SBoardService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.spectrum.entity.QUser.user;

@Api(value = "SNS API", tags = {"SBoard"})
@RestController
@RequestMapping("/api/sns")
public class SBoardController {


    @Autowired
    SBoardService sBoardService;

    QUser me;

    @GetMapping("/")
    @ApiOperation(value = "나의 sns 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })

    public ResponseEntity<List<SBoardRes>> searchAll(@ApiParam(value="나중에 지울것", required = true) Quser user) {
        List<SBoardRes> sboardList = sBoardService.getSBoardsByUser(user);
        return ResponseEntity.status(200).body(sboardList);
    }

    @PostMapping(value = "/")
    @ApiOperation(value = "게시글 작성")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "작성 오류"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<SBoardRes> createSBoard(
            @ApiParam(value="나중에 지울것", required = true) Quser user,
            @ApiParam(value="sns 정보", required = true) SBoardRegisterReq sboardinfo,
            @RequestPart(value = "사진", required = false) List<MultipartFile> sboardfiles) throws IOException {
        sBoardService.createSBoard(user, sboardinfo, sboardfiles);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
