package br.vetconnect.api.form.Prontuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacinaForm {

    private Long idProntuario;
    private String nome;
    private String dataValidade;
    private String dataAplicacao;
    private String observacoes;
}
