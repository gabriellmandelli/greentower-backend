package com.greentower.api.rules.person.service;

import com.greentower.api.rules.person.domain.entity.Pessoa;

import java.util.List;
import java.util.UUID;

public interface PessoaService {

    Pessoa save(Pessoa pessoa);

    Pessoa update(UUID id, Pessoa pessoa);

    void delete(UUID idPessoa);

    List<Pessoa> findAll();

    Pessoa findById(UUID id);
}
