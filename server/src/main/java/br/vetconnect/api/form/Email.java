package br.vetconnect.api.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String destinatario;
    private String assunto;
    private String menssagem;
}
