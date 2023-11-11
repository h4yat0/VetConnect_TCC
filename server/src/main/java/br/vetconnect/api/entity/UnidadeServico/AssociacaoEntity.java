package br.vetconnect.api.entity.UnidadeServico;

import jakarta.persistence.*;

@Entity
@Table(name = "servico_unidade")
public class AssociacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    // Outros campos específicos desta tabela
//    private String informacaoUnica;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private ServicoEntity servico;

    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private UnidadeEntity unidade;

    @Column(name = "tempo_servico", nullable = false)
    private String tempoServico;
}
