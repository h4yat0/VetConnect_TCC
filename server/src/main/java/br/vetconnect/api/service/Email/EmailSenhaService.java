package br.vetconnect.api.service.Email;

import br.vetconnect.api.service.ClienteService;
import exceptions.ExceptionResponse;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.Random;

@Service
public class EmailSenhaService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ClienteService clienteService;

    public ResponseEntity<?> enviarEmail(String destinatario) throws MessagingException {

        if(clienteService.buscarPorEmail(destinatario) == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Email não cadastrado no sistema"));
        }else{
            var mensagem = emailSender.createMimeMessage();
            String codigo = createCodigo();


            var helper = new MimeMessageHelper(mensagem, true);

            helper.setTo(destinatario);
            helper.setSubject("Código");
            helper.setText("seu codigo é " + codigo, true);


            emailSender.send(mensagem);
            return ResponseEntity.ok("Código: " + codigo);
        }



    }



    private String createCodigo(){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

}
