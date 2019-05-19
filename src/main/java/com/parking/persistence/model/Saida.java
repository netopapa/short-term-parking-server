package com.parking.persistence.model;

import com.parking.persistence.model.base.BaseModel;
import com.parking.rest.dtos.SaidaDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "saida")
public class Saida extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Medicamento medicamento;

    @Column(name = "data_saida")
    @CreationTimestamp
    private Date dataSaida;

    @Column(name = "quantidade")
    private int quantidade;

    public Saida() {
    }

    public Saida(SaidaDto dto) {
        if (dto.getId() != null) {
            super.setId(dto.getId());
        }

        this.medicamento = new Medicamento(dto.getMedicamento());
        this.quantidade = dto.getQuantidade();
        this.dataSaida = dto.getDataSaida();
    }
}
