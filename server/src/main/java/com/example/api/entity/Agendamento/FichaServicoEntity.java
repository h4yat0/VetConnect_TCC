package com.example.api.entity.Agendamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_ficha_servico")
@Entity
public class FichaServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_agendamento")
    private Long idAgendamento;
    @Column(nullable = false)
    private String observacaoServico;
    @Column(nullable = false)
    private String valorFcihaServico;
    @Column(nullable = false)
    private String servicoRealizado;
    @Column(nullable = false)
    private String nomeFuncionario;
}
