package br.vetconnect.api.service.Email;


import br.vetconnect.api.form.EmailFilaDeEspera;
import br.vetconnect.api.repository.Agendamento.FiilaEspera.FilaDeEsperaRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmailFilaDeEsperaService {


    @Autowired
    FilaDeEsperaRepository filaDeEsperaRepository;

    @Autowired
    private JavaMailSender emailSender;

    public void mandaEmail(EmailFilaDeEspera email){




        try {
            if(email != null){
                var mensagem = emailSender.createMimeMessage();
                var helper = new MimeMessageHelper(mensagem, true);
                helper.setTo(email.getEmailCliente());
                helper.setSubject("Desistencia");
                helper.setText("Olá, "+email.getNomeCliente() +" Estamos entrando em contato pois houve uma desistencia no agendamento das "+ email.getHoraAgendada() +
                        " no dia " + formataData(email.getDataAgendada()) +"\n"+
                        "Gostaria de realizar o agendamento de " + email.getNomeAnimal() + " para o serviço " + email.getNomeServico() + " na unidade "+email.getNomeUnidade() +"?");

                helper.setTo(email.getEmailCliente());
                emailSender.send(mensagem);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String formataData(String data){
        try {
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
            Date data1 = formatoEntrada.parse(data);
            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = formatoSaida.format(data1);
            return dataFormatada;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }
}
