package com.spectrum.controller;

import com.spectrum.common.response.SBoardRes;
import com.spectrum.entity.QUser;
import com.spectrum.service.SBoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public ResponseEntity<List<SBoardRes>> searchAll() {
        List<SBoardRes> sboardList = sBoardService.getSBoardsByUser(me);
        return ResponseEntity.status(200).body(sboardList);
    }
}
