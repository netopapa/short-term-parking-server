package com.parking.rest.services;

import com.parking.exception.BOException;
import com.parking.persistence.model.Farmacia;
import com.parking.persistence.model.Medicamento;
import com.parking.persistence.repositories.FarmaciaRepository;
import com.parking.persistence.repositories.MedicamentoRepository;
import com.parking.rest.dtos.FarmaciaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FarmaciaService {

    @Autowired
    private FarmaciaRepository repository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public FarmaciaDto save(FarmaciaDto dto) {
        Farmacia model = new Farmacia(dto);

        return new FarmaciaDto(this.repository.save(model));
    }

    public FarmaciaDto findOne(Long id) {
        Farmacia model = this.repository.getOne(id);

        if (model == null) {
            throw new BOException("Farmacia inexistente.", new Throwable("receita.notfound"));
        }

        return new FarmaciaDto(model);
    }

    public Collection<FarmaciaDto> findByMedicamentosContaining(String nomeMedicamento) {
        Collection<Medicamento> medicamentos = this.medicamentoRepository.findByNomeLike(nomeMedicamento);

        if (medicamentos.size() < 1) {
            throw new BOException("Medicamento não presente.", new Throwable("medicamento.notfound"));
        }

        Collection<Farmacia> farmacias = new ArrayList<>();

        medicamentos.forEach(med -> {
            farmacias.addAll(this.repository.findByMedicamentosContaining(med));
        });

        return farmacias.stream().map(farm -> new FarmaciaDto(farm)).collect(Collectors.toList());
    }

    public Collection<FarmaciaDto> findAll() {
        Collection<Farmacia> farmacias = this.repository.findAll();

        return farmacias.stream().map(farm -> new FarmaciaDto(farm)).collect(Collectors.toList());
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

}