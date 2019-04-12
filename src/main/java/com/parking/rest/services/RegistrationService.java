package com.parking.rest.services;

import com.parking.exception.BOException;
import com.parking.persistence.model.Registration;
import com.parking.persistence.repositories.RegistrationRepository;
import com.parking.rest.dtos.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository repository;

    public RegistrationDto save(RegistrationDto dto) {
        Registration model = new Registration(dto);

        if (model.getCheckout() != null) {
            model.setInside(false);
        }

        return new RegistrationDto(this.repository.save(model));
    }

    public RegistrationDto findOne(Long id) {
        Registration model = this.repository.getOne(id);

        if (model == null) {
            throw new BOException("Registro inexistente.", new Throwable("Registration.notfound"));
        }

        return new RegistrationDto(model);
    }

    public Collection<RegistrationDto> findAll() {
        Collection<Registration> Registrations = this.repository.findAll();
        Collection<RegistrationDto> RegistrationsDto = new ArrayList<>();

        Registrations.forEach((item) -> {
            RegistrationsDto.add(new RegistrationDto(item));
        });

        return RegistrationsDto;
    }

    public Collection<RegistrationDto> findAllInside() {
        Collection<Registration> Registrations = this.repository.findAllByInside(true);
        Collection<RegistrationDto> RegistrationsDto = new ArrayList<>();

        Registrations.forEach((item) -> {
            RegistrationsDto.add(new RegistrationDto(item));
        });

        return RegistrationsDto;
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
