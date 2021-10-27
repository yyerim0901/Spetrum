package com.spectrum.controller;

import com.spectrum.entity.Dogging;
import com.spectrum.service.DoggingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dogging")
public class DoggingController {

    @Autowired
    private DoggingService doggingService;

    @ApiOperation(
            value = "로그인 한 사용자 도깅 리스트",
            notes = "로그인한 유저의 id는 임시로 대체해둔 것. jwt 구현 후에 방식 변경 예정")
    @GetMapping("/{userid}")
    public ResponseEntity<List<Dogging>> MyDoggingList(@PathVariable Long id){
        List<Dogging> list;
        list = doggingService.MyDoggingList(id);
        if(list == null || list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(list,HttpStatus.OK);
    }


}
