package com.greentower.api.rules.person.rest.controller;

import com.greentower.api.rules.person.domain.entity.Pessoa;
import com.greentower.api.rules.person.rest.dto.PessoaDTO;
import com.greentower.api.rules.person.service.PessoaService;
import com.greentower.api.core.util.MapperUtil;
import com.greentower.api.rules.person.service.validation.PessoaValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pessoa/v1")
@Api(value = "Api Rest Pessoas v1")
@CrossOrigin(value = "*")
public class PessoaController {

    private final PessoaService pessoaService;

    private final MapperUtil modelMapper;

    private final PessoaValidator pessoaValidator;

    @Autowired
    public PessoaController(PessoaService pessoaService, MapperUtil modelMapper, PessoaValidator pessoaValidator){
        this.pessoaService = pessoaService;
        this.modelMapper = modelMapper;
        this.pessoaValidator = pessoaValidator;
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Criação de pessoa")
    public ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoaDTO){
        Pessoa pessoa = modelMapper.mapTo(pessoaDTO, Pessoa.class);

        pessoaValidator.isNomePessoaValido(pessoa);
        pessoaValidator.isEmailPessoaValido(pessoa);
        pessoaValidator.isDataNascimetnoPessoaValido(pessoa);
        pessoaValidator.isCpfPessoaValido(pessoa);
        pessoaValidator.isCpfPessoaExisteDataBase(pessoa);

        pessoa = pessoaService.save(pessoa);
        return ResponseEntity.ok(modelMapper.mapTo(pessoa, PessoaDTO.class));
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Atualização de pessoa")
    public ResponseEntity<PessoaDTO> update(@RequestBody PessoaDTO pessoaDTO, @PathVariable("id")UUID id){
        Pessoa pessoa = modelMapper.mapTo(pessoaDTO, Pessoa.class);
        pessoa.setId(id);

        pessoaValidator.isNomePessoaValido(pessoa);
        pessoaValidator.isEmailPessoaValido(pessoa);
        pessoaValidator.isDataNascimetnoPessoaValido(pessoa);
        pessoaValidator.isCpfPessoaValido(pessoa);
        pessoaValidator.isCpfPessoaExisteDataBase(pessoa);

        pessoa = pessoaService.update(id, pessoa);
        return ResponseEntity.ok(modelMapper.mapTo(pessoa, PessoaDTO.class));
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Busca todas as pessoas")
    public ResponseEntity<List<PessoaDTO>> findAll(){
        List<Pessoa> pessoaList = pessoaService.findAll();
        if (pessoaList.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }else{
            return ResponseEntity.ok(modelMapper.mapTo(pessoaList, PessoaDTO.class));
        }
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Busca pessoa por id")
    public ResponseEntity<PessoaDTO> findById(@PathVariable("id")UUID id){
        return ResponseEntity.ok(modelMapper.mapTo(pessoaService.findById(id), PessoaDTO.class));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove pessoa por id")
    public void deleteById(@PathVariable("id")UUID id){
        pessoaService.delete(id);
    }
}
