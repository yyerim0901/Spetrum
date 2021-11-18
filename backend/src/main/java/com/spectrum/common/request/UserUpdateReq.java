package com.spectrum.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ApiModel("UserUpdateReq")
public class UserUpdateReq {
    @ApiModelProperty(name="password", example="1234")
    private String password;
    @ApiModelProperty(name="nickname", example="codingprince")
    private String nickname;
    @ApiModelProperty(name="userid", example="codingprince")
    private String userid;
    @ApiModelProperty(name="introduce", example="i'm korean")
    private String introduce;
    @ApiModelProperty(name="temperature", example="36.5")
    private float temperature;
    @ApiModelProperty(name="PETPTI", example="100")
    private int PETPTI;
    @ApiModelProperty(name="keyword", example="활발")
    private String keyword;
    @ApiModelProperty(name="thumbnail")
    private MultipartFile thumbnail;
}
