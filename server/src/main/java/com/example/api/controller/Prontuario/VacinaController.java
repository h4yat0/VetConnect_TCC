package com.example.api.controller.Prontuario;

import com.example.api.service.Prontuario.VacinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacina")
public class VacinaController {

    @Autowired
    private VacinaService service;
}
