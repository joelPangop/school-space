package com.example.schoolspace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Dto {

    @JsonProperty("_id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
