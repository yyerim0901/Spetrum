package com.spectrum.controller;

import com.spectrum.common.request.PCommentPostReq;
import com.spectrum.common.request.PCommentUpdateReq;
import com.spectrum.common.response.PBoardResponse;
import com.spectrum.entity.PComment;
import com.spectrum.service.PCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "펫시터 댓글 API", tags = {"PComment"})
@RestController
@RequestMapping("/api/pcomment")
public class PCommentController {

    @Autowired
    private PCommentService pCommentService;
    
    @ApiOperation(
            value = "펫시터 댓글 작성",
            notes = "**글번호**와 content로 작성"
    )
    @PostMapping
    private ResponseEntity<String> writePComment( @ApiParam(value = "댓글 작성", required = true) PCommentPostReq pCommentPostReq,
                                                 HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        pCommentService.writePComment(pCommentPostReq,token);
        return new ResponseEntity<>("write comment success", HttpStatus.OK);
    }
    
    @ApiOperation(
            value = "댓글 리스트",
            notes = "**글번호**를 이용해서 댓글 출력"
    )
    @GetMapping
    private PBoardResponse listOfPComment(@ApiParam(value = "댓글 리스트",required = true) @RequestParam Long pboardId){
        List<PComment> list = pCommentService.listPComment(pboardId);
        if(list.isEmpty() || list == null){
            return new PBoardResponse("작성된 댓글이 없습니다.",null);
        }else return new PBoardResponse("댓글 리스트 출력 완료",list);
    }
    
    @ApiOperation(
            value = "댓글 수정",
            notes = "**댓글 id,내용**으로 수정"
    )
    @PutMapping
    private ResponseEntity<String> updateComment(HttpServletRequest request,
                                                 @ApiParam(value = "댓글 수정", required = true)PCommentUpdateReq pCommentUpdateReq){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!pCommentService.checkPCommentWriter(pCommentUpdateReq.getPCommentId(), token)){
            return new ResponseEntity<>("댓글 수정 권한 없음",HttpStatus.UNAUTHORIZED);
        }else
            pCommentService.updatePComment(pCommentUpdateReq);
            return new ResponseEntity<>("댓글 수정 완료",HttpStatus.OK);
    }
    
    @ApiOperation(
            value = "댓글 삭제",
            notes = "**댓글 id**를 이용해서 삭제"
    )
    @DeleteMapping
    private ResponseEntity<String> deletePComment(@ApiParam(value = "댓글 삭제", required = true) @RequestParam Long commentId){
        pCommentService.deletePComment(commentId);
        return new ResponseEntity("댓글 삭제 완료",HttpStatus.OK);
    }

}
