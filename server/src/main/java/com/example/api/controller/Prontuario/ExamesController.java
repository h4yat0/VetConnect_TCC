package com.example.api.controller.Prontuario;

import com.example.api.service.Prontuario.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exame")
public class ExamesController {

    @Autowired
    private ExameService service;
}
