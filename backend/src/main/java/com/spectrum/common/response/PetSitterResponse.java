package com.spectrum.common.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("PetSitterResponse")
public class PetSitterResponse {

    private String message;
    private Object data;

    public PetSitterResponse(String message, Object data){
        this.message = message;
        this.data = data;
    }
    public PetSitterResponse(){}

}
