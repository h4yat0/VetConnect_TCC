package br.vetconnect.api.service.Agendamento;

import br.vetconnect.api.controller.Agendamento.FichaServicoController;
import br.vetconnect.api.form.FichaServico.FichaServicoFormReturn;
import br.vetconnect.api.mapper.FichaServicoMapper;
import br.vetconnect.api.repository.Agendamento.FichaServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FichaServicoService {

    @Autowired
    private FichaServicoRepository repository;

    @Autowired
    private FichaServicoMapper mapper;

    public FichaServicoFormReturn buscaFichaServico(Long id) {

        FichaServicoFormReturn formReturn = mapper.entityParaFormReturn(repository.buscaPorIdAgendamento(id));

        if(formReturn == null){
            return  null;
        }else{
            formReturn.add(linkTo(methodOn(FichaServicoController.class).buscaFichaServico(id)).withSelfRel());
            return formReturn;
        }

    }
}
