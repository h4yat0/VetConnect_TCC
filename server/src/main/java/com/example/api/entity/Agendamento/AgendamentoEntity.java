package com.example.api.entity.Agendamento;

import com.example.api.entity.AnimalEntity;
import com.example.api.entity.ClienteEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fichaServico"})
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
    private ClienteEntity idCliente;
    @ManyToOne
    @JoinColumn(name = "id_animal")
    private AnimalEntity idAnimal;
    @ManyToOne
    @JoinColumn(name = "id_servico")
    private ServicoEntity idServico;
    @ManyToOne
    @JoinColumn(name = "id_unidade")
    private UnidadeEntity idUnidade;


    @Column(nullable = false, name = "data_agendada")
    private String dataAgendada;
    @Column(nullable = false, name = "hora_agendada")
    private String horaAgendada;

    @Column(nullable = false, name = "valor_agendado")
    private String valorAgendado;


    @OneToOne(mappedBy = "idAgendamento",cascade = CascadeType.ALL)
    private FichaServicoEntity fichaServico;
}
