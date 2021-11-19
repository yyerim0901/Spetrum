package com.spectrum.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserLoginRequest")
public class UserLoginReq {
    @ApiModelProperty(name="userId", example="ssafy")
    String userId;
    @ApiModelProperty(name="password", example="1234")
    String password;
}
