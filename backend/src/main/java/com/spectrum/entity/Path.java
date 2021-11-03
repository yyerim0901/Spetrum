package com.spectrum.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Path {

    private float lat;
    private float lng;

    public Path(float lat, float lng){
        this.lat = lat;
        this.lng = lng;
    }
    public Path(){}

}
