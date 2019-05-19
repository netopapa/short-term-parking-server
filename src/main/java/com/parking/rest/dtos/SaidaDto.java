package com.parking.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parking.persistence.model.Saida;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class SaidaDto {

    private Long id;

    @NotNull(message = "Medicamento não pode estar vazio.")
    @JsonProperty("medicamento")
    private MedicamentoDto medicamento;

    @JsonProperty("data")
    private Date dataSaida;

    @NotNull(message = "Quantidade deve ser informada")
    @JsonProperty("quantidade")
    private int quantidade;

    public SaidaDto() {
    }

    public SaidaDto(Saida model) {
        this.id = model.getId();
        this.medicamento = new MedicamentoDto(model.getMedicamento());
        this.dataSaida = model.getDataSaida();
        this.quantidade = model.getQuantidade();
    }
}
