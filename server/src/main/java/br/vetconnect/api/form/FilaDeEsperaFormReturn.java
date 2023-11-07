package br.vetconnect.api.form;

import br.vetconnect.api.form.Cliente.ClienteFormReturn;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
public class FilaDeEsperaFormReturn  extends RepresentationModel<FilaDeEsperaFormReturn> implements Serializable {
    private Long id;
    private Long idAgendamento;
    private String horaDesejada;
    private String dataDesejada;

}
