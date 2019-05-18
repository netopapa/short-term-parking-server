package com.parking.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parking.persistence.model.Prescricao;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PrescricaoDto {

    private Long id;


    @NotNull(message = "Nome não pode estar vazio.")
    @JsonProperty("nome")
    private String nome;

    @NotNull(message = "Quantidade não pode estar vazio.")
    @JsonProperty("quantidade")
    private int quantidade;

    @NotNull(message = "Observação não pode estar vazio.")
    @JsonProperty("obs")
    private String obs;

    public PrescricaoDto() {
    }

    public PrescricaoDto(Prescricao model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.quantidade = model.getQuantidade();
        this.obs = model.getObs();
    }
}
