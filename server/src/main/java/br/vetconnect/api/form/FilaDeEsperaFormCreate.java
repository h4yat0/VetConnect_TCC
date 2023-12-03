package br.vetconnect.api.form;

import lombok.Data;

@Data
public class FilaDeEsperaFormCreate {
    private Long idCliente;
    private String horaDesejada;
    private String dataDesejada;
    private Long idServico;
    private Long idUnidade;

}
