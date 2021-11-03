package com.spectrum.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @ElementCollection
//    private List<Path> path = new ArrayList<>();

    @JoinColumn(name = "user_pk")
    @ManyToOne
    private User user;
}
