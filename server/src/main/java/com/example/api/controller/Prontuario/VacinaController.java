package com.example.api.controller.Prontuario;

import com.example.api.entity.Prontuario.VacinaEntity;
import com.example.api.form.Prontuario.VacinaForm;
import com.example.api.service.Prontuario.VacinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/vacina")
public class VacinaController {

    @Autowired
    private VacinaService service;


    @PostMapping("/cadastro")
    public VacinaEntity salvarVacinal(@RequestBody VacinaForm form){
        return service.salvarVacina(form);
    }
}
