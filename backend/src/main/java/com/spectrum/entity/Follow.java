package com.spectrum.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Follow extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follow")
    User follow;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower")
    User follower;
}
