package com.example.api.form.Cliente;

import com.example.api.form.Animal.AnimalFormCreate;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({"id", "nome", "cpf", "dataNascimento", "endereco", "telefone", "email", "senha"})
public class ClienteFormReturn extends RepresentationModel<ClienteFormReturn> implements Serializable {

    private Long id;
    private String nome;
    private String dataNascimento;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private String senha;

    private List<AnimalFormCreate> animalFormCreateList = new ArrayList<>();
}
