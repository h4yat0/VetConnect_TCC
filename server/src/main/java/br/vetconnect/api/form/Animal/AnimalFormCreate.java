package br.vetconnect.api.form.Animal;

import br.vetconnect.api.form.Cliente.ClienteFormReturn;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.jdi.event.StepEvent;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;


@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalFormCreate extends RepresentationModel<ClienteFormReturn> implements Serializable {


    private Long idCliente;
    @Valid
    @NotBlank
    private String nome;
    @Valid
    @NotBlank
    private String cor;
    private String raca;
    @Valid
    @NotBlank
    private String dataNascimento;
    @Valid
    @NotBlank
    private String peso;
    @Valid
    @NotBlank
    private String tamanho;
    @Valid
    @NotBlank
    private String especie;
    @Valid
    @NotBlank
    private String sexo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> imagens;

}
