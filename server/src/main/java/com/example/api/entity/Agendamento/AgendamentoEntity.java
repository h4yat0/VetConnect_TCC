package com.example.api.entity.Agendamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_agendamento")
@Entity
public class AgendamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Long idCliente;
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Long idAnimal;
    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Long idServico;
    @Column(nullable = false, name = "data_agendada")
    private String dataAgendada;
    @Column(nullable = false, name = "hora_agendada")
    private String horaAgendada;
    @Column(nullable = false)
    private String unidade;
    @Column(nullable = false, name = "valor_agendado")
    private String valorAgendado;


    @OneToOne(mappedBy = "agendamento", cascade = CascadeType.PERSIST)
    private FichaServicoEntity fichaServico;
}
