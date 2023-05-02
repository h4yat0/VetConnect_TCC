package com.example.api.controller.Agendamento;


import com.example.api.service.Agendamento.FichaServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fichaServico")
public class FichaServicoController {

    @Autowired
    private FichaServicoService service;
}
