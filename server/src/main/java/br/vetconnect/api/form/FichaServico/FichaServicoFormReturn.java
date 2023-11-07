package br.vetconnect.api.form.FichaServico;

import br.vetconnect.api.form.Animal.AnimalFormReturn;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class FichaServicoFormReturn extends RepresentationModel<FichaServicoFormReturn> implements Serializable {
    private Long idAgendamento;
    private String observacaoServico;
    private BigDecimal valorFichaServico;
    private String servicoRealizado;
    private String nomeFuncionario;
}
