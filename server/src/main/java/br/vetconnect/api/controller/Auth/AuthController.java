package br.vetconnect.api.controller.Auth;


import br.vetconnect.api.form.Cliente.ClienteFormReturn;
import br.vetconnect.api.form.Login;
import br.vetconnect.api.form.Token;
import br.vetconnect.api.service.security.AuthService;
import exceptions.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Tag(name = "EndPoint de autenticação")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService service;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "endPoint para resgatar o token", description = "endPoint para resgatar o token",
            tags = {"EndPoint de autenticação"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Token.class)))
            }),
            @ApiResponse(description = "Credenciais Invalidas", responseCode = "403", content = @Content),
    })
    @PostMapping(value = "/signin")
    public ResponseEntity signIn(@RequestBody Login login){

        if(verificaEmailSenhaNulosOuVazios(login)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Credenciais Invalidas"));
        }else{
            var token = service.signIn(login);
            if (token == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Credenciais Invalidas"));
            }else{
                return  token;
            }
        }

    }


    @SuppressWarnings("rawtypes")
    @Operation(summary = "endPoint para resgatar o token, a partir do refreshToken", description = "endPoint para resgatar o token, a partir do refreshToken",
            tags = {"EndPoint de autenticação"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Token.class)))
            }),
            @ApiResponse(description = "Credenciais Invalidas", responseCode = "403", content = @Content),
    })
    @PutMapping(value = "/refresh/{email}")
    public ResponseEntity refreshToken(@PathVariable String email, @RequestHeader("Authorization") String refreshToken){

        if(verificaEmailRefreshTokenNulosOuVazios(email, refreshToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Credenciais Invalidas"));
        }else{
            var token = service.refreshToken(email, refreshToken);
            if (token == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Credenciais Invalidas"));
            }else{
                return  token;
            }
        }

    }



    public boolean verificaEmailRefreshTokenNulosOuVazios(String email, String refreshToken){
        return email == null || refreshToken == null || email.isBlank() || refreshToken.isBlank();
    }

    public boolean verificaEmailSenhaNulosOuVazios(Login login){
        return  login == null  || login.getEmail() == null || login.getEmail().isBlank()
                || login.getSenha() == null || login.getSenha().isBlank();
    }
}
