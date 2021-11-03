package com.spectrum.controller;

import com.spectrum.entity.Dogging;
import com.spectrum.entity.Path;
import com.spectrum.service.DoggingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Api(value = "도깅 API", tags = {"Dogging"})
@RestController
@RequestMapping("/api/dogging")
public class DoggingController {

    @Autowired
    private DoggingService doggingService;

    @ApiOperation(
            value = "로그인 한 사용자 도깅 리스트",
            notes = "**로그인한 유저의 id**로 도깅 리스트 조회.")
    @GetMapping
    public ResponseEntity<List<Dogging>> MyDoggingList(HttpServletRequest request){

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        List<Dogging> list = doggingService.MyDoggingList(token);
        if(list == null || list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(list,HttpStatus.OK);
    }

//    @PostMapping("/test")
//    public ResponseEntity<?> testOfPath(@RequestParam(value = "paths",required = false) Float[] lats,
//                                                 @RequestParam(value = "paths",required = false) Float[] lngs,
//                                                 HttpServletRequest request){
//        Dogging dogging = new Dogging();
//        dogging.setTime(7000);
//        dogging.setLocation("대전광역시");
//        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
//        doggingService.PostDogging(dogging, token, lats, lngs);
//        return new ResponseEntity<>("확인해봐", HttpStatus.OK);
//    }

    @ApiOperation(
            value = "도깅 기록하기",
            notes = "도깅 완료 후 **거리,시간,날짜,지역,사진,사용자 정보**를 가지고 DB에 저장"
    )
    @PostMapping
    public ResponseEntity<String> postDogging(@RequestBody Dogging dogging,
                                              HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        doggingService.PostDogging(dogging, token);
        return new ResponseEntity<>("post dogging success",HttpStatus.OK);
    }

    @ApiOperation(
            value = "도깅 기록 삭제",
            notes = "**doggingId**를 이용하여 기록 삭제"
    )
    @DeleteMapping
    public ResponseEntity<String> deleteDogging(@RequestParam Long doggingId){
        doggingService.DeleteDogging(doggingId);
        return new ResponseEntity<>("delete dogging success",HttpStatus.OK);
    }

}
