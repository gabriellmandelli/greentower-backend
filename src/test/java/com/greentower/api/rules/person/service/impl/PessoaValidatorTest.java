package com.greentower.api.rules.person.service.impl;

import com.greentower.api.core.util.ValidadorUtil;
import com.greentower.api.rules.person.domain.entity.Pessoa;
import com.greentower.api.rules.person.domain.repository.PessoaRepository;
import com.greentower.api.rules.person.service.PessoaService;
import com.greentower.api.rules.person.service.validation.PessoaValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
public class PessoaValidatorTest {

    @InjectMocks
    private PessoaValidator pessoaValidator;

    @Mock
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ValidadorUtil validadorUtil;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ResponseStatusException.class)
    public void testPessoaInvalido() {
        Pessoa pessoa = new Pessoa();
        this.pessoaValidator.isCpfPessoaValido(pessoa);
    }

    @Test(expected = ResponseStatusException.class)
    public void testPessoaNomeInvalido() {
        Pessoa pessoa = new Pessoa();
        this.pessoaValidator.isNomePessoaValido(pessoa);
    }

    @Test(expected = ResponseStatusException.class)
    public void testPessoaDataNascimentoInvalido() {
        Pessoa pessoa = new Pessoa();
        this.pessoaValidator.isDataNascimetnoPessoaValido(pessoa);
    }

    @Test(expected = ResponseStatusException.class)
    public void testPessoaEmailInvalido() {
        Pessoa pessoa = new Pessoa();
        pessoa.setEmail("softplayer@");
        this.pessoaValidator.isEmailPessoaValido(pessoa);
    }

    @Test(expected = ResponseStatusException.class)
    public void testPessoaCpfInvalido() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("12345678912");
        this.pessoaValidator.isCpfPessoaValido(pessoa);
    }

    @Test(expected = ResponseStatusException.class)
    public void testPessoaEnderecoInvalido() {
        Pessoa pessoa = new Pessoa();
        this.pessoaValidator.isEnderecoValido(pessoa);
    }
}