package com.parking.rest.services;

import com.parking.exception.BOException;
import com.parking.persistence.model.Entrada;
import com.parking.persistence.repositories.EntradaRepository;
import com.parking.rest.dtos.EntradaDto;
import com.parking.rest.dtos.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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

    public MyResult findBetween(int mes, Long farm, Long med) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(Calendar.MONTH, mes);
        start.set(Calendar.DAY_OF_MONTH, start.getActualMinimum(Calendar.DATE));
        end.set(Calendar.MONTH, mes);
        end.set(Calendar.DAY_OF_MONTH, start.getActualMaximum(Calendar.DATE));

        System.out.println(start.getTime());
        System.out.println(end.getTime());

        List<Entrada> entradas = this.repository.findAllByDataEntradaBetweenAndFarmaciaIdAndMedicamentoId(start.getTime(), end.getTime(), farm, med);

        int soma = 0;

        for (int i = 0; i< entradas.size(); i++) {
            soma += entradas.get(i).getQuantidade();
        }

        MyResult result = new MyResult();
        result.setResult(soma/(entradas.size()));

        return result;
    }

    public Collection<EntradaDto> findAll() {
        Collection<Entrada> Entradas = this.repository.findAll();

        return Entradas.stream().map(entrada -> new EntradaDto(entrada)).collect(Collectors.toList());
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
