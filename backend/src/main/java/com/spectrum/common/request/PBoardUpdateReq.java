package com.spectrum.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ApiModel("PetSitterUpdateRequest")
public class PBoardUpdateReq {

    @ApiModelProperty(name = "id", example = "1")
    Long id;
    @ApiModelProperty(name = "title", example = "(수정)펫시터 구합니다.")
    String title;
    @ApiModelProperty(name = "content", example = "2021.10.29 10:00~14:00")
    String content;
    @ApiModelProperty(name = "lng", example = "127.29832410508448f")
    BigDecimal lng;
    @ApiModelProperty(name = "lat", example = "36.35502217331564f")
    BigDecimal lat;
    @ApiModelProperty(name = "picture", example = "image path")
    String picture;

}
