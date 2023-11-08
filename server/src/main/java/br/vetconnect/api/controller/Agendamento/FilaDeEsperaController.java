package br.vetconnect.api.controller.Agendamento;


import br.vetconnect.api.form.FilaDeEsperaFormCreate;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/fila-espera")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Agendamento", description = "end points relacionados as filas de espera dos agendamentos")
public class FilaDeEsperaController {


    public ResponseEntity<?> cadastraEspera(FilaDeEsperaFormCreate formCreate) {
        return null;
    }
}
