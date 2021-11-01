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
    @ApiModelProperty(name="files")
    private List<MultipartFile> snsfiles;
}
