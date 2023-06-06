package com.example.api.entity.Agendamento;


import com.example.api.entity.Prontuario.ProntuarioEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_servico")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "unidades"})
@Entity
public class ServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal preco;
    @ManyToMany
    @JoinTable(name = "servico_unidade",
            joinColumns = @JoinColumn(name = "servico_id"),
            inverseJoinColumns = @JoinColumn(name = "unidade_id"))
    private List<UnidadeEntity> unidades;


}
