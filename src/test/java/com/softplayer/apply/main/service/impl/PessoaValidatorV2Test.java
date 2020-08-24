package com.softplayer.apply.main.service.impl;

import com.softplayer.apply.main.domain.entity.Pessoa;
import com.softplayer.apply.main.service.validation.PessoaValidatorV2;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
public class PessoaValidatorV2Test {

    @InjectMocks
    private PessoaValidatorV2 pessoaValidator;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ResponseStatusException.class)
    public void testPessoaEnderecoInvalido() {
        Pessoa pessoa = new Pessoa();
        this.pessoaValidator.isEnderecoValido(pessoa);
    }
}