package com.parking.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parking.persistence.model.Registration;
import com.parking.persistence.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class RegistrationDto {

    private Long id;

    @NotNull(message = "Veículo não pode estar vazio.")
    @JsonProperty("car")
    private Vehicle vehicle;

    @NotNull(message = "Entrada não pode estar vazio.")
    private Date checkin;

    private Date checkout;

    private Double value;

    RegistrationDto() {
    }

    public RegistrationDto(Registration model) {
        this.id = model.getId();
        this.vehicle = model.getVehicle();
        this.checkin = model.getCheckin();

        if (model.getCheckout() != null) {
            this.setCheckout(model.getCheckout());
        }
    }
}
