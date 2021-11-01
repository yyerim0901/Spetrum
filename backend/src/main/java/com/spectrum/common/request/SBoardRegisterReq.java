package com.spectrum.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("SBoardRegisterRequest")
public class SBoardRegisterReq {
    @ApiModelProperty(name="content", example="반갑습니다")
    private String content;
    @ApiModelProperty(name="token", example="eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InNzYWZ5IiwiaWF0IjoxNjM1NzY4MTE2LCJleHAiOjE2MzU3Nzg5MTZ9.c7ZmNk-tK-IHrxR5ZEgBwLsGrk01uuWElIEOBwMYCWE")
    private String token;
}
