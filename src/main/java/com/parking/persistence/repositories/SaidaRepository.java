package com.parking.persistence.repositories;

import com.parking.persistence.model.Entrada;
import com.parking.persistence.model.Farmacia;
import com.parking.persistence.model.Medicamento;
import com.parking.persistence.model.Saida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;

public interface SaidaRepository extends JpaRepository<Saida, Long> {
    Collection<Saida> findAllByDataSaidaBetween(Date start, Date end);

    Collection<Saida> findAllByMedicamento(Medicamento medicamento);

    Collection<Entrada> findAllByFarmacia(Farmacia farmacia);
}
