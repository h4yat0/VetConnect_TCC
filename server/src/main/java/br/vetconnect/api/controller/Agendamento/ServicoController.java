package br.vetconnect.api.controller.Agendamento;


import br.vetconnect.api.entity.Agendamento.ServicoEntity;
import br.vetconnect.api.entity.Agendamento.UnidadeEntity;
import br.vetconnect.api.service.Agendamento.ServicoService;
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
