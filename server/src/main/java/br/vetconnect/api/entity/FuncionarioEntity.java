package br.vetconnect.api.entity;


import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import br.vetconnect.api.entity.UnidadeServico.AssociacaoEntity;
import br.vetconnect.api.entity.UnidadeServico.UnidadeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "funcionario")
@Entity
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_unidade")
    @JsonIgnore
    private UnidadeEntity idUnidade;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_nascimento")
    private String dataNascimento;

    @OneToMany(mappedBy = "idFuncionario", cascade = CascadeType.ALL)
    private List<AgendamentoEntity> agendamentoEntities;


    @ManyToMany(mappedBy = "funcionarioEntities")
    private List<AssociacaoEntity> associacaoEntities;


}
