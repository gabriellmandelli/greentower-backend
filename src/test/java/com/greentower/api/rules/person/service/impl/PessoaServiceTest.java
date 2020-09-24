package com.greentower.api.rules.person.service.impl;

import com.greentower.api.rules.person.domain.entity.Pessoa;
import com.greentower.api.rules.person.domain.repository.PessoaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class PessoaServiceTest {

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ResponseStatusException.class)
    public void testFindByID() {
        UUID idPessoa = UUID.randomUUID();
        pessoaService.findById(idPessoa);
    }

    @Test
    public void testFindAllEmpty(){
        List<Pessoa> pessoaList = pessoaService.findAll();
        Assert.assertEquals(pessoaList, Collections.emptyList());
    }
}