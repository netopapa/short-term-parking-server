package com.parking.persistence.repositories;

import com.parking.persistence.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    Collection<Medicamento> findByNomeIgnoreCaseContaining(String nome);
}
