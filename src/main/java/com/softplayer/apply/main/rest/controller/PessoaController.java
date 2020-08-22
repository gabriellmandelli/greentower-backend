package com.softplayer.apply.main.rest.controller;

import com.softplayer.apply.infrastructure.util.MapperUtil;
import com.softplayer.apply.main.domain.entity.Pessoa;
import com.softplayer.apply.main.rest.dto.PessoaDTO;
import com.softplayer.apply.main.service.PessoaService;
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
@RequestMapping("/pessoa/v1")
@Api(value = "Api Rest Pessoas")
@CrossOrigin(value = "*")
public class PessoaController {

    private final PessoaService pessoaService;

    private final MapperUtil modelMapper;

    @Autowired
    public PessoaController(PessoaService pessoaService, MapperUtil modelMapper){
        this.pessoaService = pessoaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Criação de pessoa")
    public ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoaDTO){
        Pessoa pessoa = pessoaService.save(modelMapper.mapTo(pessoaDTO, Pessoa.class));
        return ResponseEntity.ok(modelMapper.mapTo(pessoa, PessoaDTO.class));
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Atualização de pessoa")
    public ResponseEntity<PessoaDTO> update(@RequestBody PessoaDTO pessoaDTO, @PathVariable("id")UUID id){

        Pessoa pessoa = pessoaService.update(id, modelMapper.mapTo(pessoaDTO, Pessoa.class));
        return ResponseEntity.ok(modelMapper.mapTo(pessoa, PessoaDTO.class));
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Busca todas as pessoas")
    public ResponseEntity<List<PessoaDTO>> findAll(){
        List<Pessoa> pessoaList = pessoaService.findAll();

        return ResponseEntity.ok( modelMapper.mapTo(pessoaList, PessoaDTO.class));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Busca pessoa por id")
    public ResponseEntity<PessoaDTO> findById(@PathVariable("id")UUID id){
        return ResponseEntity.ok(modelMapper.mapTo(pessoaService.findById(id), PessoaDTO.class));
    }

    @DeleteMapping(value = "")
    @ApiOperation(value = "Remove todas as pessoas")
    public void deleteAll(){
        pessoaService.deleteAll();
    }
}
