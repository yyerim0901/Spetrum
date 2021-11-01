package com.spectrum.service;

import com.spectrum.common.request.PCommentPostReq;
import com.spectrum.common.request.PCommentUpdateReq;
import com.spectrum.entity.PComment;

import java.util.List;

public interface PCommentService {

    public void writePComment(PCommentPostReq pCommentPostReq, String token);
    public List<PComment> listPComment(Long pboardId);
    public boolean checkPCommentWriter(Long pcommentId, String token);
    public void updatePComment(PCommentUpdateReq pCommentUpdateReq);
    public void deletePComment(Long pcommentId);

}
