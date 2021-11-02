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
    @ApiModelProperty(name="files")
    private MultipartFile thumbnail;
}
