package com.softplayer.apply.main.service.validation;

import com.softplayer.apply.infrastructure.util.ValidadorUtil;
import com.softplayer.apply.main.domain.entity.Pessoa;
import com.softplayer.apply.main.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

@Component
public class PessoaValidator {

    private final ValidadorUtil validadorUtil;
    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaValidator(ValidadorUtil validadorUtil, PessoaRepository pessoaRepository){
        this.validadorUtil = validadorUtil;
        this.pessoaRepository = pessoaRepository;
    }

    public Boolean isNomePessoaValido(Pessoa pessoa){
        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome obrigatório.");
        }
        return true;
    }

    public Boolean isDataNascimetnoPessoaValido(Pessoa pessoa){
        if (pessoa.getDataNascimento() == null || pessoa.getDataNascimento().after(Date.from(Instant.now()))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de nascimento inválida.");
        }
        return true;
    }

    public Boolean isCpfPessoaValido(Pessoa pessoa){
        if (!validadorUtil.isCpfValido(pessoa.getCpf())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cpf inválido.");
        }
        return true;
    }

    public Boolean isCpfPessoaExisteDataBase(Pessoa pessoa){
        Optional<Pessoa> pessoaDb = pessoaRepository.findBycpf(pessoa.getCpf());
        if (pessoaDb.isPresent()){
            if (!pessoaDb.get().getId().equals(pessoa.getId())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cpf informado já existe na base de dados.");
            }
        }
        return true;
    }

    public Boolean isEmailPessoaValido(Pessoa pessoa){
        if (!validadorUtil.isValidEmailAddress(pessoa.getEmail()) && !pessoa.getEmail().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email inválido.");
        }
        return true;
    }

    public void isEnderecoValido(Pessoa pessoa){
        if (pessoa.getEndereco() == null || pessoa.getEndereco().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereço obrigatório.");
        }
    }
}
