package com.softplayer.apply.main.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softplayer.apply.infrastructure.generic.GenericEntity;
import com.softplayer.apply.main.domain.enums.Sexo;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "pessoa")
public class Pessoa extends GenericEntity implements Serializable {

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "sexo")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "naturalidade")
    private String naturalidade;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "endereco")
    private String endereco;
}
