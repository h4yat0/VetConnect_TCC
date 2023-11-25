package br.vetconnect.api.entity.Prontuario;


import br.vetconnect.api.entity.AnimalEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prontuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "exames"}) // anotação adicionada aqui

@Entity
public class ProntuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_animal")
    @JsonIgnore
    private AnimalEntity idAnimal;
    @Column(nullable = false, name = "data_abertura")
    private String dataAbertura;
    private String enfermidade;
    private String alergia;
    private String medicamento;

    @OneToMany(mappedBy = "idProntuario", cascade = CascadeType.ALL) //excluir todos os animais desse cliente não faz ele ser apagado
    private List<VacinaEntity> vacinas = new ArrayList<>();

    @OneToMany(mappedBy = "idProntuario", cascade = CascadeType.ALL) //excluir todos os animais desse cliente não faz ele ser apagado
    private List<ExameEntity> exames = new ArrayList<>();

    @OneToMany(mappedBy =  "idProntuario", cascade = CascadeType.ALL)
    private  List<ItemProntuarioEntity> itemProntuario = new ArrayList<>();


}
