package com.parking.rest.services;

import com.parking.exception.BOException;
import com.parking.persistence.model.Entrada;
import com.parking.persistence.repositories.EntradaRepository;
import com.parking.rest.dtos.EntradaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository repository;

    public EntradaDto save(EntradaDto dto) {
        Entrada model = new Entrada(dto);

        return new EntradaDto(this.repository.save(model));
    }

    public EntradaDto findOne(Long id) {
        Entrada model = this.repository.getOne(id);

        if (model == null) {
            throw new BOException("Entrada inexistente.", new Throwable("entrada.notfound"));
        }

        return new EntradaDto(model);
    }

    public Collection<EntradaDto> findBetween(Date start, Date end) {
        Collection<Entrada> Entradas = this.repository.findAllByDataEntradaBetween(start, end);

        return Entradas.stream().map(entrada -> new EntradaDto(entrada)).collect(Collectors.toList());
    }

    public Collection<EntradaDto> findAll() {
        Collection<Entrada> Entradas = this.repository.findAll();

        return Entradas.stream().map(entrada -> new EntradaDto(entrada)).collect(Collectors.toList());
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
