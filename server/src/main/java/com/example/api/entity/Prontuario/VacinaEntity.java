package com.example.api.entity.Prontuario;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_vacina")
public class VacinaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "prontuario_id")
    private Long idProntuario;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, name= "data_validade")
    private String dataValidade;
    @Column(nullable = false, name= "data_aplicacao")
    private String dataAplicacao;
    private String observacoes;
}
