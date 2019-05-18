package com.parking.persistence.model;

import com.parking.persistence.model.base.BaseModel;
import com.parking.rest.dtos.PrescricaoDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "prescricao")
public class Prescricao extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "obs")
    private String obs;

    public Prescricao() {
    }

    public Prescricao(PrescricaoDto dto) {
        if (dto.getId() != null) {
            super.setId(dto.getId());
        }

        this.nome = dto.getNome();
        this.quantidade = dto.getQuantidade();
        this.obs = dto.getObs();
    }
}
