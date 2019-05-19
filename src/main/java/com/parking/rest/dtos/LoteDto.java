package com.parking.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parking.persistence.model.Lote;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoteDto {

    private Long id;

    @JsonProperty("medicamento")
    private MedicamentoDto medicamento;

    @JsonProperty("quantidade")
    private int quantidade;

    public LoteDto() {
    }

    public LoteDto(Lote model) {
        this.id = model.getId();
        this.medicamento = new MedicamentoDto(model.getMedicamento());
        this.quantidade = model.getQuantidade();
    }
}
