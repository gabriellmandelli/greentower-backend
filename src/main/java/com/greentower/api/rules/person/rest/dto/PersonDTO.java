package com.greentower.api.rules.person.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.greentower.api.rules.person.domain.enums.Gender;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public class PersonDTO {

    @ApiModelProperty(value = "Person id")
    private UUID id;

    @ApiModelProperty(value = "Person name", example = "Gabriel Mandelli", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(value = "Person email", example = "gabrielmandelli@hotmail.com")
    @NotBlank
    private String email;

    @ApiModelProperty(value = "Person gender", example = "MALE")
    private Gender gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Person date of birth", example = "yyyy-MM-dd", required = true)
    @NotNull
    private Date dateOfBirth;

    @ApiModelProperty(value = "Person naturalness", example = "Veneziano")
    private String naturalness;

    @ApiModelProperty(value = "Person nationality", example = "Brasileiro")
    private String nationality;

    @ApiModelProperty(value = "Person cpf", example = "58362664096")
    @NotBlank
    private String cpf;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNaturalness() {
        return naturalness;
    }

    public void setNaturalness(String naturalness) {
        this.naturalness = naturalness;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
