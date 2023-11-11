package br.vetconnect.api.controller.Agendamento;


import br.vetconnect.api.form.Unidade.UnidadeFormReturn;
import br.vetconnect.api.form.servico.ServicoFormCreate;
import br.vetconnect.api.form.servico.ServicoFormReturn;
import br.vetconnect.api.service.Agendamento.ServicoService;
import exceptions.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/servico")
@Tag(name = "Serviço", description = "end points relacionados aos serviços")
public class ServicoController {

    @Autowired
    private ServicoService service;


    @Operation(summary = "endPoint para buscar os serviços cadastrados no sistema", description = "endPoint para buscar os serviços cadastrados no sistema",
            tags = {"Serviço"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UnidadeFormReturn.class)))
            }),
            @ApiResponse(description = "Não tem servicos cadastradas no sistema", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @GetMapping("/v1/somente-servicos")
    public ResponseEntity<?> somenteServicos(){
        List<ServicoFormReturn> formReturn = service.somenteServicos();
        if(formReturn == null || formReturn.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Não tem serviços cadastrados no sistema"));
        }else{
            return ResponseEntity.ok().body(formReturn);
        }


    }

    @Operation(summary = "endPoint para buscar os serviços cadastrados no sistema e as unidades relaiconadas", description = "endPoint para buscar os serviços cadastrados no sistema e as unidades relacionadas",
            tags = {"Serviço"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UnidadeFormReturn.class)))
            }),
            @ApiResponse(description = "Não tem servicos cadastradas no sistema", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @GetMapping("/v1/servicos-unidades")
    public ResponseEntity<?> servicosUnidades(){
        List<ServicoFormReturn> formReturn = service.servicosUnidades();
        if(formReturn == null || formReturn.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Não tem serviços cadastrados no sistema"));
        }else{
            return ResponseEntity.ok().body(formReturn);
        }
    }

    @Operation(summary = "endPoint para buscar os serviços cadastrados no sistema mesmo sem unidades", description = "endPoint para buscar os serviços cadastrados no sistema mesmo sem unidades",
            tags = {"Serviço"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UnidadeFormReturn.class)))
            }),
            @ApiResponse(description = "Não tem servicos cadastradas no sistema", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @GetMapping("/v1/todos-servicos")
    public ResponseEntity<?> todosServicos(){
        List<ServicoFormReturn> formReturn = service.todosServicos();
        if(formReturn == null || formReturn.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Não tem serviços cadastrados no sistema"));
        }else{
            return ResponseEntity.ok().body(formReturn);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "endPoint para cadastrar um serviço no sistema", description = "endPoint para cadastrar um serviço no sistema",
            tags = {"Unidade"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadeFormReturn.class))
            }),
            @ApiResponse(description = "Ja tem uma unidade cadastrada com esse cnpj", responseCode = "409", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @PostMapping("/v1/cadastrar")
    public ResponseEntity<?> cadastraServico(@RequestBody ServicoFormCreate servico){
        ServicoFormReturn servicoReturn = service.cadastraServico(servico);
        return null;


    }



}
