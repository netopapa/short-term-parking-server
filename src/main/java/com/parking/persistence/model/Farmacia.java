package com.parking.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parking.persistence.model.base.BaseModel;
import com.parking.rest.dtos.FarmaciaDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "farmacia")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Farmacia extends BaseModel implements Serializable {

    private static final long serialVersionUID = 575436956203506212L;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Lote> lotes;

    public Farmacia() {
    }

    public Farmacia(FarmaciaDto dto) {
        if (dto.getId() != null) {
            super.setId(dto.getId());
        }

        this.nome = dto.getNome();
        this.endereco = dto.getEndereco();
        this.lotes = dto.getLotes().stream().map(lote -> new Lote(lote)).collect(Collectors.toList());
    }

}
