package br.vetconnect.api.form;

import lombok.Data;

@Data
public class FilaDeEsperaFormCreate {
    private Long idAgendamento;
    private String horaDesejada;
    private String dataDesejada;

}
