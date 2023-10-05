package br.vetconnect.api.form.Cliente;

import br.vetconnect.api.form.Animal.AnimalFormCreate;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
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
    private String telefone;
    @Email
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String senha;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imagem;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    private String numero;
    private String cep;

    private List<AnimalFormCreate> animalFormCreateList = new ArrayList<>();
}
