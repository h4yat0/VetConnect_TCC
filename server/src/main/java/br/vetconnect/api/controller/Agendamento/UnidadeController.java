package br.vetconnect.api.controller.Agendamento;

import br.vetconnect.api.service.Agendamento.UnidadeService;
import br.vetconnect.api.entity.Agendamento.ServicoEntity;
import br.vetconnect.api.entity.Agendamento.UnidadeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/unidade")
public class UnidadeController {

    @Autowired
    private UnidadeService service;

    @GetMapping("/all")
    public List<UnidadeEntity> getAll(){
        return service.getAll();
    }

    @GetMapping("/buscarServico/{id}")
    public List<ServicoEntity> buscarPorId(@PathVariable Long id){
        return service.getById(id);
    }

}
