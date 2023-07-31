package com.example.api.form.Prontuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemProntuarioForm {

    private Long idProntuario;
    private String data;
    private String veterinario;
    private String sintomas;
    private String examesSolicitados;
    private String diagnostico;
    private String prescricao;
    private String observacoes;
}
