package com.example.api.entity;


import com.example.api.entity.Prontuario.ProntuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_animal")
@Entity
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Long idCliente;
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

    @OneToOne(mappedBy = "animal", cascade = CascadeType.PERSIST)
    private ProntuarioEntity prontuario;
}
