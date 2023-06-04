package com.example.api.controller;


import com.example.api.entity.ClienteEntity;
import com.example.api.entity.form.ClienteForm;
import com.example.api.entity.form.Login;
import com.example.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URL;
@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;



    @PostMapping("/login")
    public ResponseEntity<ClienteEntity> getLogin(@RequestBody Login login){
        ClienteEntity entity = service.getClienteByEmailSenha(login.getEmail(), login.getSenha());
        return ResponseEntity.ok().body(entity);
    }

    @PostMapping("/cadastro")
    public ClienteEntity postCliente(@RequestBody ClienteForm cliente){
         return service.saveCliente(cliente);
    }

    @PutMapping("/alterar")
    public ClienteEntity putCliente(@RequestBody ClienteEntity cliente){
        return service.alteararCliente(cliente);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCliente(@PathVariable Long id){
        service.delete(id);
    }




}
