package com.example.api.entity.Prontuario;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_item_prontuario")
@Entity
public class ItemProntuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_prontuario")
    @JsonIgnore
    private ProntuarioEntity idProntuario;
    @Column(nullable = false)
    private String data;
    @Column(nullable = false)
    private String veterinario;
    private String sintomas;
    @Column(name = "exames_solicitados")
    private String examesSolicitados;
    private String diagnostico;
    private String prescricao;
    private String observacoes;

}
