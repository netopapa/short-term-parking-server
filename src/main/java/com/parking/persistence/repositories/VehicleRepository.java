package com.parking.persistence.repositories;

import com.parking.persistence.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	Vehicle findByPlate(String plate);
}
