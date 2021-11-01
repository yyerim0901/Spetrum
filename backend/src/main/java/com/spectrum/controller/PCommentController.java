package com.spectrum.controller;

import com.spectrum.common.request.PCommentPostReq;
import com.spectrum.entity.PComment;
import com.spectrum.service.PCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "펫시터 댓글 API", tags = {"PetSitterComment"})
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

}
