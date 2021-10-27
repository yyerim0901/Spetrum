package com.spectrum.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class SBoard extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk")
    user user;

    String content;
    int likes;

    @Temporal(TemporalType.TIMESTAMP)
    Date created;
    @Temporal(TemporalType.TIMESTAMP)
    Date updated;
}
