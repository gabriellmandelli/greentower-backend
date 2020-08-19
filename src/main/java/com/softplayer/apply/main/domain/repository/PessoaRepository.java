package com.softplayer.apply.main.domain.repository;

import com.softplayer.apply.infrastructure.generic.GenericRepository;
import com.softplayer.apply.main.domain.entity.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends GenericRepository<Pessoa> {
    Optional<Pessoa> findBycpf(String cpf);
}
