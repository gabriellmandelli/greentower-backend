package com.greentower.api.rules.person.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.greentower.api.core.generic.AbstractAuditingEntity;
import com.greentower.api.rules.person.domain.enums.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person extends AbstractAuditingEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
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

    public Person(UUID id, Long sequential, LocalDateTime createdAt, LocalDateTime updatedAt, String name, String email, Gender gender, Date dateOfBirth, String naturalness, String nationality, String cpf) {
        super(id, sequential, createdAt, updatedAt);
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.naturalness = naturalness;
        this.nationality = nationality;
        this.cpf = cpf;
    }

    public Person(){
        super();
    }

}
