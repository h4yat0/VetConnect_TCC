package br.vetconnect.api.form.Cliente;

import br.vetconnect.api.form.Animal.AnimalFormCreate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClienteFormCreate extends RepresentationModel<ClienteFormCreate> implements Serializable {

    @Valid
    @NotBlank
    private String nome;
    @Valid
    @NotBlank
    private String dataNascimento;
    @Valid
    @NotBlank
    private String cpf;
    @Valid
    @NotBlank
    private String endereco;
    @Valid
    @NotBlank
    private String telefone;
    @Email
    private String email;
    private String senha;

    private List<AnimalFormCreate> animalFormCreateList = new ArrayList<>();
}
