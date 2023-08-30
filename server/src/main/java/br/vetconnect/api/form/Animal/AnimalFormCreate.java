package br.vetconnect.api.form.Animal;

import br.vetconnect.api.form.Cliente.ClienteFormReturn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

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
}
