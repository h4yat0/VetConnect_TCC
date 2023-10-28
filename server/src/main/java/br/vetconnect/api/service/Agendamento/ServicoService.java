package br.vetconnect.api.service.Agendamento;

import br.vetconnect.api.controller.Agendamento.ServicoController;
import br.vetconnect.api.controller.Agendamento.UnidadeController;
import br.vetconnect.api.entity.Agendamento.ServicoEntity;
import br.vetconnect.api.entity.Agendamento.UnidadeEntity;
import br.vetconnect.api.form.Unidade.UnidadeFormReturn;
import br.vetconnect.api.form.servico.ServicoFormCreate;
import br.vetconnect.api.form.servico.ServicoFormReturn;
import br.vetconnect.api.mapper.Servico.ServicoMapper;
import br.vetconnect.api.repository.Agendamento.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    @Autowired
    private ServicoMapper mapper;

    public List<ServicoFormReturn> todosServicos(){
       List<ServicoFormReturn> servicos = mapper.entityParaFormReturn(repository.findAll());
       if(servicos == null || servicos.isEmpty()){
           return null;
       }else{
           servicos.stream().forEach(a -> a.add(linkTo(methodOn(ServicoController.class).todosServicos()).withSelfRel()));
           return servicos;
       }
    }

    public List<ServicoFormReturn> somenteServicos(){
        List<ServicoFormReturn> unidades = mapper.formCreateToEntitysSemUnidades(repository.buscarServicosComUnidade());
        if(unidades == null || unidades.size() ==0){
            return null;
        }else{
            unidades.stream().forEach(a -> a.add(linkTo(methodOn(ServicoController.class).somenteServicos()).withSelfRel()));
            return unidades;
        }
    }

    public List<ServicoFormReturn> servicosUnidades(){
        List<ServicoFormReturn> unidades = mapper.entityParaFormReturn(repository.buscarServicosComUnidade());
        if(unidades == null || unidades.size() ==0){
            return null;
        }else{
            unidades.stream().forEach(a -> a.add(linkTo(methodOn(ServicoController.class).servicosUnidades()).withSelfRel()));
            return unidades;
        }
    }



    public List<UnidadeEntity> getById(Long id){
        ServicoEntity entity = repository.buscarServico(id);
        return entity.getUnidades();
    }

    public ServicoFormReturn cadastraServico(ServicoFormCreate servico) {
        ServicoEntity servicoReturn = mapper.formCreateToEntitysSemUnidades(servico);
        return null;
    }
}
