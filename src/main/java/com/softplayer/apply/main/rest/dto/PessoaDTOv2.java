package com.softplayer.apply.main.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softplayer.apply.main.domain.enums.Sexo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class PessoaDTOv2 {

    @ApiModelProperty(value = "Id da pessoa")
    private UUID id;

    @ApiModelProperty(value = "Nome da pessoa", example = "Gabriel Mandelli", required = true)
    private String nome;

    @ApiModelProperty(value = "Email da pessoa", example = "gabriel-mandelli@hotmail.com")
    private String email;

    @ApiModelProperty(value = "Sexo da pessoa", example = "MASCULINO")
    private Sexo sexo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Data de nascimento da pessoa", example = "yyyy-MM-dd", required = true)
    private Date dataNascimento;

    @ApiModelProperty(value = "Naturalidade da pessoa", example = "Veneziano")
    private String naturalidade;

    @ApiModelProperty(value = "Nascionalidade da pessoa", example = "Brasileiro")
    private String nacionalidade;

    @ApiModelProperty(value = "Cpf da pessoa", example = "58362664096")
    private String cpf;

    @ApiModelProperty(value = "Endereço da pessoa", example = "Estrada geral, linha mandelli, SN", required = true)
    private String endereco;
}
