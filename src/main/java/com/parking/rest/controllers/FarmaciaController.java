package com.parking.rest.controllers;

import com.parking.rest.dtos.FarmaciaDto;
import com.parking.rest.dtos.MedicamentoDto;
import com.parking.rest.services.FarmaciaService;
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
public class FarmaciaController {

    @Autowired
    private FarmaciaService service;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<FarmaciaDto> findOne(@PathVariable("id") Long id) {
        FarmaciaDto response = this.service.findOne(id);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<Collection<FarmaciaDto>> findAll() {
        Collection<FarmaciaDto> response = this.service.findAll();

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/filter")
    public ResponseEntity<Collection<FarmaciaDto>> findByMedicamentosContaining(@Valid @RequestBody MedicamentoDto medicamento, BindingResult result) {
        Collection<FarmaciaDto> response = this.service.findByMedicamentosContaining(medicamento.getNome());

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<?> save(@Valid @RequestBody FarmaciaDto farmaciaDto, BindingResult result) {

        return getResponseEntity(farmaciaDto, result);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/")
    public ResponseEntity<?> update(@Valid @RequestBody FarmaciaDto farmaciaDto, BindingResult result) {

        return getResponseEntity(farmaciaDto, result);
    }

    private ResponseEntity<?> getResponseEntity(@RequestBody @Valid FarmaciaDto farmaciaDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        FarmaciaDto response = this.service.save(farmaciaDto);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return ResponseEntity.ok(null);
    }

}
