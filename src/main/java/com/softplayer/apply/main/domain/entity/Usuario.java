package com.softplayer.apply.main.domain.entity;

import com.softplayer.apply.infrastructure.generic.GenericEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "usuario")
public class Usuario extends GenericEntity implements Serializable {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;
}
