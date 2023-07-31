package com.example.api.form.Agendamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FichaServicoForm {

    private Long idAgendamento;
    private String observacaoServico;
    private String valorFichaServico;
    private String servicoRealizado;
    private String nomeFuncionario;
}
