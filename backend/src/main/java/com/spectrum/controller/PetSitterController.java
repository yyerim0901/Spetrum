package com.spectrum.controller;

import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.common.request.PetSitterPostReq;
import com.spectrum.common.request.PetSitterUpdateReq;
import com.spectrum.common.response.PetSitterResponse;
import com.spectrum.entity.PetSitter;
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
import java.util.List;
import java.util.Optional;


@Api(value = "펫시터 API", tags = {"PetSitter"})
@RestController
@RequestMapping("/api/petsitter")
public class PetSitterController {

    @Autowired
    private PetSitterService petSitterService;

    @Autowired
    private JWTUtil jwtUtil;

    @ApiOperation(
            value = "펫시터 게시글 작성",
            notes = "**제목, 내용, 위도, 경도** 입력 ~~작성자는 토큰 구현 이후 수정 예정~~ "
    )
    @PostMapping
    private ResponseEntity<String> postPetsitter(
            @ApiParam(value = "게시글 작성", required = true) PetSitterPostReq petSitterPostRequest,
            @RequestPart(value = "image", required = false) MultipartFile petSitterImage,
            HttpServletRequest request){

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
//        String userid = jwtUtil.getUsername(token);

        petSitterService.postPetSitter(petSitterPostRequest,petSitterImage, token);
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

    @ApiOperation(
            value = "게시글 삭제",
            notes = "**게시글 고유 번호**를 입력하여 삭제"
    )
    @DeleteMapping
    private ResponseEntity<String> deletePetsitter(
            @RequestBody Long petSitterId
            ){
        petSitterService.deletePetSitter(petSitterId);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }

    @ApiOperation(
            value = "내 펫시터 리스트",
            notes = "**토큰**을 이용하여 리스트 출력"
    )
    @GetMapping("/mylist")
    private PetSitterResponse myPetsitterList(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        List<PetSitter> list = petSitterService.myPetsitterList(token);

        if(list == null || list.isEmpty()){
            return new PetSitterResponse("나의 리스트가 존재하지 않습니다.",null);
        }else return new PetSitterResponse("리스트 출력 완료",list);
    }

}
