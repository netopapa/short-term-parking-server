package com.parking.rest.services;

import com.parking.exception.BOException;
import com.parking.persistence.model.Vehicle;
import com.parking.persistence.repositories.VehicleRepository;
import com.parking.rest.dtos.VehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public VehicleDto save(VehicleDto dto) {
        Vehicle model = new Vehicle(dto);

        return new VehicleDto(this.repository.save(model));
    }

    public VehicleDto findOne(Long id) {
        Vehicle model = this.repository.getOne(id);

        if (model == null) {
            throw new BOException("Veículo inexistente.", new Throwable("vehicle.notfound"));
        }

        return new VehicleDto(model);
    }

    public Collection<VehicleDto> findAll() {
        Collection<Vehicle> vehicles = this.repository.findAll();
        Collection<VehicleDto> vehiclesDto = new ArrayList<>();

        vehicles.forEach((item) -> {
            vehiclesDto.add(new VehicleDto(item));
        });

        return vehiclesDto;
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

}