package br.vetconnect.api.entity.Agendamento;

import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.entity.UnidadeServico.ServicoEntity;
import br.vetconnect.api.entity.UnidadeServico.UnidadeEntity;
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
    @JoinColumn(name = "id_cliente")
    private ClienteEntity idCliente;

    @ManyToOne
    @JoinColumn(name = "id_unidade")
    private UnidadeEntity idUnidade;

    @ManyToOne
    @JoinColumn(name = "id_servico")
    private ServicoEntity idServico;

    @Column(nullable = false, name = "data_agendada")
    private String dataAgendada;
    @Column(nullable = false, name = "hora_agendada")
    private String horaAgendada;




    @Column(columnDefinition = "boolean DEFAULT true")
    private Boolean ativo;


}
