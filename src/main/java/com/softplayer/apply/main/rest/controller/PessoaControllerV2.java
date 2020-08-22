package com.softplayer.apply.main.rest.controller;

import com.softplayer.apply.infrastructure.util.MapperUtil;
import com.softplayer.apply.main.domain.entity.Pessoa;
import com.softplayer.apply.main.rest.dto.PessoaDTOv2;
import com.softplayer.apply.main.service.PessoaService;
import com.softplayer.apply.main.service.validation.PessoaValidatorV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pessoa/v2")
@Api(value = "Api Rest Pessoas v2")
@CrossOrigin(value = "*")
public class PessoaControllerV2 {

    private final PessoaService pessoaService;

    private final MapperUtil modelMapper;

    private final PessoaValidatorV2 pessoaValidator;

    @Autowired
    public PessoaControllerV2(PessoaService pessoaService, MapperUtil modelMapper, PessoaValidatorV2 pessoaValidator){
        this.pessoaService = pessoaService;
        this.modelMapper = modelMapper;
        this.pessoaValidator = pessoaValidator;
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Criação de pessoa")
    public ResponseEntity<PessoaDTOv2> save(@RequestBody PessoaDTOv2 pessoaDTO){
        Pessoa pessoa = modelMapper.mapTo(pessoaDTO, Pessoa.class);
        pessoaValidator.isPessoaValida(pessoa);
        pessoa = pessoaService.save(pessoa);
        return ResponseEntity.ok(modelMapper.mapTo(pessoa, PessoaDTOv2.class));
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Atualização de pessoa")
    public ResponseEntity<PessoaDTOv2> update(@RequestBody PessoaDTOv2 pessoaDTO, @PathVariable("id")UUID id){
        Pessoa pessoa = modelMapper.mapTo(pessoaDTO, Pessoa.class);
        pessoa.setId(id);
        pessoaValidator.isPessoaValida(pessoa);
        pessoa = pessoaService.update(id, pessoa);
        return ResponseEntity.ok(modelMapper.mapTo(pessoa, PessoaDTOv2.class));
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Busca todas as pessoas")
    public ResponseEntity<List<PessoaDTOv2>> findAll(){
        List<Pessoa> pessoaList = pessoaService.findAll();

        return ResponseEntity.ok( modelMapper.mapTo(pessoaList, PessoaDTOv2.class));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Busca pessoa por id")
    public ResponseEntity<PessoaDTOv2> findById(@PathVariable("id")UUID id){
        return ResponseEntity.ok(modelMapper.mapTo(pessoaService.findById(id), PessoaDTOv2.class));
    }

    @DeleteMapping(value = "")
    @ApiOperation(value = "Remove todas as pessoas")
    public void deleteAll(){
        pessoaService.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove pessoa por id")
    public void deleteAll(@PathVariable("id")UUID id){
        pessoaService.delete(id);
    }
}
