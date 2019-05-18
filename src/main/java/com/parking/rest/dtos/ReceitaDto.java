package com.parking.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parking.persistence.model.Receita;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReceitaDto {
    private Long id;

    private String sus;

    @NotNull(message = "Nome do paciente não pode estar vazio.")
    @JsonProperty("nome_paciente")
    private String nome;

    @NotNull(message = "CPF do paciente não pode estar vazio.")
    @JsonProperty("cpf_paciente")
    private String cpf;

    @JsonProperty("endereco")
    private String endereco;

    @JsonProperty("prescricoes")
    private List<PrescricaoDto> prescricoes;

    @JsonProperty("dateCreate")
    private Date dateCreate;

    @JsonProperty("entregue")
    private Boolean entregue;

    public ReceitaDto() {
    }

    public ReceitaDto(Receita model) {
        this.id = model.getId();
        this.sus = model.getSus();
        this.nome = model.getNome();
        this.cpf = model.getCpf();
        this.endereco = model.getEndereco();
        this.dateCreate = model.getDateCreate();
        this.entregue = model.getEntregue();
        this.prescricoes = model.getPrescricoes().stream().map(pres -> new PrescricaoDto(pres)).collect(Collectors.toList());
    }

}
