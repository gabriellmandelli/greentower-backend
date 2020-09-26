package com.greentower.api.rules.person.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.greentower.api.rules.person.domain.enums.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class PersonDTOv2 {

    @ApiModelProperty(value = "Person id")
    private UUID id;

    @ApiModelProperty(value = "Person name", example = "Gabriel Mandelli", required = true)
    private String name;

    @ApiModelProperty(value = "Person email", example = "gabrielmandelli@hotmail.com")
    private String email;

    @ApiModelProperty(value = "Person gender", example = "MALE")
    private Gender gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Person date of birth", example = "yyyy-MM-dd", required = true)
    private Date dateOfBirth;

    @ApiModelProperty(value = "Person naturalness", example = "Veneziano")
    private String naturalness;

    @ApiModelProperty(value = "Person nationality", example = "Brasileiro")
    private String nationality;

    @ApiModelProperty(value = "Person cpf", example = "58362664096")
    private String cpf;

    @ApiModelProperty(value = "Person address", example = "Estrada geral, linha mandelli, SN", required = true)
    private String address;
}
