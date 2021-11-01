package com.spectrum.service;

import com.spectrum.common.request.PCommentPostReq;
import com.spectrum.entity.PComment;

public interface PCommentService {

    public void writePComment(PCommentPostReq pCommentPostReq, String token);

}
