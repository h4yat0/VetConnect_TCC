package br.vetconnect.api.service.Agendamento;


import br.vetconnect.api.controller.Agendamento.FilaDeEsperaController;
import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import br.vetconnect.api.entity.Agendamento.FilaEsperaEntity;
import br.vetconnect.api.form.EmailFilaDeEspera;
import br.vetconnect.api.form.FilaDeEsperaFormCreate;
import br.vetconnect.api.form.FilaDeEsperaFormReturn;
import br.vetconnect.api.mapper.FilaDeEsperaMapper;
import br.vetconnect.api.repository.Agendamento.FiilaEspera.FilaDeEsperaRepository;
import br.vetconnect.api.service.Animal.AnimalService;
import br.vetconnect.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import br.vetconnect.api.service.Email.EmailFilaDeEsperaService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FilaDeEsperaService {

    @Autowired
    private FilaDeEsperaRepository repository;

    @Autowired
    private FilaDeEsperaMapper mapper;



    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private UnidadeService unidadeService;

    @Autowired
    private ServicoService servicoService;


    @Autowired
    private EmailFilaDeEsperaService emailService;


    public FilaDeEsperaFormReturn cadastraEspera(FilaDeEsperaFormCreate formCreate){
        FilaEsperaEntity entity = mapper.formCreateParaEntity(formCreate, clienteService.getClientByIdEntity(formCreate.getIdCliente()),
                unidadeService.getById(formCreate.getIdUnidade()), servicoService.buscarPorId(formCreate.getIdServico()));
        FilaDeEsperaFormReturn formReturn = mapper.entityParaFormReturn(repository.save(entity));
        formReturn.add(linkTo(methodOn(FilaDeEsperaController.class).cadastraEspera(formCreate)).withSelfRel());
        return formReturn;



    }

    public void verificaFilaEspera(AgendamentoEntity agendamento) {
        List<FilaEsperaEntity> filaEsperaEntity = repository.buscarPorIdUnidadeIdServicoDataHora(agendamento.getIdUnidade().getId(), agendamento.getIdServico().getId(), agendamento.getDataAgendada(), agendamento.getHoraAgendada());

        for(FilaEsperaEntity filaEspera : filaEsperaEntity){

            EmailFilaDeEspera emailFilaDeEsperas = new EmailFilaDeEspera(agendamento.getDataAgendada(), agendamento.getHoraAgendada(),filaEspera.getIdCliente().getNome(),
                    agendamento.getIdServico().getNome() ,  agendamento.getIdUnidade().getNome(), filaEspera.getIdCliente().getEmail(), agendamento.getIdUnidade().getContato() );
            emailService.mandaEmail(emailFilaDeEsperas);
        }

    }
}
