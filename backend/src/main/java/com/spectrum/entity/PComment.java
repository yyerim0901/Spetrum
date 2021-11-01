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
public class PComment extends BaseEntity {

    private String content;
    private Date created;

    @JoinColumn(name = "userpk")
    @ManyToOne
    private User user;

    @JoinColumn(name = "boardpk")
    @ManyToOne
    private PBoard pBoard;

}
