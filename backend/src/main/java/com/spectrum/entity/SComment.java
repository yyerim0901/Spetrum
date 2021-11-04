package com.spectrum.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
public class SComment extends BaseEntity {
    String content;
    Date created;

    @JoinColumn(name = "user_pk")
    @ManyToOne
    User user;

    @JoinColumn(name = "sboard_pk")
    @ManyToOne
    SBoard sBoard;
}
