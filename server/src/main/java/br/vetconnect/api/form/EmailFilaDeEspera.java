package br.vetconnect.api.form;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class EmailFilaDeEspera {
    String dataAgendada;
    String horaAgendada;
    String nomeCliente;
    String nomeAnimal;
    String nomeServico;
    String nomeUnidade;
    String emailCliente;

    public EmailFilaDeEspera(String dataAgendada, String horaAgendada, String nomeCliente, String nomeAnimal, String nomeServico, String nomeUnidade, String emailCliente){
        this.dataAgendada = dataAgendada;
        this.horaAgendada = horaAgendada;
        this.nomeCliente = nomeCliente;
        this.nomeAnimal = nomeAnimal;
        this.nomeServico = nomeServico;
        this.nomeUnidade = nomeUnidade;
        this.emailCliente = emailCliente;
    }
}
