package com.spectrum.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SBoardRegisterRequest")
public class SBoardRegisterReq {
    @ApiModelProperty(name="content", example="반갑습니다")
    String content;
}
