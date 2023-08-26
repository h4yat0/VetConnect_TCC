package br.vetconnect.api.entity.Agendamento;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "servico")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "unidades"})
@Entity
public class ServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false,precision = 10, scale = 2)
    private BigDecimal preco;
    @ManyToMany
    @JoinTable(name = "servico_unidade",
            joinColumns = @JoinColumn(name = "servico_id"),
            inverseJoinColumns = @JoinColumn(name = "unidade_id"))
    private List<UnidadeEntity> unidades;


}
