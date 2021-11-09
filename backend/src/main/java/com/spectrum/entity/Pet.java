package com.spectrum.entity;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Pet extends BaseEntity {

    private String kind;
    private int gender;
    private String center;
    private String noticenumber;
    private String field;
    private int keyword;
    private String url;

    public Pet(String kind, int gender, String center, String noticenumber, String field, int keyword, String url){
        this.kind = kind;
        this.gender = gender;
        this.center = center;
        this.noticenumber = noticenumber;
        this.field = field;
        this.keyword = keyword;
        this.url = url;
    }
    public Pet(){}
}
