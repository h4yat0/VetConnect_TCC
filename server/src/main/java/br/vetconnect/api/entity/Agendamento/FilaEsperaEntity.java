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

    @ManyToOne
    @JoinColumn(name = "id_agendamento")
    private AgendamentoEntity idAgendamento;

    @ManyToOne
    @JoinColumn(name = "id_agendamento_desejado")
    private AgendamentoEntity idAgendamentoDesejado;

    @Column(columnDefinition = "boolean DEFAULT true")
    private Boolean ativo;


}
