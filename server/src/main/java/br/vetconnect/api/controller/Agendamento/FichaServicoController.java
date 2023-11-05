package br.vetconnect.api.controller.Agendamento;


import br.vetconnect.api.controller.ClienteController;
import br.vetconnect.api.form.FichaServico.FichaServicoFormCreate;
import br.vetconnect.api.form.FichaServico.FichaServicoFormReturn;
import br.vetconnect.api.service.Agendamento.FichaServicoService;
import exceptions.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api/ficha-servico")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Ficha Serviço", description = "end points relacionados as fichas de servico dos agendamentos")
public class FichaServicoController {

    @Autowired
    private FichaServicoService service;

    @Operation(summary = "endPoint buscar as fichas de serviço a partir do id do agendamento", description = "endPoint buscar as fichas de serviço a partir do id do agendamento",
            tags = {"Ficha Serviço"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = FichaServicoController .class))),
            @ApiResponse(description = "Não foi possivel alterar esse cliente", responseCode = "400", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @GetMapping("/v1/busca-ficha/{id}")
    public ResponseEntity<?> buscaFichaServico(@PathVariable Long id){
        FichaServicoFormReturn formReturn = service.buscaFichaServico(id);
        if(formReturn == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, ""));
        }else{
            return ResponseEntity.ok().body(formReturn);
        }
    }
}
