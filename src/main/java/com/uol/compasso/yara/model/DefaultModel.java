package com.uol.compasso.yara.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class DefaultModel {

    @Id
    @ApiModelProperty(value = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

}
