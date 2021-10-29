package com.spectrum.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class PetSitter extends BaseEntity{

    private String title;
    private String content;
    private String picture;
    private Date created;
    private Date updated;
    private int status;
    @Column(precision=10, scale=7)
    private BigDecimal lng;
    @Column(precision=10, scale=7)
    private BigDecimal lat;

    @JoinColumn(name = "user_pk")
    @ManyToOne
    private user user;
}
