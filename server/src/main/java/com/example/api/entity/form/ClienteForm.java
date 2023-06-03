package com.example.api.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteForm {

    private String nome;
    private String dataNascimento;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private String senha;

    private List<AnimalForm> animalFormList = new ArrayList<>();
}
