package br.vetconnect.api.controller.Agendamento;

import br.vetconnect.api.form.Agendamento.AgendamentoForm;
import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import br.vetconnect.api.service.Agendamento.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping("v1/cadastro")
    public AgendamentoEntity cadastroAgendamento(@RequestBody AgendamentoForm form){
          return service.cadastrarAgendamento(form);
    }

    @GetMapping("v1/buscarAgendamentos/{id}")
    public List<AgendamentoEntity> getById(@PathVariable Long id){
        return service.buscarPorId(id);
    }
}
