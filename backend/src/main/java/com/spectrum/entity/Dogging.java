package com.spectrum.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Dogging extends BaseEntity {

    private float distance;
    private int time;
    private Date date;
    private String location;
    private String picture;

    @JoinColumn(name = "user_pk")
    @ManyToOne
    private user user;
}
