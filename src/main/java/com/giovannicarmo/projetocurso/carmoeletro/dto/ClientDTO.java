package com.giovannicarmo.projetocurso.carmoeletro.dto;

import com.giovannicarmo.projetocurso.carmoeletro.domain.Client;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Required field!")
    @Length(min = 5, max = 80, message = "The number of characters must be between 5 and 80")
    private String name;

    @NotEmpty(message = "Required field!")
    @Email(message = "Invalid email!")
    private String email;

    public ClientDTO() {
    }

    public ClientDTO(Client object) {
        id = object.getId();
        name = object.getName();
        email = object.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
