package com.greentower.api.rules.person.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.greentower.api.rules.person.domain.enums.Gender;
import com.greentower.api.core.generic.GenericEntity;
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
@Table(name = "person")
public class Person extends GenericEntity implements Serializable {

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "sexo")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "naturalness")
    private String naturalness;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "address")
    private String address;
}
