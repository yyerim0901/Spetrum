package com.spectrum.controller;

import com.spectrum.common.request.PetSitterPostReq;
import com.spectrum.common.request.PetSitterUpdateReq;
import com.spectrum.service.PetSitterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Api(value = "펫시터 API", tags = {"PetSitter"})
@RestController
@RequestMapping("/api/petsitter")
public class PetSitterController {

    @Autowired
    private PetSitterService petSitterService;
    
    @ApiOperation(
            value = "펫시터 게시글 작성",
            notes = "**제목, 내용, 위도, 경도** 입력 ~~작성자는 토큰 구현 이후 수정 예정~~ "
    )
    @PostMapping
    private ResponseEntity<String> postPetsitter(
            @ApiParam(value = "게시글 작성", required = true) PetSitterPostReq petSitterPostRequest,
            @RequestPart(value = "image", required = false) MultipartFile petSitterImage){

//        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
//        String userid = jwtUtil.getUsername(token);

        petSitterService.postPetSitter(petSitterPostRequest,petSitterImage);
        return new ResponseEntity<>("post petsitter success", HttpStatus.OK);
    }

    @ApiOperation(
            value = "펫시터 게시글 수정",
            notes = "**글 id, 제목, 내용, 위도, 경도, 이미지** 입력"
    )
    @PutMapping
    private ResponseEntity<String> updatePetsitter(
            @ApiParam(value = "게시글 수정", required = true) PetSitterUpdateReq petSitterUpdateReq,
            @RequestPart(value = "image", required = false) MultipartFile newPicture
            ){
        petSitterService.updatePetSitter(petSitterUpdateReq, newPicture);
        return new ResponseEntity<>("update petsitter success", HttpStatus.OK);
    }

}