package com.parking.persistence.repositories;

import com.parking.persistence.model.Lote;
import com.parking.persistence.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface LoteRepository extends JpaRepository<Lote, Long> {
    Collection<Lote> findAllByMedicamento(Medicamento medicamento);
}
