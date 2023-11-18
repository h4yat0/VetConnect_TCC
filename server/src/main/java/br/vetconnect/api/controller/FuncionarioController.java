package br.vetconnect.api.controller;


import br.vetconnect.api.form.Funcionario.FuncionarioFormCreate;
import br.vetconnect.api.form.Funcionario.FuncionarioFormReturn;
import br.vetconnect.api.form.Login;
import br.vetconnect.api.service.FuncionarioService;
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

@RestController
@RequestMapping("api/funcionario")
@Tag(name = "Funcionario", description = "end points relacionados aos funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioService service;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("v1/login")
    @Operation(summary = "endPoint para buscar um funcionario", description = "endPoint para buscar um funcionario",
            tags = {"funcionario"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionarioFormReturn.class))
            }),
            @ApiResponse(description = "Não foi possivel encontrar esse funcionario", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> getLogin(@RequestBody Login login){
        FuncionarioFormReturn entity = service.getFuncionarioByEmailSenha(login);
        if (entity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Funcionario não foi encontrado"));
        } else {
            return ResponseEntity.ok().body(entity);
        }

    }


    @PostMapping("v1/cadastrar")
    @Operation(summary = "endPoint para buscar um funcionario", description = "endPoint para buscar um funcionario",
            tags = {"Funcionario"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionarioFormReturn.class))
            }),
            @ApiResponse(description = "Não foi possivel encontrar esse funcionario", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> cadastrarFuncionario(@RequestBody FuncionarioFormCreate formCreate){
        FuncionarioFormReturn formReturn = service.cadastrarFuncionario(formCreate);
        if (formReturn == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Não foi possivel cadastrar esse usuario"));
        }  else {
            return ResponseEntity.status(HttpStatus.CREATED).body(formReturn);
        }
    }

}
