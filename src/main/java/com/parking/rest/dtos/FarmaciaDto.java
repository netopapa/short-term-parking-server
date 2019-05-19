package com.parking.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parking.persistence.model.Farmacia;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class FarmaciaDto {
    private Long id;

    @NotNull(message = "Nome não pode estar vazio.")
    @JsonProperty("nome")
    private String nome;

    @NotNull(message = "Endereço não pode estar vazio.")
    @JsonProperty("endereco")
    private String endereco;

    @JsonProperty("lotes")
    private List<LoteDto> lotes;

    public FarmaciaDto() {
    }

    public FarmaciaDto(Farmacia model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.endereco = model.getEndereco();
        this.lotes = model.getLotes().stream().map(lote -> new LoteDto(lote)).collect(Collectors.toList());
    }

}
