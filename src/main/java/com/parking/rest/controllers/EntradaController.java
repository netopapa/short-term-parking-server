package com.parking.rest.controllers;

import com.parking.rest.dtos.EntradaDto;
import com.parking.rest.dtos.MyResult;
import com.parking.rest.services.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/rest/entrada")
public class EntradaController {

    @Autowired
    private EntradaService service;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<EntradaDto> findOne(@PathVariable("id") Long id) {
        EntradaDto response = this.service.findOne(id);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<Collection<EntradaDto>> findAll() {
        Collection<EntradaDto> response = this.service.findAll();

        return ResponseEntity.ok(response);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/filtermounth/{mes}/{farm}/{med}")
    public ResponseEntity<MyResult> findOne(@PathVariable("mes") int mes, @PathVariable("farm") Long farm, @PathVariable("med") Long med) {
        return ResponseEntity.ok(this.service.findBetween(mes, farm, med));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<?> save(@Valid @RequestBody EntradaDto entradaDto, BindingResult result) {

        return getResponseEntity(entradaDto, result);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/")
    public ResponseEntity<?> update(@Valid @RequestBody EntradaDto entradaDto, BindingResult result) {

        return getResponseEntity(entradaDto, result);
    }

    private ResponseEntity<?> getResponseEntity(@RequestBody @Valid EntradaDto entradaDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        EntradaDto response = this.service.save(entradaDto);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return ResponseEntity.ok(null);
    }
}
