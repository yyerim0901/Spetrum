package com.spectrum.service;

import com.spectrum.common.request.SCommentRegisterReq;
import com.spectrum.entity.SBoard;
import com.spectrum.entity.SComment;
import com.spectrum.entity.User;

import java.util.List;
import java.util.Optional;

public interface SCommentService {
    List<SComment> getSCommentsBysBoard(SBoard sboard);

    SComment createSComment(User user, SCommentRegisterReq scommentinfo, Long sboardid);

    SComment getSCommentsById(Long scommentid);

    SComment putSComment(User user, SCommentRegisterReq scommentinfo, Long scommentid);

    Boolean deleteSComment(User user, Long scommentid);
}
