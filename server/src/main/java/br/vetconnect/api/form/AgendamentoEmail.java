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
}
