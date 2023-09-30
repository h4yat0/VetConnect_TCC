package br.vetconnect.api.entity.imagens;


import br.vetconnect.api.entity.Agendamento.UnidadeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "img_unidade")
@Data
public class UnidadeImagensEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_unidade")
    @JsonIgnore
    private UnidadeEntity unidade;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagem;
}
