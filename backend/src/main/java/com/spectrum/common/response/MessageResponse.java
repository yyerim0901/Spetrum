package com.spectrum.common.response;

import com.spectrum.entity.SBoard;
import com.spectrum.entity.SBoardFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@ApiModel("MessageResponse")
public class MessageResponse {

    @ApiModelProperty(name="응답 메시지", example = "정상")
    String message = null;
    @ApiModelProperty(name="응답 코드", example = "200")
    Integer statusCode = null;

    public static MessageResponse of(Integer statusCode, String message) {
        MessageResponse res = new MessageResponse();
        res.setMessage(message);
        res.setStatusCode(statusCode);
        return res;
    }
}
