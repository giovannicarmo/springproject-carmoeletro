package com.giovannicarmo.projetocurso.carmoeletro.dto;

import com.giovannicarmo.projetocurso.carmoeletro.domain.City;

import java.io.Serializable;

public class CityDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public CityDTO() {
    }

    public CityDTO(City object) {
        this.id = object.getId();
        this.name = object.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
