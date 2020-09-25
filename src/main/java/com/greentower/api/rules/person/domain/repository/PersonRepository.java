package com.greentower.api.rules.person.domain.repository;

import com.greentower.api.core.generic.GenericRepository;
import com.greentower.api.rules.person.domain.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends GenericRepository<Person> {
    Optional<Person> findBycpf(String cpf);

    List<Person> findAllByOrderByNome();
}
