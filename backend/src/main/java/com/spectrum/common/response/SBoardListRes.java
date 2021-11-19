package com.spectrum.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SBoardListRes")
public class SBoardListRes {
    @ApiModelProperty(name="응답 메시지", example = "정상")
    String message = null;
    @ApiModelProperty(name="응답 코드", example = "200")
    Integer statusCode = null;
    @ApiModelProperty(name="데이터")
    Object data;
    @ApiModelProperty(name="응답 메시지", example = "정상")
    String followStatus = null;

    public static SBoardListRes of(Integer statusCode, String message, Object data) {
        SBoardListRes res = new SBoardListRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setData(data);
        return res;
    }

    public static SBoardListRes of(Integer statusCode, String message, Object data, String status) {
        SBoardListRes res = new SBoardListRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setData(data);
        res.setFollowStatus(status);
        return res;
    }
}
