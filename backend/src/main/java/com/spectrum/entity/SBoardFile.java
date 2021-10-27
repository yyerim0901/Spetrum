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
public class SBoardFile extends BaseEntity {

    String save_file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_pk")
    SBoard sBoard;
}
