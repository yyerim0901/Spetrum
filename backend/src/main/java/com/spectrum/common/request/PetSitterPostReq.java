package com.spectrum.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ApiModel("PetSitterPostRequest")
public class PetSitterPostReq {
    //name이랑 변수명이랑 동일하지 않으면 값이 아예 안 들어감..
    @ApiModelProperty(name = "title", example = "펫시터 구합니다.")
    String title;
    @ApiModelProperty(name = "content", example = "12월31일 4시간")
    String content;
    @ApiModelProperty(name = "lng", example = "127.29832410508448f")
    BigDecimal lng;
    @ApiModelProperty(name = "lat", example = "36.35502217331564f")
    BigDecimal lat;
    //작성자는 나중에 토큰에서 가지고 오기
//    @ApiModelProperty(name = "작성자 id", example = "1")
//    private Long user_pk;

}
