package com.example.api.form.Prontuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExameForm {

    private Long idProntuario;
    private String data;
    private String nome;
    private String resultado;
    private String observacao;
}
