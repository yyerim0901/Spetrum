package com.spectrum.common.response;

import com.spectrum.service.PetSitterService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Getter
@Setter
@ApiModel("PetSitterResponse")
public class PetSitterResponse {

    private String title;
    private String content;
    private String picture;
    private Date created;
    private Date updated;

    public PetSitterResponse(String title, String content, String picture, Date created, Date updated){
        this.title = title;
        this.content = content;
        this.picture = picture;
        this.created = created;
        this.updated = updated;
    }

}
