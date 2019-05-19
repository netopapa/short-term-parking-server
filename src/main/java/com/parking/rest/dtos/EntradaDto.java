package com.parking.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parking.persistence.model.Entrada;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class EntradaDto {

    private Long id;

    @NotNull(message = "Medicamento não pode estar vazio.")
    @JsonProperty("medicamento")
    private MedicamentoDto medicamento;

    @JsonProperty("data")
    private Date dataEntrada;

    @NotNull(message = "Quantidade deve ser informada")
    @JsonProperty("quantidade")
    private int quantidade;

    public EntradaDto() {
    }

    public EntradaDto(Entrada model) {
        this.id = model.getId();
        this.medicamento = new MedicamentoDto(model.getMedicamento());
        this.dataEntrada = model.getDataEntrada();
        this.quantidade = model.getQuantidade();
    }
}
