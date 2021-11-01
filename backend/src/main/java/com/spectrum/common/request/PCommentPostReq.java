package com.spectrum.common.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PCommentPostReq {

    String content;
    Long pboardId;

    public  PCommentPostReq(String content, Long pboardId){
        this.content = content;
        this.pboardId = pboardId;
    }
    public PCommentPostReq(){}
}
