package com.parking.persistence.repositories;

import com.parking.persistence.model.Entrada;
import com.parking.persistence.model.Farmacia;
import com.parking.persistence.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {
    List<Entrada> findAllByDataEntradaBetween(Date start, Date end);

    Collection<Entrada> findAllByMedicamento(Medicamento medicamento);

    Collection<Entrada> findAllByFarmacia(Farmacia farmacia);


}
