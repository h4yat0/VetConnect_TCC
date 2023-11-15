package br.vetconnect.api.controller.Agendamento;


import br.vetconnect.api.form.FilaDeEsperaFormCreate;
import br.vetconnect.api.form.FilaDeEsperaFormReturn;
import br.vetconnect.api.service.Agendamento.FilaDeEsperaService;
import exceptions.ExceptionResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("api/fila-espera")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Agendamento", description = "end points relacionados as filas de espera dos agendamentos")
public class FilaDeEsperaController {

    @Autowired
    FilaDeEsperaService service;

    @PostMapping("/v1/cadastrar")
    public ResponseEntity<?> cadastraEspera(@RequestBody FilaDeEsperaFormCreate formCreate) {
        FilaDeEsperaFormReturn formReturn = service.cadastraEspera(formCreate);
        if(formReturn.getFilaPreenchida() != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(new Date(), HttpStatus.CONFLICT, formReturn.getFilaPreenchida()));
        }else{
            return ResponseEntity.ok().body(formReturn);
        }
    }
}
