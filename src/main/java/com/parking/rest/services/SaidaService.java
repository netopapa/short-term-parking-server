package com.parking.rest.services;

import com.parking.exception.BOException;
import com.parking.persistence.model.Saida;
import com.parking.persistence.repositories.SaidaRepository;
import com.parking.rest.dtos.SaidaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class SaidaService {

    @Autowired
    private SaidaRepository repository;

    public SaidaDto save(SaidaDto dto) {
        Saida model = new Saida(dto);

        return new SaidaDto(this.repository.save(model));
    }

    public SaidaDto findOne(Long id) {
        Saida model = this.repository.getOne(id);

        if (model == null) {
            throw new BOException("Saida inexistente.", new Throwable("saida.notfound"));
        }

        return new SaidaDto(model);
    }

    public Collection<SaidaDto> findBetween(Date start, Date end) {
        Collection<Saida> saida = this.repository.findAllByDataSaidaBetween(start, end);

        return saida.stream().map(sai -> new SaidaDto(sai)).collect(Collectors.toList());
    }

    public Collection<SaidaDto> findAll() {
        Collection<Saida> saida = this.repository.findAll();

        return saida.stream().map(sai -> new SaidaDto(sai)).collect(Collectors.toList());
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
