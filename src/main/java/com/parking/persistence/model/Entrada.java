package com.parking.persistence.model;

import com.parking.persistence.model.base.BaseModel;
import com.parking.rest.dtos.EntradaDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "entrada")
public class Entrada extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Medicamento medicamento;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Farmacia farmacia;

    @Column(name = "data_entrada")
    @CreationTimestamp
    private Date dataEntrada;

    @Column(name = "quantidade")
    private int quantidade;

    public Entrada() {
    }

    public Entrada(EntradaDto dto) {
        if (dto.getId() != null) {
            super.setId(dto.getId());
        }

        this.medicamento = new Medicamento(dto.getMedicamento());
        this.quantidade = dto.getQuantidade();
        this.dataEntrada = dto.getDataEntrada();
        this.farmacia = new Farmacia(dto.getFarmacia());
    }
}
