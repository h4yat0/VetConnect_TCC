package com.example.api.entity.Prontuario;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_vacina")
@Entity
public class VacinaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_prontuario")
    @JsonIgnore
    private ProntuarioEntity idProntuario;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, name= "data_validade")
    private String dataValidade;
    @Column(nullable = false, name= "data_aplicacao")
    private String dataAplicacao;
    private String observacoes;
}
