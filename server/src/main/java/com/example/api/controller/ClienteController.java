package com.example.api.controller;


import com.example.api.entity.ClienteEntity;
import com.example.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/login/{email}/{senha}")
    public ClienteEntity getLogin(@PathVariable String email, @PathVariable String senha){
        return service.getClienteByEmailSenha(email, senha);
    }

    @PostMapping("/cadastro")
    public ClienteEntity postCliente(@RequestBody ClienteEntity cliente){
        return service.saveCliente(cliente);
    }


}
