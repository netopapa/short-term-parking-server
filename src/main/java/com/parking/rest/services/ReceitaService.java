package com.parking.rest.services;

import com.parking.exception.BOException;
import com.parking.persistence.model.Farmacia;
import com.parking.persistence.repositories.ReceitaRepository;
import com.parking.rest.dtos.FarmaciaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository repository;

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

    public Collection<FarmaciaDto> findByCpfOrSus(String filter) {
        Collection<Farmacia> farmacias = this.repository.findAllByCpfOrSus(filter, filter);
        Collection<FarmaciaDto> receitasDto = new ArrayList<>();

        farmacias.forEach((item) -> {
            receitasDto.add(new FarmaciaDto(item));
        });

        return receitasDto;
    }

    public Collection<FarmaciaDto> findAll() {
        Collection<Farmacia> farmacias = this.repository.findAll();
        Collection<FarmaciaDto> receitasDto = new ArrayList<>();

        farmacias.forEach((item) -> {
            receitasDto.add(new FarmaciaDto(item));
        });

        return receitasDto;
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

}