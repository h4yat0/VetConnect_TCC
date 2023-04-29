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
    @JoinColumn(name = "id_prontuario")
    private Long idProntuario;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String dataValidade;
    @Column(nullable = false)
    private String dataAplicacao;
    private String observacoes;
}
