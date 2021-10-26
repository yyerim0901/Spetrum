package com.spectrum.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class user extends BaseEntity{
    String userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    String nickname;
    String thumbnail;
    float temperature;
    String introduce;
    int petpit;
    String keyword;

}
