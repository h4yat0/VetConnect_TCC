package com.example.api.entity.form.Prontuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProntuarioForm {

    private Long idAnimal;
    private String dataAbertura;
    private String enfermidade;
    private String alergia;
    private String medicamento;
}
