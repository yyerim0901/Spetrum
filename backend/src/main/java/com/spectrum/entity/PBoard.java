package com.spectrum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;
//import org.springframework.data.geo.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class PBoard extends BaseEntity{

    private String title;
    private String content;
    private String picture;
    private Date created;
    private Date updated;
    private int status;
//    @Column(precision=10, scale=7)
//    private BigDecimal lng;
//    @Column(precision=10, scale=7)
//    private BigDecimal lat;
    @JsonIgnore
    private Point point; //위경도 저장 타입

//    @JsonIgnore
    @JoinColumn(name = "user_pk")
    @ManyToOne
    private User user;
}
