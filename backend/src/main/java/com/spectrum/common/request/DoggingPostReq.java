package com.spectrum.common.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoggingPostReq {

    private float distance;
    private String time;
    private String location;
    private float[] lats;
    private float[] lngs;

    public DoggingPostReq(float distance, String time, String location, float[] lats, float[] lngs){
        this.distance = distance;
        this.time = time;
        this.location = location;
        this.lats = lats;
        this.lngs = lngs;
    }
    public DoggingPostReq(){}
}
