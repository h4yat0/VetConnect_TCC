package br.vetconnect.api.service.Agendamento;


import br.vetconnect.api.controller.Agendamento.FilaDeEsperaController;
import br.vetconnect.api.controller.ClienteController;
import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import br.vetconnect.api.entity.Agendamento.FilaEsperaEntity;
import br.vetconnect.api.form.FilaDeEsperaFormCreate;
import br.vetconnect.api.form.FilaDeEsperaFormReturn;
import br.vetconnect.api.mapper.FilaDeEsperaMapper;
import br.vetconnect.api.repository.Agendamento.FilaDeEsperaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FilaDeEsperaService {

    @Autowired
    private FilaDeEsperaRepository repository;

    @Autowired
    private FilaDeEsperaMapper mapper;

    @Autowired
    private AgendamentoService agendamentoService;


    public FilaDeEsperaFormReturn cadastraEspera(FilaDeEsperaFormCreate formCreate){
        AgendamentoEntity agendamentoEntity = agendamentoService.buscarPorId(formCreate.getIdAgendamento());
        FilaEsperaEntity entity = mapper.formCreateParaEntity(formCreate, agendamentoEntity);
        FilaDeEsperaFormReturn formReturn = mapper.entityParaFormReturn(repository.save(entity));
        formReturn.add(linkTo(methodOn(FilaDeEsperaController.class).cadastraEspera(formCreate)).withSelfRel());
        return formReturn;
    }
}
