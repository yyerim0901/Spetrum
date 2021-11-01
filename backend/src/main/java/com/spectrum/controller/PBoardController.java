package com.spectrum.controller;

import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.common.request.PBoardPostReq;
import com.spectrum.common.request.PBoardUpdateReq;
import com.spectrum.common.response.PBoardResponse;
import com.spectrum.entity.PBoard;
import com.spectrum.service.PBoardService;
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


@Api(value = "펫시터 API", tags = {"PetSitter"})
@RestController
@RequestMapping("/api/petsitter")
public class PBoardController {

    @Autowired
    private PBoardService petSitterService;

    @Autowired
    private JWTUtil jwtUtil;

    @ApiOperation(
            value = "펫시터 게시글 작성",
            notes = "**제목, 내용, 위도, 경도** 입력 ~~작성자는 토큰 구현 이후 수정 예정~~ "
    )
    @PostMapping
    private ResponseEntity<String> postPetsitter(
            @ApiParam(value = "게시글 작성", required = true) PBoardPostReq petSitterPostRequest,
            @RequestPart(value = "image", required = false) MultipartFile petSitterImage,
            HttpServletRequest request){

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        petSitterService.postPetSitter(petSitterPostRequest,petSitterImage, token);
        return new ResponseEntity<>("post petsitter success", HttpStatus.OK);
    }

    @ApiOperation(
            value = "펫시터 게시글 수정",
            notes = "**글 id, 제목, 내용, 위도, 경도, 이미지** 입력"
    )
    @PutMapping
    private ResponseEntity<String> updatePetsitter(
            @ApiParam(value = "게시글 수정", required = true) PBoardUpdateReq petSitterUpdateReq,
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
    private PBoardResponse myPetsitterList(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        List<PBoard> list = petSitterService.myPetsitterList(token);

        if(list == null || list.isEmpty()){
            return new PBoardResponse("나의 리스트가 존재하지 않습니다.",null);
        }else return new PBoardResponse("리스트 출력 완료",list);
    }

    @ApiOperation(
            value = "전제 petsitter 글 출력",
            notes = "사용자의 위도 경도에 따라 다르게 정렬...하는거 나중에 추가할게욤..우선은 그냥 다 출력.."
    )
    @GetMapping("/list")
    private PBoardResponse allPetsitterList(){
        List<PBoard> allList = petSitterService.allPetsitterList();
        if(allList == null || allList.isEmpty()){
            return new PBoardResponse("등록된 글이 존재하지 않습니다.",null);
        }else return new PBoardResponse("리스트 출력 완료",allList);
    }

    @ApiOperation(
            value = "펫시터 상세 페이지 출력",
            notes = "url에 **글 번호(id)**를 넣고 출력"
    )
    @GetMapping("/detail/{petsitterId}")
    private PBoardResponse detailPetsitterPage(
            @PathVariable Long petsitterId
    ){
        PBoard petSitter = petSitterService.detailOfPetsitter(petsitterId);

        if(petSitter == null){
            return new PBoardResponse("존재하지 않는 글입니다.",null);
        }else return new PBoardResponse("petsitter 글 출력 완료",petSitter);
    }
}
