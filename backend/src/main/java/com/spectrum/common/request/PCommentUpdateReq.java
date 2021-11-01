package com.spectrum.common.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PCommentUpdateReq {

    Long pCommentId;
    String content;

    public PCommentUpdateReq(Long pCommentId, String content){
        this.pCommentId = pCommentId;
        this.content = content;
    }
    public PCommentUpdateReq(){}

}
