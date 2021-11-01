package com.spectrum.common.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("PetSitterResponse")
public class PBoardResponse {

    private String message;
    private Object data;

    public PBoardResponse(String message, Object data){
        this.message = message;
        this.data = data;
    }
    public PBoardResponse(){}

}
