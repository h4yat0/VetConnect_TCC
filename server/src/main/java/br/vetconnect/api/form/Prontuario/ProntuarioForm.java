package br.vetconnect.api.form.Prontuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProntuarioForm extends RepresentationModel<ProntuarioForm> implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private Long idAnimal;
    private String dataAbertura;
    private String enfermidade;
    private String alergia;
    private String medicamento;
}
