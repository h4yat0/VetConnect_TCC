package br.vetconnect.api.service.Agendamento;


import br.vetconnect.api.controller.Agendamento.FilaDeEsperaController;
import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import br.vetconnect.api.entity.Agendamento.FilaEsperaEntity;
import br.vetconnect.api.form.EmailFilaDeEspera;
import br.vetconnect.api.form.FilaDeEsperaFormCreate;
import br.vetconnect.api.form.FilaDeEsperaFormReturn;
import br.vetconnect.api.mapper.FilaDeEsperaMapper;
import br.vetconnect.api.repository.Agendamento.FiilaEspera.FilaDeEsperaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import br.vetconnect.api.service.Email.EmailFilaDeEsperaService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FilaDeEsperaService {

    @Autowired
    private FilaDeEsperaRepository repository;

    @Autowired
    private FilaDeEsperaMapper mapper;

    @Lazy
    @Autowired
    private AgendamentoService agendamentoService;



    @Autowired
    private EmailFilaDeEsperaService emailService;


    public FilaDeEsperaFormReturn cadastraEspera(FilaDeEsperaFormCreate formCreate){
        AgendamentoEntity agendamentoDesejado = agendamentoService.buscarPorDataHoraServicoUnidade(formCreate.getDataDesejada(), formCreate.getHoraDesejada(), formCreate.getIdServico(), formCreate.getIdUnidade());
        AgendamentoEntity agendamentoAtual = agendamentoService.buscarPorId(formCreate.getIdAgendamento());

        FilaEsperaEntity entity1 = repository.buscaPorIdAgendamentoDesejado(agendamentoDesejado.getId());
        if(entity1 != null){
           FilaDeEsperaFormReturn formReturn =  new FilaDeEsperaFormReturn();
           formReturn.setFilaPreenchida("ja tem uma fila de espera nesse agendamento");
           return formReturn;
        }else{
            FilaEsperaEntity entity = mapper.formCreateParaEntity(agendamentoDesejado, agendamentoAtual);
            FilaDeEsperaFormReturn formReturn = mapper.entityParaFormReturn(repository.save(entity));
            formReturn.add(linkTo(methodOn(FilaDeEsperaController.class).cadastraEspera(formCreate)).withSelfRel());
            return formReturn;
        }


    }

    public void verificaFilaEspera( Long id) {
        EmailFilaDeEspera emailFilaDeEsperas = agendamentoService.criaEmail(id);
        emailService.mandaEmail(emailFilaDeEsperas);
    }
}
