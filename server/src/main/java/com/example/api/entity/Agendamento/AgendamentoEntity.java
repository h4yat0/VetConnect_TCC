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
    @JoinColumn(name = "id_cliente")
    private Long idCliente;
    @ManyToOne
    @JoinColumn(name = "id_animal")
    private Long idAnimal;
    @ManyToOne
    @JoinColumn(name = "id_servico")
    private Long idServico;
    @Column(nullable = false)
    private String dataAgendada;
    @Column(nullable = false)
    private String horaAgendada;
    @Column(nullable = false)
    private String unidade;
    @Column(nullable = false)
    private String valorAgendado;


    @OneToOne(mappedBy = "agendamento", cascade = CascadeType.PERSIST)
    private FichaServicoEntity fichaServico;
}
