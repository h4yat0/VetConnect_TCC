package br.vetconnect.api.form.FichaServico;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public class FichaServicoFormCreate {
    private Long idAgendamento;
    private String observacaoServico;
    private BigDecimal valorFichaServico;
    private String servicoRealizado;
    private String nomeFuncionario;
}
