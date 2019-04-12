package com.parking.persistence.repositories;

import com.parking.persistence.model.Registration;
import com.parking.persistence.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Collection<Registration> findAllByInside(Boolean inside);

    Registration findByVehicleAndInside(Vehicle vehicle, Boolean inside);
}
