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
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;


@Api(value = "펫시터 API", tags = {"PBoard"})
@RestController
@RequestMapping("/api/pboard")
public class PBoardController {

    @Autowired
    private PBoardService pBoardService;

    @Autowired
    private JWTUtil jwtUtil;

    @ApiOperation(
            value = "거리순 글 정렬 TEST API",
            notes = "프론트까지 다 완성되면 지울 예정"
    )
    @GetMapping("/test")
    private ResponseEntity<?> testOfList() throws Exception{
        List<PBoard> list = pBoardService.sortOfDistance();
        return new ResponseEntity<List<PBoard>>(list,HttpStatus.OK);
    }

    @ApiOperation(
            value = "펫시터 게시글 작성",
            notes = "**제목, 내용, 위도, 경도** 입력 ~~작성자는 토큰 구현 이후 수정 예정~~ "
    )
    @PostMapping
    private ResponseEntity<String> postPetsitter(
            @ApiParam(value = "게시글 작성", required = true) PBoardPostReq pBoardPostReq,
            @RequestPart(value = "image", required = false) MultipartFile petSitterImage,
            HttpServletRequest request) throws Exception {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        pBoardService.postPetSitter(pBoardPostReq,petSitterImage, token);
        return new ResponseEntity<>("post petsitter success", HttpStatus.OK);
    }

    @ApiOperation(
            value = "펫시터 게시글 수정",
            notes = "**글 id, 제목, 내용, 위도, 경도, 이미지** 입력"
    )
    @PutMapping
    private ResponseEntity<String> updatePetsitter(
            HttpServletRequest request,
            @ApiParam(value = "게시글 수정", required = true) PBoardUpdateReq pBoardUpdateReq,
            @RequestPart(value = "image", required = false) MultipartFile newPicture
            ) throws Exception {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!pBoardService.checkWriterOfBoard(pBoardUpdateReq,token)) {
            return new ResponseEntity<>("글 수정 권한 없음", HttpStatus.UNAUTHORIZED);
        }
        pBoardService.updatePetSitter(pBoardUpdateReq, newPicture, token);
        return new ResponseEntity<>("펫 시터 글 수정 완료",HttpStatus.OK);
    }

    @ApiOperation(
            value = "게시글 삭제",
            notes = "**게시글 고유 번호**를 입력하여 삭제"
    )
    @DeleteMapping
    private ResponseEntity<String> deletePetsitter(
            @ApiParam(value = "게시글 id", required = true) Long petSitterId
            ) throws IOException {
        pBoardService.deletePetSitter(petSitterId);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }

    @ApiOperation(
            value = "내 펫시터 리스트",
            notes = "**토큰**을 이용하여 리스트 출력"
    )
    @GetMapping("/mylist")
    private PBoardResponse myPetsitterList(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        List<PBoard> list = pBoardService.myPetsitterList(token);

        if(list == null || list.isEmpty()){
            return new PBoardResponse("나의 리스트가 존재하지 않습니다.",null,HttpStatus.NO_CONTENT);
        }else return new PBoardResponse("리스트 출력 완료",list,HttpStatus.OK);
    }
    
    //mongoDB의 mindistance, maxdistance 쓰면 일정 거리에 따라 글 sort 가능
    //mongoDB가 위치정보를 저장하기에도 더 쉬움

    //mysql -> ST_DISTANCE_SPHERE()

    //페이징=> pageable 사용
    @ApiOperation(
            value = "전제 petsitter 글 출력",
            notes = "**사용자의 위도, 경도, page**에 따라 글 정렬"
    )
    @GetMapping("/list")
    private PBoardResponse allPetsitterList(@RequestParam float latitude, @RequestParam float longitude,
                                            @RequestParam int pagenum){
        Object allList = pBoardService.allPetsitterList(longitude, latitude, pagenum);
        if(allList == null){
            return new PBoardResponse("등록된 글이 존재하지 않습니다.",null,HttpStatus.NO_CONTENT);
        }else return new PBoardResponse("리스트 출력 완료",allList, HttpStatus.OK);
    }

    @ApiOperation(
            value = "펫시터 상세 페이지 출력",
            notes = "url에 **글 번호(id)**를 넣고 출력"
    )
    @GetMapping("/detail/{pboardId}")
    private PBoardResponse detailPetsitterPage(
            @PathVariable Long pboardId
    ){
        PBoard pBoard = pBoardService.detailOfPetsitter(pboardId);

        if(pBoard == null){
            return new PBoardResponse("존재하지 않는 글입니다.",null,HttpStatus.NO_CONTENT);
        }else return new PBoardResponse("petsitter 글 출력 완료",pBoard,HttpStatus.OK);
    }

    @ApiOperation(
            value = "펫시터 거래 상태 변경 API",
            notes = "**pboardId**를 통해 상태변경, 1이면 완료, 0이면 찾는 중"
    )
    @PutMapping("/status")
    private PBoardResponse changeStatus(@RequestParam Long pboardId){
        int status = pBoardService.completedPetsitter(pboardId);
        return new PBoardResponse("상태 변경 완료",status,HttpStatus.OK);
    }


}
