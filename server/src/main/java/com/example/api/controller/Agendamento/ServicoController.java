package com.example.api.controller.Agendamento;


import com.example.api.entity.Agendamento.ServicoEntity;
import com.example.api.entity.Agendamento.UnidadeEntity;
import com.example.api.service.Agendamento.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @GetMapping("/all")
    public List<ServicoEntity> getAll(){
        return service.getAll();
    }

    @GetMapping("/buscarUnidade/{id}")
    public List<UnidadeEntity> buscarPorId(@PathVariable Long id){
        return service.getById(id);
    }

}
