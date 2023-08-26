package br.vetconnect.api.entity.Agendamento;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "unidade")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "servicos"}) // anotação adicionada aqui
@Entity
public class UnidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String localizacao;
    private String especialidade;
    @Column(name = "horario_funcionamento")
    private String horarioFuncionamento;
    private String contato;

    @ManyToMany(mappedBy = "unidades")
    private List<ServicoEntity> servicos;


}
