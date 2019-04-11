package com.parking.rest.services;

import com.parking.exception.BOException;
import com.parking.persistence.model.Registration;
import com.parking.persistence.repositories.RegistrationRepository;
import com.parking.persistence.repositories.VehicleRepository;
import com.parking.rest.dtos.PeriodSearchDto;
import com.parking.rest.dtos.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository repository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public RegistrationDto save(RegistrationDto dto) {
        Registration model = new Registration(dto);

        if (model.getVehicle().getId() == 0) {
            model.setVehicle(vehicleRepository.save(model.getVehicle()));
        } else if (repository.findByVehicleAndInside(model.getVehicle(), true) != null && !model.getInside()) {
            throw new BOException("Este veículo já está estacionado.", new Throwable("registration.already.exists"));
        }

        if (model.getCheckout() != null) {
            model.setInside(false);
        }

        return new RegistrationDto(this.repository.save(model));
    }

    public RegistrationDto enableExit(RegistrationDto dto) {
        Registration model = new Registration(dto);
        model.setInside(false);

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
        Collection<Registration> Registrations = this.repository.findAllByInside(false);
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

    public Collection<RegistrationDto> findByCheckinBetween(PeriodSearchDto period) {
        Collection<Registration> Registrations = this.repository.findByCheckinBetween(period.getStart(), new Date(period.getEnd().getTime() + (1000 * 60 * 60 * 24)));
        Collection<RegistrationDto> RegistrationsDto = new ArrayList<>();
        for (Registration item : Registrations) {
            if (!period.getInside() && item.getInside()) {
                continue;
            }
            RegistrationsDto.add(new RegistrationDto(item));
        }

        return RegistrationsDto;
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
