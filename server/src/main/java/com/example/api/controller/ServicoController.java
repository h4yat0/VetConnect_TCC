package com.example.api.controller;


import com.example.api.entity.Agendamento.ServicoEntity;
import com.example.api.service.Agendamento.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @GetMapping("/all")
    public List<ServicoEntity> getAll(){
        return service.getAll();
    }

}
