package br.vetconnect.api.entity.Agendamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fila_espera")
public class FilaEsperaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_agendamento")
    private AgendamentoEntity idAgendamento;

    @Column(name = "horario_desejado")
    private String horarioDesejavel;

    @Column(name = "data_desejada")
    private String dataDesejada;

    private Boolean ativo;


}
