package com.softplayer.apply.main.service.impl;

import com.softplayer.apply.main.domain.entity.Pessoa;
import com.softplayer.apply.main.domain.repository.PessoaRepository;
import com.softplayer.apply.main.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaServiceImpl(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa update(UUID id, Pessoa pessoa) {
        pessoa.setId(id);
        return pessoaRepository.save(pessoa);
    }

    @Override
    public void delete(UUID idPessoa) {
        pessoaRepository.deleteById(idPessoa);
    }

    @Override
    public void deleteAll() {
        pessoaRepository.deleteAll();
    }

    @Override
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa findById(UUID idPessoa) {
        return pessoaRepository.findById(idPessoa).orElse(null);
    }
}
