package com.parking.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parking.persistence.model.Medicamento;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MedicamentoDto {

    private Long id;


    @NotNull(message = "Nome não pode estar vazio.")
    @JsonProperty("nome")
    private String nome;

    public MedicamentoDto() {
    }

    public MedicamentoDto(Medicamento model) {
        this.id = model.getId();
        this.nome = model.getNome();
    }
}
