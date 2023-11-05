package br.vetconnect.api.entity;


import br.vetconnect.api.entity.Prontuario.ProntuarioEntity;
import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "prontuario", "agendamentoEntityList"}) // anotação adicionada aqui
@Entity(name = "animal")
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonIgnore
    private ClienteEntity idCliente;
    @Column(nullable = false)
    private String cor;
    private String raca;
    @Column(nullable = false, name = "data_nascimento")
    private String dataNascimento;
    @Column(nullable = false, columnDefinition = "decimal(6,3)")
    private BigDecimal peso;
    @Column(nullable = false, columnDefinition = "decimal(6,3)")
    private BigDecimal tamanho;
    @Column(nullable = false)
    private String especie;
    @Column(nullable = false, name = "sexo", length = 1)
    @Check(constraints = "sexo IN ('S', 'N')")
    private String sexo;

    @OneToOne(mappedBy = "idAnimal", cascade = CascadeType.PERSIST)
    private ProntuarioEntity prontuario;

    @OneToMany(mappedBy = "idAnimal")
    private List<AgendamentoEntity> agendamentoEntityList = new ArrayList<>();
}
