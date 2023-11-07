package br.vetconnect.api.controller.Email;


import br.vetconnect.api.form.Email;
import br.vetconnect.api.service.Email.EmailSenhaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/email")
@Tag(name = "Email", description = "end points para envio de emails")
public class EmailSenhaController {


    @Autowired
    private EmailSenhaService service;


    @PostMapping("v1/teste/{email}")
    public ResponseEntity<?> eviarEmail(@PathVariable String email) throws MessagingException {
          return  service.enviarEmail(email);
    }
}
