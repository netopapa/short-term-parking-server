package com.parking.rest.dtos;

import com.parking.persistence.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VehicleDto {
    private Long id;

    @NotNull(message = "Modelo não pode estar vazio.")
    private String model;

    @NotNull(message = "Cor não pode estar vazio.")
    private String color;

    @NotNull(message = "Placa não pode estar vazio.")
    private String plate;

    public VehicleDto() {
    }

    public VehicleDto(Vehicle model) {
        this.id = model.getId();
        this.model = model.getModel();
        this.color = model.getColor();
        this.plate = model.getPlate();
    }

}
