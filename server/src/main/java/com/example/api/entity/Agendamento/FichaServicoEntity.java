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
    @JoinColumn(name = "agendamento_id")
    private Long idAgendamento;
    @Column( name = "observacao_servico")
    private String observacaoServico;
    @Column(nullable = false, name = "valor_ficha_servico")
    private String valorFichaServico;
    @Column(nullable = false, name = "servico_realizado")
    private String servicoRealizado;
    @Column(nullable = false, name = "nome_funcionario")
    private String nomeFuncionario;
}
