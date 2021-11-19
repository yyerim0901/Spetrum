package com.spectrum.controller;

import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.common.request.SBoardPutReq;
import com.spectrum.common.request.SBoardRegisterReq;
import com.spectrum.common.response.MessageResponse;
import com.spectrum.common.response.SBoardListRes;
import com.spectrum.common.response.SBoardRes;
import com.spectrum.entity.SBoard;
import com.spectrum.entity.SBoardFile;
import com.spectrum.entity.User;
import com.spectrum.repository.SBoardFileRepository;
import com.spectrum.service.SBoardService;
import com.spectrum.service.UserService;
import io.swagger.annotations.*;
//import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Api(value = "SNS API", tags = {"SBoard"})
@RestController
@RequestMapping("/api/sns")
public class SBoardController {


    @Autowired
    SBoardService sBoardService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    UserService userService;


    @GetMapping("/")
    @ApiOperation(value = "나의 sns 전체 조회 _ paging")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })

    public ResponseEntity<SBoardListRes> searchAll(
            @ApiIgnore HttpServletRequest request) {
        int pagenum = Integer.parseInt(request.getParameter("page"));
        Pageable pageable = PageRequest.of(pagenum-1, 10, Sort.by(Sort.Direction.DESC, "created"));
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String userid = jwtUtil.getUsername(token);
        System.out.println(userid);
        User user = userService.findUserByUserId(userid);
        List<SBoardRes> sboardList = sBoardService.getSBoardsByUser(user, pageable);
        System.out.println("★★★★★");
        System.out.println(sboardList.get(0).getContent());

        return ResponseEntity.status(200).body(SBoardListRes.of(200, "성공", sboardList));
    }

    @GetMapping("/users/{userid}")
    @ApiOperation(value = "나의 sns 전체 조회 _ paging",
            notes = "A: 팔로우상태, B: 팔로우 아닌상태")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<SBoardListRes> searchAllOther(
            @ApiIgnore HttpServletRequest request,
            @PathVariable("userid") String userid) {
        int pagenum = Integer.parseInt(request.getParameter("page"));
        Pageable pageable = PageRequest.of(pagenum-1, 10, Sort.by(Sort.Direction.DESC, "created"));
        User user = userService.findUserByUserId(userid);
        List<SBoardRes> sboardList = sBoardService.getSBoardsByUser(user, pageable);

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String myid = jwtUtil.getUsername(token);
        String status = userService.followCheck(userid,myid);
        return ResponseEntity.status(200).body(SBoardListRes.of(200, "성공", sboardList,status));
    }

    @GetMapping("/{sboardid}")
    @ApiOperation(value = "특정 sns 조회")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })

    public ResponseEntity<SBoardListRes> searchOne(
            @ApiIgnore HttpServletRequest request,
            @PathVariable("sboardid") Long sboardid) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String userid = jwtUtil.getUsername(token);
        User user = userService.findUserByUserId(userid);
        SBoard sboard = sBoardService.getSBoardsById((sboardid));
        List<SBoardFile> files = sBoardService.getFilesBysBoard(sboard);
        SBoardRes sboardres = new SBoardRes().of(sboard, files);
        return ResponseEntity.status(200).body(SBoardListRes.of(200, "성공", sboardres));
    }

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation(value = "게시글 작성")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "작성 오류"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MessageResponse> createSBoard(
            @ApiIgnore HttpServletRequest request,
            @ApiParam(value="sns 정보", required = true) SBoardRegisterReq sboardinfo) throws IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String userid = jwtUtil.getUsername(token);
        User user = userService.findUserByUserId(userid);
        SBoard sboard = sBoardService.createSBoard(user, sboardinfo, sboardinfo.getSnsfiles());
        return ResponseEntity.status(200).body(MessageResponse.of(200, "Success"));
    }

    @PutMapping(value = "/{sboardid}")
    @ApiOperation(value = "게시글 수정")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "작성 오류"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MessageResponse> putSBoard(
            @ApiIgnore HttpServletRequest request,
            @ApiParam(value="sns 정보", required = true) SBoardPutReq sboardinfo,
            @PathVariable("sboardid") Long sboardid ) throws IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String userid = jwtUtil.getUsername(token);
        String userid2 = sBoardService.getSBoardsById(sboardid).getUser().getUserId();
        if (!userid.equals(userid2)){
            return ResponseEntity.status(401).body(MessageResponse.of(401, "작성한 사람만 수정할 수 있습니다."));
        }
        User user = userService.findUserByUserId(userid);
        SBoard sboard = sBoardService.putSBoard(user, sboardinfo, sboardid);
        return ResponseEntity.ok(MessageResponse.of(200, "Success"));
    }

    @DeleteMapping(value = "/{sboardid}")
    @ApiOperation(value = "게시글 삭제")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "작성 오류"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MessageResponse> deleteSBoard(
            @ApiIgnore HttpServletRequest request,
            @PathVariable("sboardid") Long sboardid ) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String userid = jwtUtil.getUsername(token);
        String userid2 = sBoardService.getSBoardsById(sboardid).getUser().getUserId();
        if (!userid.equals(userid2)){
            return ResponseEntity.status(401).body(MessageResponse.of(401, "작성한 사람만 삭제할 수 있습니다."));
        }
        User user = userService.findUserByUserId(userid);
        Boolean isDelete = sBoardService.deleteSBoard(user, sboardid);
        if (isDelete) {
            return ResponseEntity.ok(MessageResponse.of(200, "Success"));
        }
        else {
            return ResponseEntity.status(401).body(MessageResponse.of(401, "삭제되지 않았습니다. 잘못된 요청입니다."));
        }

    }
}
