package br.vetconnect.api.form.Agendamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoFormCreate {

    private Long idCliente;
    private Long idAnimal;
    private Long idServico;
    private Long idUnidade;
    private String dataAgendada;
    private String horaAgendada;
    private BigDecimal valorAgendado;
    private String observacao;

}
