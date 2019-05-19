package com.parking.persistence.repositories;

import com.parking.persistence.model.Farmacia;
import com.parking.persistence.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
    @Query("select i from Farmacia as i join i.medicamentos c where c = :medicamento")
    Collection<Farmacia> findByMedicamentosContaining(@Param("medicamento") Medicamento medicamento);
}
