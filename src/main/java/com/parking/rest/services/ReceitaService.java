package com.parking.rest.services;

import com.parking.exception.BOException;
import com.parking.persistence.model.Receita;
import com.parking.persistence.repositories.ReceitaRepository;
import com.parking.rest.dtos.ReceitaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository repository;

    public ReceitaDto save(ReceitaDto dto) {
        Receita model = new Receita(dto);

        return new ReceitaDto(this.repository.save(model));
    }

    public ReceitaDto findOne(Long id) {
        Receita model = this.repository.getOne(id);

        if (model == null) {
            throw new BOException("Receita inexistente.", new Throwable("receita.notfound"));
        }

        return new ReceitaDto(model);
    }

    public Collection<ReceitaDto> findByCpfOrSus(String filter) {
        Collection<Receita> receitas = this.repository.findAllByCpfOrSus(filter);
        Collection<ReceitaDto> receitasDto = new ArrayList<>();

        receitas.forEach((item) -> {
            receitasDto.add(new ReceitaDto(item));
        });

        return receitasDto;
    }

    public Collection<ReceitaDto> findAll() {
        Collection<Receita> receitas = this.repository.findAll();
        Collection<ReceitaDto> receitasDto = new ArrayList<>();

        receitas.forEach((item) -> {
            receitasDto.add(new ReceitaDto(item));
        });

        return receitasDto;
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

}