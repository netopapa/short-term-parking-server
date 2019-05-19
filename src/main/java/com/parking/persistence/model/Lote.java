package com.parking.persistence.model;

import com.parking.persistence.model.base.BaseModel;
import com.parking.rest.dtos.LoteDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "lote")
public class Lote extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Medicamento medicamento;

    @Column(name = "quantidade")
    private int quantidade;

    public Lote() {
    }

    public Lote(LoteDto dto) {
        if (dto.getId() != null) {
            super.setId(dto.getId());
        }

        this.medicamento = new Medicamento(dto.getMedicamento());
        this.quantidade = dto.getQuantidade();
    }

}
