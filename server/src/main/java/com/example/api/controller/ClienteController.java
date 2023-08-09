package com.example.api.controller;


import com.example.api.form.Cliente.ClienteFormCreate;
import com.example.api.form.Cliente.ClienteFormReturn;
import com.example.api.form.Login;
import com.example.api.service.ClienteService;
import com.example.api.utils.MetodosAuxiliares;
import exceptions.ExceptionResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteController extends MetodosAuxiliares {

    @Autowired
    private ClienteService service;


    @PostMapping("/login")
    public ResponseEntity<?> getLogin(@RequestBody Login login) {
        ClienteFormReturn entity = service.getClienteByEmailSenha(login.getEmail(), login.getSenha());
        if (entity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Cliente não foi encontrado"));
        } else {
            return ResponseEntity.ok().body(entity);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> postCliente(@RequestBody @Valid ClienteFormCreate cliente) {
//        if (service.buscarCpf(cliente.getCpf()) != null || service.buscarEmail(cliente.getEmail()) != null) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(new Date(), HttpStatus.CONFLICT, "Cpf ou email ja cadastrados"));
//        } else if (idadeUsuario(cliente.getDataNascimento()) < 18) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.CONFLICT, "Cliente com menos de 18 anos"));
//        } else if (cliente.getCpf().length() > 11) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Cpf invalido"));
//        } else {
//            ClienteFormReturn clienteFormReturn = service.saveCliente(cliente);
//            return ResponseEntity.status(HttpStatus.CREATED).body(clienteFormReturn);
//        }

        if (service.buscarCpf(cliente.getCpf()) != null || service.buscarEmail(cliente.getEmail()) != null ||
                idadeUsuario(cliente.getDataNascimento()) < 18 || cliente.getCpf().length() > 11) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Não foi possivel cadastrar esse usuario"));
        }  else {
            ClienteFormReturn clienteFormReturn = service.saveCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteFormReturn);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> putCliente(@PathVariable Long id, @RequestBody ClienteFormCreate cliente) {
        ClienteFormReturn clienteFormReturn = service.getClientById(id);
        ClienteFormReturn clienteReturnCpf = service.getClienteByCpf(cliente.getCpf());
        ClienteFormReturn clienteReturnEmail = service.getClienteByEmail(cliente.getEmail());

//        if (cliente.getCpf().length() > 11) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Cpf invalido"));
//        } else if (clienteFormReturn == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Cliente não encontrado"));
//        } else if (clienteReturnCpf != null && !clienteReturnCpf.getId().equals(id)) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(new Date(), HttpStatus.CONFLICT, "Cpf ou email ja cadastrados"));
//        } else if (clienteReturnEmail != null && !clienteReturnEmail.getId().equals(id)) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(new Date(), HttpStatus.CONFLICT, "Cpf ou email ja cadastrados"));
//        }else{
//            return ResponseEntity.ok().body(service.alteararCliente(cliente, id));
//        }

        if(cliente.getCpf().length() >11 || clienteFormReturn == null || (clienteReturnCpf != null && !clienteReturnCpf.getId().equals(id)) ||
                (clienteReturnEmail != null && clienteReturnEmail.getId().equals(id)) ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Não foi possivel alterar esse usuario"));
        }else{
            return ResponseEntity.ok().body(service.alteararCliente(cliente, id));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
