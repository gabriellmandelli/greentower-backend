package com.softplayer.apply.main.service;

import com.softplayer.apply.main.domain.entity.Pessoa;

import java.util.List;
import java.util.UUID;

public interface PessoaService {

    Pessoa save(Pessoa pessoa);

    Pessoa update(UUID id, Pessoa pessoa);

    void delete(UUID idPessoa);

    void deleteAll();

    List<Pessoa> findAll();

    Pessoa findById(UUID id);
}
