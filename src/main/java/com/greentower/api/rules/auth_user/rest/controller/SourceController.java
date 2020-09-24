package com.greentower.api.rules.auth_user.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source")
@Api(value = "Api Rest código fonte")
@CrossOrigin(value = "*")
public class SourceController {

    @GetMapping
    @ApiOperation(value = "Busca link código fonte")
    public String getSource(){
        return "https://github.com/gabriellmandelli";
    }
}
