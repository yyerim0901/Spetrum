package com.spectrum.controller;

import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.common.request.DoggingPostReq;
import com.spectrum.common.response.DoggingDetailResponse;
import com.spectrum.entity.Dogging;
import com.spectrum.entity.User;
import com.spectrum.repository.UserRepository;
import com.spectrum.service.CustomImage;
import com.spectrum.service.DoggingService;
import io.jenetics.jpx.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Api(value = "도깅 API", tags = {"Dogging"})
@RestController
@RequestMapping("/api/dogging")
public class DoggingController {

    @Autowired
    private DoggingService doggingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @ApiOperation(
            value = "로그인 한 사용자 도깅 리스트",
            notes = "**로그인한 유저의 id**로 도깅 리스트 조회.")
    @GetMapping
    public ResponseEntity<List<Dogging>> MyDoggingList(HttpServletRequest request) throws IOException{

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        doggingService.saveUserInfo(token);
        List<Dogging> list = doggingService.MyDoggingList();
        if(list == null || list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(list,HttpStatus.OK);
    }
    
    @ApiOperation(
            value = "도깅 1개 상세 페이지",
            notes = "**도깅 ID**를 이용해서 경로와 함께 상세 정보 출력"
    )
    @GetMapping("/detail")
    public ResponseEntity<?> detailOfDogging(@RequestParam Long doggingId,
                                                                 HttpServletRequest request) throws IOException{
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        doggingService.saveUserInfo(token);

        DoggingDetailResponse res = new DoggingDetailResponse();
        res = doggingService.DetailOfDogging(doggingId);

        if(res == null){
            return new ResponseEntity<String>("도깅 자료 불러오는 도중 오류 발생",HttpStatus.NOT_FOUND);
        }else return new ResponseEntity<DoggingDetailResponse>(res,HttpStatus.OK);

    }
    
    @ApiOperation(
            value = "GPX파일을 위한 임시 API",
            notes = "나아아아아아아ㅏ아아중에 지울거니까 무시해도 돼~!~!"
    )
    @PostMapping("/test")
    public ResponseEntity<?> test(HttpServletRequest request, Float[] lat, Float[] lng) throws IOException{

        String filePath = "src/main/resources/dogging/";

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String userid = jwtUtil.getUsername(token);
        Optional<User> userOptional = userRepository.findByUserId(userid);
        User user = userOptional.get();
        Long id = user.getId();
        Long doggingId = 1L;

        String fileName = id+"_"+doggingId;

        List<WayPoint> wayPoints = new ArrayList<>();
        for(int i=0; i<lat.length; i++){
            wayPoints.add(WayPoint.of(lat[i], lng[i]));
        }
        final GPX gpx1 = GPX.builder()
                .addTrack(track -> track
                        .addSegment(segment -> segment
                                .points(wayPoints)))
                .build();


        GPX.write(gpx1, filePath+fileName+".gpx");
        //GPX작성

        Object[] objects = GPX.read(filePath+fileName+".gpx")
                .tracks()
                .flatMap(Track::segments)
                .flatMap(TrackSegment::points).toArray();

        return new ResponseEntity<Object[]>(objects,HttpStatus.OK);
    }

    @ApiOperation(
            value = "도깅 완료 후 사진 커스텀",
            notes = "도깅 완료 후 버튼을 통해 커스텀을 실행시키면 **이미지 파일, doggingId**로 이미지 커스텀"
    )
    @PostMapping("/image")
    public ResponseEntity<?> customImageTest(@ApiParam(value = "imageTest", required = true) MultipartFile multipartFile,
                                             @RequestParam Long doggingId) throws IOException{

        doggingService.customImage(multipartFile,doggingId);
        return new ResponseEntity<String>("success to make Image",HttpStatus.OK);
    }


    @ApiOperation(
            value = "도깅 기록하기",
            notes = "**거리,시간,지역,Float[]위경도 배열 따로 두 개**를 받아서 저장 / 사진 추후 수정"
    )
    @PostMapping
    public ResponseEntity<String> postDogging(@ApiParam(value = "dogging post", required = true) DoggingPostReq doggingPostReq,
                                              HttpServletRequest request) throws IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        doggingService.saveUserInfo(token);

        doggingService.PostDogging(doggingPostReq);
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

    @ApiOperation(
            value = "도깅 사진 다운로드",
            notes = "도깅 사진 커스텀 후 다운로드"
    )
    @GetMapping("/download")
    public ResponseEntity<?> downloadCustomImage(@RequestParam Long doggingId){
        doggingService.downloadCustomImage(doggingId);
        return new ResponseEntity<String>("다운로드 확인하기",HttpStatus.OK);
    }


}
