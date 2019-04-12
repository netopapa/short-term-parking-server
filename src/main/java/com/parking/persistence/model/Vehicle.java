package com.parking.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parking.persistence.model.base.BaseModel;
import com.parking.rest.dtos.VehicleDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vehicle extends BaseModel implements Serializable {

    private static final long serialVersionUID = 575436956203506212L;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "plate", nullable = false, unique = true)
    private String plate;

    @Column(name = "color", nullable = false)
    private String color;

    public Vehicle() {
    }

    public Vehicle(VehicleDto dto) {
        if (dto.getId() != null) {
            super.setId(dto.getId());
        }

        this.model = dto.getModel();
        this.plate = dto.getPlate();
        this.color = dto.getColor();
    }

}
