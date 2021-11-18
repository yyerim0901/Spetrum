package com.spectrum.common.response;

import io.jenetics.jpx.Latitude;
import io.jenetics.jpx.Longitude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DoggingDetailResponse {

    private float distance;
    private String time;
    private Date date;
    private String location;
    private String picture;
    private List<Latitude> lats;
    private List<Longitude> lngs;

}
