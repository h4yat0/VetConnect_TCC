package br.vetconnect.api.service.Email;


import br.vetconnect.api.form.EmailFilaDeEspera;
import br.vetconnect.api.repository.Agendamento.FiilaEspera.FilaDeEsperaRepository;
import jakarta.mail.MessagingException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
                helper.setSubject("Fila de Espera");
                helper.setText("Olá, "+email.getNomeCliente() +" estamos entrando em contato, pois houve uma desistencia no agendamento das "+ email.getHoraAgendada() +
                        " no dia " + formataData(email.getDataAgendada()) +"\n"+
                        "Gostaria de realizar o agendamento para o serviço " + email.getNomeServico() + " na unidade "+email.getNomeUnidade() +"? \n"+
                        "Entrar em contato: " + formataNumeroTelefone(email.getContatoUnidade()));

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

    private String formataNumeroTelefone(String telefone){
        String numericPhoneNumber = StringUtils.getDigits(telefone);

        // Verificar se o número é válido
        if (NumberUtils.isParsable(numericPhoneNumber) && numericPhoneNumber.length() == 10) {
            // Formatar como (XXX) XXX-XXXX
            return String.format("(%s) %s-%s",
                    numericPhoneNumber.substring(0, 2),
                    numericPhoneNumber.substring(2, 6),
                    numericPhoneNumber.substring(6));
        } else {
            // Se o número não for válido, retornar a string original
            return telefone;
        }

    }
}
