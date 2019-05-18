package com.parking.rest.controllers;

import com.parking.rest.dtos.ReceitaDto;
import com.parking.rest.services.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/rest/receita")
public class ReceitaController {

    @Autowired
    private ReceitaService service;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<ReceitaDto> findOne(@PathVariable("id") Long id) {
        ReceitaDto response = this.service.findOne(id);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<Collection<ReceitaDto>> findAll() {
        Collection<ReceitaDto> response = this.service.findAll();

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<?> save(@Valid @RequestBody ReceitaDto receitaDto, BindingResult result) {

        return getResponseEntity(receitaDto, result);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/")
    public ResponseEntity<?> update(@Valid @RequestBody ReceitaDto receitaDto, BindingResult result) {

        return getResponseEntity(receitaDto, result);
    }

    private ResponseEntity<?> getResponseEntity(@RequestBody @Valid ReceitaDto receitaDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        ReceitaDto response = this.service.save(receitaDto);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return ResponseEntity.ok(null);
    }

}
