package com.parking.persistence.repositories;

import com.parking.persistence.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    Collection<Receita> findAllByCpfOrSus(String cpf, String sus);
}
