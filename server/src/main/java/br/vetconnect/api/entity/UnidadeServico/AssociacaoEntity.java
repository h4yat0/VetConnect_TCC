package br.vetconnect.api.entity.UnidadeServico;

import br.vetconnect.api.entity.FuncionarioEntity;
import jakarta.persistence.*;

import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "servico_unidade_funcionario", joinColumns = {@JoinColumn (name = "servico_unidade_id")},
            inverseJoinColumns = {@JoinColumn (name = "funcionario_id")})
    private List<FuncionarioEntity> funcionarioEntities;
}
