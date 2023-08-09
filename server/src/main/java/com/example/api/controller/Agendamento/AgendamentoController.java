package com.example.api.controller.Agendamento;

import com.example.api.entity.Agendamento.AgendamentoEntity;
import com.example.api.form.Agendamento.AgendamentoForm;
import com.example.api.service.Agendamento.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping("/cadastro")
    public AgendamentoEntity cadastroAgendamento(@RequestBody AgendamentoForm form){
          return service.cadastrarAgendamento(form);
    }

    @GetMapping("/buscarAgendamentos/{id}")
    public List<AgendamentoEntity> getById(@PathVariable Long id){
        return service.buscarPorId(id);
    }
}
