package com.example.api.entity;


import com.example.api.entity.Agendamento.AgendamentoEntity;
import com.example.api.entity.Prontuario.ProntuarioEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "prontuario", "agendamentoEntityList"}) // anotação adicionada aqui
@Entity
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
    @Column(nullable = false)
    private String peso;
    @Column(nullable = false)
    private String tamanho;
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
