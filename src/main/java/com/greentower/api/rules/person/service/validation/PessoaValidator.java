package com.greentower.api.rules.person.service.validation;

import com.greentower.api.core.util.ValidadorUtil;
import com.greentower.api.rules.person.domain.entity.Pessoa;
import com.greentower.api.rules.person.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

@Component
public class PessoaValidator {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaValidator(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    public Boolean isNomePessoaValido(Pessoa pessoa){
        return (pessoa.getNome() == null || pessoa.getNome().isEmpty());
    }

    public Boolean isDataNascimetnoPessoaValido(Pessoa pessoa){
        return (pessoa.getDataNascimento() == null || pessoa.getDataNascimento().after(Date.from(Instant.now())));
    }

    public Boolean isCpfPessoaValido(Pessoa pessoa){
        return (!ValidadorUtil.isCpfValido(pessoa.getCpf()));
    }

    public Boolean isCpfPessoaExisteDataBase(Pessoa pessoa){
        Optional<Pessoa> pessoaDb = pessoaRepository.findBycpf(pessoa.getCpf());
        return pessoaDb.map(value -> value.getId().equals(pessoa.getId())).orElse(true);
    }

    public Boolean isEmailPessoaValido(Pessoa pessoa){
        return (!ValidadorUtil.isValidEmailAddress(pessoa.getEmail()) && !pessoa.getEmail().isEmpty());
    }

    public Boolean isEnderecoValido(Pessoa pessoa){
        return (pessoa.getEndereco() == null || pessoa.getEndereco().isEmpty());
    }
}
