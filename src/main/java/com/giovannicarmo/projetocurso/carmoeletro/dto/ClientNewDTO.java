package com.giovannicarmo.projetocurso.carmoeletro.dto;

import com.giovannicarmo.projetocurso.carmoeletro.services.validation.ClientInsert;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ClientInsert
public class ClientNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Required field!")
    @Length(min = 5, max = 80, message = "The number of characters must be between 5 and 80")
    private String name;

    @NotEmpty(message = "Required field!")
    @Email(message = "Invalid email!")
    private String email;

    @NotEmpty(message = "Required field!")
    private String cpfOrCnpj;

    private Integer type;

    @NotEmpty(message = "Required field!")
    private String publicPlace;

    @NotEmpty(message = "Required field!")
    private String number;

    private String complement;
    private String neighborhood;

    @NotEmpty(message = "Required field!")
    private String zipCode;

    @NotEmpty(message = "Required field!")
    private String telephone1;

    private String telephone2;
    private String telephone3;

    private Integer cityId;

    public ClientNewDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getTelephone3() {
        return telephone3;
    }

    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
