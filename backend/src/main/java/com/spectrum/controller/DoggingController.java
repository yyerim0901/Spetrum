package com.spectrum.controller;

import com.spectrum.entity.Dogging;
import com.spectrum.service.DoggingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "도깅 API", tags = {"Dogging"})
@RestController
@RequestMapping("/api/dogging")
public class DoggingController {

    @Autowired
    private DoggingService doggingService;

    @ApiOperation(
            value = "로그인 한 사용자 도깅 리스트",
            notes = "**로그인한 유저의 id**로 도깅 리스트 조회. jwt 구현 후에 방식 변경 예정")
    @GetMapping("/{userid}")
    public ResponseEntity<List<Dogging>> MyDoggingList(@PathVariable Long id){
        List<Dogging> list;
        list = doggingService.MyDoggingList(id);
        if(list == null || list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @ApiOperation(
            value = "도깅 기록하기",
            notes = "도깅 완료 후 **거리,시간,날짜,지역,사진,사용자 정보**를 가지고 DB에 저장"
    )
    @PostMapping
    public ResponseEntity<String> postDogging(@RequestBody Dogging dogging){
        doggingService.PostDogging(dogging);
        return new ResponseEntity<>("post dogging success",HttpStatus.OK);
    }

    @ApiOperation(
            value = "도깅 기록 삭제",
            notes = "**도깅 기록의 id**를 이용하여 기록 삭제"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDogging(@PathVariable Long id){
        doggingService.DeleteDogging(id);
        return new ResponseEntity<>("delete dogging success",HttpStatus.OK);
    }

}
