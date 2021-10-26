package com.spectrum.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {
    @ApiModelProperty(name="userId", example="ssafy")
    String userId;
    @ApiModelProperty(name="password", example="1234")
    String password;
    @ApiModelProperty(name="nickname", example="codingprince")
    String nickname;

}
