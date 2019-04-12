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
    private VehicleRepository vehicleRepository;

    public VehicleDto save(VehicleDto dto) {
        Vehicle model = new Vehicle(dto);

        return new VehicleDto(this.vehicleRepository.save(model));
    }

    public VehicleDto findOne(Long id) {
        Vehicle model = this.vehicleRepository.getOne(id);

        if (model == null) {
            throw new BOException("Veículo inexistente.", new Throwable("vehicle.notfound"));
        }

        return new VehicleDto(model);
    }

    public Collection<VehicleDto> findAll() {
        Collection<Vehicle> vehicles = this.vehicleRepository.findAll();
        Collection<VehicleDto> vehiclesDto = new ArrayList<>();

        vehicles.forEach((cam) -> {
            vehiclesDto.add(new VehicleDto(cam));
        });

        return vehiclesDto;
    }

    public void delete(Long id) {
        this.vehicleRepository.deleteById(id);
    }

}