package com.greentower.api.rules.person.domain.repository;

import com.greentower.api.core.generic.GenericRepository;
import com.greentower.api.rules.person.domain.entity.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends GenericRepository<Pessoa> {
    Optional<Pessoa> findBycpf(String cpf);

    List<Pessoa> findAllByOrderByNome();
}
