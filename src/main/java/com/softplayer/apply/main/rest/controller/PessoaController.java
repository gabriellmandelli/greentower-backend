package com.softplayer.apply.main.rest.controller;

import com.softplayer.apply.main.domain.entity.Pessoa;
import com.softplayer.apply.main.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pessoa/v1/")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
        return ResponseEntity.ok(pessoaService.save(pessoa));
    }

    @PostMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa, @PathVariable("id")UUID id){
        return ResponseEntity.ok(pessoaService.update(id, pessoa));
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Pessoa>> findAll(){
        return ResponseEntity.ok(pessoaService.findAll());
    }

    @GetMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Pessoa> findById(@PathVariable("id")UUID id){
        return ResponseEntity.ok(pessoaService.findById(id));
    }

    @DeleteMapping(value = "")
    public void deleteAll(){
        pessoaService.deleteAll();
    }
}
