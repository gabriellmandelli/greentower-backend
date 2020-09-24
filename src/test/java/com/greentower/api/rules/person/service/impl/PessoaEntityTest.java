package com.greentower.api.rules.person.service.impl;

import com.greentower.api.rules.person.domain.enums.Sexo;
import com.greentower.api.rules.person.domain.entity.Pessoa;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
public class PessoaEntityTest {

    @Test
    public void testPessoa() {
        Pessoa pessoa = new Pessoa();
        Date dataNascimento = new Date();
        UUID idPessoa = UUID.randomUUID();

        pessoa.setId(idPessoa);
        pessoa.setNome("Softplayer");
        pessoa.setEmail("softplayer@softplayer.com");
        pessoa.setSexo(Sexo.MASCULINO);
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setNaturalidade("Veneziano");
        pessoa.setNacionalidade("Brasileiro");
        pessoa.setCpf("12345678912");
        pessoa.setEndereco("Estrado geral, linha mandelli, SN");

        Assert.assertEquals(pessoa.getId(), idPessoa);
        Assert.assertEquals(pessoa.getNome(), "Softplayer");
        Assert.assertEquals(pessoa.getEmail(), "softplayer@softplayer.com");
        Assert.assertEquals(pessoa.getSexo(), Sexo.MASCULINO);
        Assert.assertEquals(pessoa.getDataNascimento(), dataNascimento);
        Assert.assertEquals(pessoa.getNaturalidade(), "Veneziano");
        Assert.assertEquals(pessoa.getNacionalidade(), "Brasileiro");
        Assert.assertEquals(pessoa.getCpf(), "12345678912");
        Assert.assertEquals(pessoa.getEndereco(), "Estrado geral, linha mandelli, SN");

    }
}