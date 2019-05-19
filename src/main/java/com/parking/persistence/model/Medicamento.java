package com.parking.persistence.model;

import com.parking.persistence.model.base.BaseModel;
import com.parking.rest.dtos.MedicamentoDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "medicamento")
public class Medicamento extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    public Medicamento() {
    }

    public Medicamento(MedicamentoDto dto) {
        if (dto.getId() != null) {
            super.setId(dto.getId());
        }

        this.nome = dto.getNome();
    }
}
