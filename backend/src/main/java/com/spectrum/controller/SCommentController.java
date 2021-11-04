package com.spectrum.controller;

import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.common.request.SCommentRegisterReq;
import com.spectrum.common.response.MessageResponse;
import com.spectrum.common.response.SBoardListRes;
import com.spectrum.entity.SBoard;
import com.spectrum.entity.SComment;
import com.spectrum.entity.User;
import com.spectrum.service.SBoardService;
import com.spectrum.service.SCommentService;
import com.spectrum.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Api(value = "SNS Comment API", tags = {"SComment"})
@RestController
@RequestMapping("/api/scomments")
public class SCommentController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    UserService userService;

    @Autowired
    SBoardService sBoardService;

    @Autowired
    SCommentService sCommentService;

    @GetMapping("/{sboardid}")
    @ApiOperation(value = "sboard의 댓글 조회")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<SBoardListRes> searchComments(
            @ApiIgnore HttpServletRequest request,
            @PathVariable("sboardid") Long sboardid) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String userid = jwtUtil.getUsername(token);
        User user = userService.findUserByUserId(userid);
        SBoard sboard = sBoardService.getSBoardsById(sboardid);
        List<SComment> scommentList = sCommentService.getSCommentsBysBoard(sboard);
        return ResponseEntity.status(200).body(SBoardListRes.of(200, "성공", scommentList));
    }

    @PostMapping("/{sboardid}")
    @ApiOperation(value = "댓글 작성")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<SBoardListRes> createSComment(
            @ApiIgnore HttpServletRequest request,
            @PathVariable("sboardid") Long sboardid,
            @ApiParam(value="댓글 정보", required = true) SCommentRegisterReq scommentinfo) throws IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String userid = jwtUtil.getUsername(token);
        User user = userService.findUserByUserId(userid);
        SComment scomment = sCommentService.createSComment(user, scommentinfo, sboardid);
        if (scomment == null) {
            return ResponseEntity.status(401).body(SBoardListRes.of(401, "잘못된 요청입니다.", null));
        }
        return ResponseEntity.status(200).body(SBoardListRes.of(200, "Success", scomment));
    }

    @PutMapping(value = "/{scommentid}")
    @ApiOperation(value = "댓글 수정")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "작성 오류"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MessageResponse> putSComment(
            @ApiIgnore HttpServletRequest request,
            @ApiParam(value="댓글 정보", required = true) SCommentRegisterReq scommentinfo,
            @PathVariable("sboardid") Long scommentid) throws IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String userid = jwtUtil.getUsername(token);
        SComment scomment = sCommentService.getSCommentsById(scommentid);
        String userid2 = scomment.getUser().getUserId();
        if (!userid.equals(userid2)){
            return ResponseEntity.status(401).body(MessageResponse.of(401, "작성한 사람만 수정할 수 있습니다."));
        }
        User user = userService.findUserByUserId(userid);
        SComment sComment = sCommentService.putSComment(user, scommentinfo, scommentid);
        if (sComment == null){
            return ResponseEntity.status(401).body(MessageResponse.of(401, "잘못된 요청입니다."));
        }
        return ResponseEntity.status(200).body(MessageResponse.of(200, "Success"));
    }

    @DeleteMapping(value = "/{scommentid}")
    @ApiOperation(value = "댓글 삭제")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "작성 오류"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MessageResponse> deleteSComment(
            @ApiIgnore HttpServletRequest request,
            @PathVariable("sboardid") Long scommentid ) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String userid = jwtUtil.getUsername(token);
        SComment scomment = sCommentService.getSCommentsById(scommentid);
        String userid2 = scomment.getUser().getUserId();
        if (!userid.equals(userid2)){
            return ResponseEntity.status(401).body(MessageResponse.of(401, "작성한 사람만 수정할 수 있습니다."));
        }
        User user = userService.findUserByUserId(userid);
        Boolean isDelete = sCommentService.deleteSComment(user, scommentid);
        if (isDelete) {
            return ResponseEntity.ok(MessageResponse.of(200, "Success"));
        }
        else {
            return ResponseEntity.status(401).body(MessageResponse.of(401, "잘못된 요청입니다."));
        }

    }
}
