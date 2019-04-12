package com.parking.persistence.repositories;

import com.parking.persistence.model.Registration;
import com.parking.persistence.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Collection<Registration> findAllByInside(Boolean inside);
    Collection<Registration> findAllByVehicleId(Long id);
    Registration findByVehicleAndInside(Vehicle vehicle, Boolean inside);
    Collection<Registration> findByCheckinBetween(Date start, Date end);
}
