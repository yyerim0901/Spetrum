package com.spectrum.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserLoginPostResponse")
public class UserResponse {
    @ApiModelProperty(name="응답 메시지", example = "정상")
    String message = null;
    @ApiModelProperty(name="응답 코드", example = "200")
    Integer statusCode = null;
    @ApiModelProperty(name="응답 메시지", example = "정상")
    String token = null;

    public static UserResponse of(Integer statusCode, String message) {
        UserResponse res = new UserResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

    public static UserResponse of(Integer statusCode, String message,String accesstoken) {
        UserResponse res = new UserResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setToken(accesstoken);
        return res;
    }
}
