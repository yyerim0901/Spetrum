package com.spectrum.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FollowRequest")
public class FollowReq {
    @ApiModelProperty(name = "from", example = "ssafy")
    String from;
    @ApiModelProperty(name = "to", example = "kido")
    String to;
}
