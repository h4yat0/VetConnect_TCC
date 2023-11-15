package br.vetconnect.api.form;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class AgendamentoEmail {
    String nomeCliente;
    String nomeAnimal;
    String nomeServico;
    String nomeUnidade;
    String horaAgendamento;
    String ruaUnidade;
    String bairroUnidade;
    String cidadeUnidade;
    String emailCliente;
    String contatoUnidade;
    String dataAgendamento;

    public AgendamentoEmail(String nomeCliente, String nomeAnimal, String nomeServico, String nomeUnidade,
                            String horaAgendamento, String ruaUnidade, String bairroUnidade, String cidadeUnidade,
                            String emailCliente, String contatoUnidade, String dataAgendamento) {
        this.nomeCliente = nomeCliente;
        this.nomeAnimal = nomeAnimal;
        this.nomeServico = nomeServico;
        this.nomeUnidade = nomeUnidade;
        this.horaAgendamento = horaAgendamento;
        this.ruaUnidade = ruaUnidade;
        this.bairroUnidade = bairroUnidade;
        this.cidadeUnidade = cidadeUnidade;
        this.emailCliente = emailCliente;
        this.contatoUnidade = contatoUnidade;
        this.dataAgendamento = dataAgendamento;
    }
}
