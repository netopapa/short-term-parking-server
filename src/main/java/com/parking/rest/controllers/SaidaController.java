package com.parking.rest.controllers;

import com.parking.rest.dtos.SaidaDto;
import com.parking.rest.services.SaidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/rest/saida")
public class SaidaController {

    @Autowired
    private SaidaService service;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<SaidaDto> findOne(@PathVariable("id") Long id) {
        SaidaDto response = this.service.findOne(id);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<Collection<SaidaDto>> findAll() {
        Collection<SaidaDto> response = this.service.findAll();

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<?> save(@Valid @RequestBody SaidaDto saidaDto, BindingResult result) {

        return getResponseEntity(saidaDto, result);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/")
    public ResponseEntity<?> update(@Valid @RequestBody SaidaDto saidaDto, BindingResult result) {

        return getResponseEntity(saidaDto, result);
    }

    private ResponseEntity<?> getResponseEntity(@RequestBody @Valid SaidaDto saidaDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        SaidaDto response = this.service.save(saidaDto);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return ResponseEntity.ok(null);
    }
}
