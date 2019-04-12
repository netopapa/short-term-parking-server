package com.parking.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parking.persistence.model.base.BaseModel;
import com.parking.rest.dtos.RegistrationDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "registration")
public class Registration extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, optional = false)
    private Vehicle vehicle;

    @Column(name = "checkin", nullable = false)
    private Date checkin;

    @Column(name = "checkout")
    private Date checkout;

    @Column(name = "value")
    private Double value;

    @Column(name = "inside")
    private Boolean inside = true;

    Registration() {
    }

    public Registration(RegistrationDto dto) {
        if (dto.getId() != null) {
            super.setId(dto.getId());
        }

        this.value = dto.getValue();
        this.vehicle = dto.getVehicle();
        this.checkin = dto.getCheckin();

        if (dto.getCheckout() != null) {
            this.setCheckout(dto.getCheckout());
        }
    }
}
