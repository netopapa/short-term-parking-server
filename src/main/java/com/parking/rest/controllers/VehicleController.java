package com.parking.rest.controllers;

import com.parking.rest.dtos.VehicleDto;
import com.parking.rest.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/rest/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<VehicleDto> findOne(@PathVariable("id") Long id) {
        VehicleDto response = this.service.findOne(id);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<Collection<VehicleDto>> findAll() {
        Collection<VehicleDto> response = this.service.findAll();

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<?> save(@Valid @RequestBody VehicleDto vehicleDto, BindingResult result) {

        return getResponseEntity(vehicleDto, result);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/")
    public ResponseEntity<?> update(@Valid @RequestBody VehicleDto vehicleDto, BindingResult result) {

        return getResponseEntity(vehicleDto, result);
    }

    private ResponseEntity<?> getResponseEntity(@RequestBody @Valid VehicleDto vehicleDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        VehicleDto response = this.service.save(vehicleDto);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return ResponseEntity.ok(null);
    }

}
