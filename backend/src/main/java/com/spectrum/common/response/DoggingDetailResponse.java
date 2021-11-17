package com.spectrum.common.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DoggingDetailResponse {

    private float distance;
    private String time;
    private Date date;
    private String location;
    private String picture;
    private Object[] objects;

}
