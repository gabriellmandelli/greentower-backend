package com.softplayer.apply.infrastructure.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface GenericRepository<T extends GenericClass> extends JpaRepository<T, UUID> {

}