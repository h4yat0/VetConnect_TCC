package com.example.api.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalForm {

    private Long idCliente;
    private String nome;
    private String cor;
    private String raca;
    private String dataNascimento;
    private String peso;
    private String tamanho;
    private String especie;
    private String sexo;
}
