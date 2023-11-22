package br.vetconnect.api.controller.Prontuario;


import br.vetconnect.api.entity.Prontuario.ProntuarioEntity;
import br.vetconnect.api.form.Cliente.ClienteFormReturn;
import br.vetconnect.api.form.Prontuario.ProntuarioForm;
import br.vetconnect.api.service.Prontuario.ProntuarioService;
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
@RequestMapping("api/prontuario")
@Tag(name = "Prontuario", description = "end points relacionados ao prontuario")
@SecurityRequirement(name = "bearerAuth")
public class ProntuarioController {

    @Autowired
    private ProntuarioService service;

    @PostMapping("v1/cadastro")
    @Operation(summary = "endPoint para cadastrar um prontuario", description = "endPoint para cadastrar um prontuario",
            tags = {"Prontuario"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProntuarioForm.class))
            }),
            @ApiResponse(description = "Não foi possivel cadastrar esse prontuario", responseCode = "400", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> salvarProntuario(@RequestBody ProntuarioForm form){
        ProntuarioForm prontuarioReturn =  service.salvarProntuario(form);
        if(prontuarioReturn == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Não foi possivel cadastrar esse prontuario"));
        }else {
            return ResponseEntity.ok().body(prontuarioReturn);
        }
    }

    @GetMapping("v1/buscar/{id}")
    @Operation(summary = "endPoint para buscar um prontuario a partir do id do animal", description = "endPoint para buscar um prontuario do id do animal",
            tags = {"Prontuario"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProntuarioForm.class))
            }),
            @ApiResponse(description = "Não foi possivel encontrar esse prontuario", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> buscarProntuario(@PathVariable Long id){
        ProntuarioForm form = service.buscarPronatuario(id);
        if(form == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Não foi possivel encontrar esse prontuario"));
        }else{
            return ResponseEntity.ok().body(form);
        }
    }

    @Operation(summary = "endPoint para deletar um prontuario a partir do id do animal", description = "endPoint para deletar um prontuario do id do animal",
            tags = {"Prontuario"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProntuarioForm.class))
            }),
            @ApiResponse(description = "Não foi possivel deletar esse prontuario", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @DeleteMapping("v1/delete/{id}")
    public ResponseEntity<?> deleteProntuario(@PathVariable Long id){
        service.deleteProntuario(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "endPoint para alterar um prontuario", description = "endPoint para alterar um prontuario, variavel id é a relacionado ao id do prontuario",
            tags = {"Prontuario"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProntuarioForm.class))
            }),
            @ApiResponse(description = "Não foi possivel alterar esse prontuario", responseCode = "400", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @PutMapping("v1/update/{id}")
    public  ResponseEntity<?> alterarPontuario(@RequestBody ProntuarioForm form, @PathVariable Long id){
        ProntuarioForm formReturn = service.alterarProntuario(form, id);
        if(formReturn == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Não foi possivel alterar esse prontuario"));
        }else {
            return ResponseEntity.ok().body(formReturn);
        }
    }


}
