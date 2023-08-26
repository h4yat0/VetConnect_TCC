package br.vetconnect.api.entity.Agendamento;

import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.ClienteEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fichaServico"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agendamento")
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

    @Column(nullable = false, name = "valor_agendado", precision = 10, scale = 2)
    private BigDecimal valorAgendado;


    @OneToOne(mappedBy = "idAgendamento",cascade = CascadeType.ALL)
    private FichaServicoEntity fichaServico;
}
