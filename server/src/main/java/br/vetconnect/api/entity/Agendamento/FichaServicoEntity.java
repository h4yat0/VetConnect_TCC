package br.vetconnect.api.entity.Agendamento;

import br.vetconnect.api.entity.FuncionarioEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ficha_servico")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FichaServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_agendamento")
    private AgendamentoEntity idAgendamento;

    @OneToOne
    @JoinColumn(name = "id_funcionario")
    private FuncionarioEntity nomeFuncionario;



    @Column( name = "observacao_servico")
    private String observacaoServico;
    @Column(nullable = false, name = "valor_ficha_servico", precision = 10, scale = 2)
    private BigDecimal valorFichaServico;
    @Column(nullable = false, name = "servico_realizado")
    private String servicoRealizado;

}
