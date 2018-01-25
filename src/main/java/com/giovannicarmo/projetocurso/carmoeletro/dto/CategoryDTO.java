package com.giovannicarmo.projetocurso.carmoeletro.dto;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Category;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatorio!")
    @Length(min = 5, max = 80, message = "O numero de caracteres deve ser entre 5 e 80")
    private String name;

    public CategoryDTO(){}

    public CategoryDTO(Category category) {
        id = category.getId();
        name = category.getName();
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
