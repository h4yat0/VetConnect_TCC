package br.vetconnect.api.form.Agendamento;

import br.vetconnect.api.form.Animal.AnimalFormReturn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoFormReturn extends RepresentationModel<AgendamentoFormReturn> implements Serializable {

    private String dataAgendada;
    private String horaAgendada;
    private BigDecimal valorAgendado;
    private String observacao;
    private String servico;
    private String unidade;
    private String cliente;
    private String animal;
    private Boolean cancelado;
}
