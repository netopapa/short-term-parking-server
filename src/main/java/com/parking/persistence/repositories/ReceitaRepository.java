package com.parking.persistence.repositories;

import com.parking.persistence.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ReceitaRepository extends JpaRepository<Farmacia, Long> {
    Collection<Farmacia> findAllByCpfOrSus(String cpf, String sus);
}
