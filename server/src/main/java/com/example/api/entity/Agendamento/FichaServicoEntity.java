package com.example.api.entity.Agendamento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_ficha_servico")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FichaServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_agendamento")
    private AgendamentoEntity idAgendamento;



    @Column( name = "observacao_servico")
    private String observacaoServico;
    @Column(nullable = false, name = "valor_ficha_servico")
    private String valorFichaServico;
    @Column(nullable = false, name = "servico_realizado")
    private String servicoRealizado;
    @Column(nullable = false, name = "nome_funcionario")
    private String nomeFuncionario;
}
