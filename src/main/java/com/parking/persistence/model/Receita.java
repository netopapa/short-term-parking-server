package com.parking.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parking.persistence.model.base.BaseModel;
import com.parking.rest.dtos.ReceitaDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "receita")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Receita extends BaseModel implements Serializable {

    private static final long serialVersionUID = 575436956203506212L;

    @Column(name = "sus")
    private String sus;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "endereco")
    private String endereco;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Prescricao> prescricoes;

    @Column(name = "dateCreate")
    @CreationTimestamp
    private Date dateCreate;

    @Column(name = "entregue")
    private Boolean entregue = false;

    public Receita() {
    }

    public Receita(ReceitaDto dto) {
        if (dto.getId() != null) {
            super.setId(dto.getId());
        }

        this.sus = dto.getSus();
        this.nome = dto.getNome();
        this.endereco = dto.getEndereco();
        this.cpf = dto.getCpf();
        this.dateCreate = dto.getDateCreate();
        this.entregue = dto.getEntregue();
        this.prescricoes = dto.getPrescricoes().stream().map(pres -> new Prescricao(pres)).collect(Collectors.toList());
    }

}
