package br.vetconnect.api.form.Cliente;

import br.vetconnect.api.form.Animal.AnimalFormCreate;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "nome", "cpf", "dataNascimento", "endereco", "telefone", "email", "senha"})
public class ClienteFormReturn extends RepresentationModel<ClienteFormReturn> implements Serializable {

    private Long id;
    private String nome;
    private String dataNascimento;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private byte[] imagem;

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    private String numero;
    private String cep;

    private List<AnimalFormCreate> animalFormCreateList = new ArrayList<>();
}
