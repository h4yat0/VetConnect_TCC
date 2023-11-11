package br.vetconnect.api.entity.imagens;


import br.vetconnect.api.entity.AnimalEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "img_animal")
@Data
public class AnimalImagensEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_animal")
    @JsonIgnore
    private AnimalEntity animal;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagem;
}
