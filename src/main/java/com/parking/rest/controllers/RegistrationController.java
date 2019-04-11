package com.parking.rest.controllers;

import com.parking.rest.dtos.PeriodSearchDto;
import com.parking.rest.dtos.RegistrationDto;
import com.parking.rest.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/rest/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<RegistrationDto> findOne(@PathVariable("id") Long id) {
        RegistrationDto response = this.service.findOne(id);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<Collection<RegistrationDto>> findAll() {
        Collection<RegistrationDto> response = this.service.findAll();

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/inside")
    public ResponseEntity<Collection<RegistrationDto>> findAllInside() {
        Collection<RegistrationDto> response = this.service.findAllInside();

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/findbetween")
    public ResponseEntity<?> findBetween(@Valid @RequestBody PeriodSearchDto period) {
        Collection<RegistrationDto> response = this.service.findByCheckinBetween(period);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<?> save(@Valid @RequestBody RegistrationDto RegistrationDto, BindingResult result) {

        return getResponseEntity(RegistrationDto, result);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/")
    public ResponseEntity<?> update(@Valid @RequestBody RegistrationDto RegistrationDto, BindingResult result) {

        return getResponseEntity(RegistrationDto, result);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/enableExit")
    public ResponseEntity<?> enableExit(@Valid @RequestBody RegistrationDto RegistrationDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        RegistrationDto response = this.service.enableExit(RegistrationDto);

        return ResponseEntity.ok(response);
    }

    private ResponseEntity<?> getResponseEntity(@RequestBody @Valid RegistrationDto RegistrationDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        RegistrationDto response = this.service.save(RegistrationDto);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return ResponseEntity.ok(null);
    }
}
