package com.parking.persistence.repositories;

import com.parking.persistence.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
    // select f from farmacia as f inner join farmacia_lotes as fl on f.id = fl.farmacia_id inner join lote as l on fl.lotes_id = l.id inner join medicamento as m on l.medicamento_id = m.id where m.nome = :medicamento
    @Query(value = "SELECT id_farm as id, nome_farm as nome, endereco  FROM (SELECT * FROM (SELECT * FROM (SELECT id as id_farm,lotes_id, nome as nome_farm, endereco FROM farmacia,farmacia_lotes WHERE farmacia.id=farmacia_lotes.lotes_id) as j, lote as l WHERE j.lotes_id=l.id) as j2, medicamento as m WHERE j2.medicamento_id=m.id) as mm WHERE nome=:medicamento", nativeQuery = true)
    Collection<Farmacia> findAllByMedicamentoNative(@Param("medicamento") String medicamento);
}
