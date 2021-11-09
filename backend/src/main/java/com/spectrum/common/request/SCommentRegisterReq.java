package com.spectrum.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SCommentRegisterReq")
public class SCommentRegisterReq {
    @ApiModelProperty(name="content", example="반갑습니다")
    private String content;
}
