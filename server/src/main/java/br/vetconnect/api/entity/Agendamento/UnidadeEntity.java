package br.vetconnect.api.entity.Agendamento;


import br.vetconnect.api.entity.imagens.UnidadeImagensEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "unidade")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // anotação adicionada aqui
@Entity
public class UnidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 14, unique = true)
    private String cnpj;
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String especialidade;
    @Column(name = "horario_funcionamento", nullable = false)
    private String horarioFuncionamento;
    @Column(nullable = false)
    private String contato;
    @Column(nullable = false, length = 8)
    private String cep;

    @Column(nullable = false)
    private String rua;
    @Column(nullable = false)
    private Integer numero;
    private String complemento;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private String cidade;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "servico_unidade", joinColumns = {@JoinColumn (name = "unidade_id")},
        inverseJoinColumns = {@JoinColumn (name = "servico_id")})
    private List<ServicoEntity> servicos;

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<UnidadeImagensEntity> imagens;


}
