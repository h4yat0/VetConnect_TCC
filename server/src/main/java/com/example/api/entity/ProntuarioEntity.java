package com.example.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_prontuario")
@Entity
public class ProntuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_animal")
    private Long idAnimal;
    private String dataAbertura;
    private String enfermidade;
    private String alergia;
    private String medicamento;

    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.PERSIST) //excluir todos os animais desse cliente não faz ele ser apagado
    private List<VacinaEntity> vacinas = new ArrayList<>();

    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.PERSIST) //excluir todos os animais desse cliente não faz ele ser apagado
    private List<ExameEntity> exames = new ArrayList<>();


}
