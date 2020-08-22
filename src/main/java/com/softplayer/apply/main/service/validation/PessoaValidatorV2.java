package com.softplayer.apply.main.service.validation;

import com.softplayer.apply.infrastructure.util.ValidadorUtil;
import com.softplayer.apply.main.domain.entity.Pessoa;
import com.softplayer.apply.main.domain.repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class PessoaValidatorV2 extends PessoaValidator {

    public PessoaValidatorV2(ValidadorUtil validadorUtil, PessoaRepository pessoaRepository) {
        super(validadorUtil, pessoaRepository);
    }

    @Override
    public boolean isPessoaValida(Pessoa pessoa) {
        super.isPessoaValida(pessoa);

        if (pessoa.getEndereco() == null || pessoa.getEndereco().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereço obrigatório.");
        }
        return true;
    }
}
