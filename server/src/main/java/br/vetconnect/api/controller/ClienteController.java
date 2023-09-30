package br.vetconnect.api.controller;



import br.vetconnect.api.form.Cliente.ClienteFormCreate;
import br.vetconnect.api.form.Cliente.ClienteFormReturn;
import br.vetconnect.api.form.Login;
import br.vetconnect.api.service.ClienteService;
import br.vetconnect.api.utils.MetodosAuxiliares;
import exceptions.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("api/cliente")
@Tag(name = "Cliente", description = "end points relacionados ao cliente")
public class ClienteController extends MetodosAuxiliares {

    @Autowired
    private ClienteService service;


    @PostMapping("v1/login")
    @Operation(summary = "endPoint para buscar um cliente", description = "endPoint para buscar um cliente",
            tags = {"Cliente"}, responses = {
                @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClienteFormReturn.class)))
                    }),
                @ApiResponse(description = "Não foi possivel encontrar esse cliente", responseCode = "404", content = @Content),
                @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
            })
        public ResponseEntity<?> getLogin(@RequestBody Login login) {
        ClienteFormReturn entity = service.getClienteByEmailSenha(login);
        if (entity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Cliente não foi encontrado"));
        } else {
            return ResponseEntity.ok().body(entity);
        }
    }



    @PostMapping("v1/register")
    @Operation(summary = "endPoint para registrar um cliente", description = "endPoint para registrar um cliente",
            tags = {"Cliente"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClienteFormReturn.class)))
            }),
            @ApiResponse(description = "Não foi possivel registrar esse cliente", responseCode = "400", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> postCliente(@RequestBody @Valid ClienteFormCreate cliente) {

        ClienteFormReturn clienteFormReturn = service.saveCliente(cliente);
        if (clienteFormReturn == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Não foi possivel cadastrar esse usuario"));
        }  else {
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteFormReturn);
        }
    }

    @PutMapping("v1/update/{id}")
    @Operation(summary = "endPoint para alterar um cliente", description = "endPoint para alterar um cliente",
            tags = {"Cliente"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClienteFormReturn.class)))
            }),
            @ApiResponse(description = "Não foi possivel alterar esse cliente", responseCode = "400", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> putCliente(@PathVariable Long id, @RequestBody ClienteFormCreate cliente) {


        ClienteFormReturn clienteForm = service.alteararCliente(cliente, id);
        if(clienteForm == null ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Não foi possivel alterar esse usuario"));
        }else{
            return ResponseEntity.ok().body(clienteForm);
        }
    }

    @DeleteMapping("v1/delete/{id}")
    @Operation(summary = "endPoint para deletar um cliente", description = "endPoint para deletar um cliente",
            tags = {"Cliente"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "204"),
            @ApiResponse(description = "Não foi possivel deletar esse cliente", responseCode = "400", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
