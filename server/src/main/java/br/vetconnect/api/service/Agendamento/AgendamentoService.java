package br.vetconnect.api.service.Agendamento;

import br.vetconnect.api.controller.Agendamento.AgendamentoController;
import br.vetconnect.api.controller.AnimalController;
import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import br.vetconnect.api.entity.Agendamento.ServicoEntity;
import br.vetconnect.api.entity.Agendamento.UnidadeEntity;
import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.form.Agendamento.AgendamentoFormCreate;
import br.vetconnect.api.form.Agendamento.AgendamentoFormReturn;
import br.vetconnect.api.mapper.AgendamentoMapper;
import br.vetconnect.api.repository.Agendamento.AgendamentoRepository;
import br.vetconnect.api.repository.Agendamento.ServicoRepository;
import br.vetconnect.api.repository.Agendamento.UnidadeRepository;
import br.vetconnect.api.repository.AnimalRepository;
import br.vetconnect.api.repository.ClienteRepository;
import br.vetconnect.api.service.AssociacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private AgendamentoMapper mapper;

    @Autowired
    private AssociacaoService associacaoService;


    public AgendamentoFormReturn cadastrarAgendamento(AgendamentoFormCreate form, String token){
        UnidadeEntity unidadeEntity = unidadeRepository.buscarUnidade(form.getIdUnidade());
        AnimalEntity animalEntity = animalRepository.buscarAnimal(form.getIdAnimal());
        ClienteEntity clienteEntity = clienteRepository.buscarPorId(form.getIdCliente());
        ServicoEntity servicoEntity = servicoRepository.buscarServico(form.getIdServico());

        AgendamentoEntity resultado = repository.verificaDataHora(form.getDataAgendada(), form.getHoraAgendada(), form.getIdServico(), form.getIdUnidade());

        if(resultado != null && unidadeEntity.getServicos().contains(servicoEntity)){
            return null;
        }else{
            AgendamentoEntity entity = mapper.formCreateParaEntity(form, animalEntity, clienteEntity,unidadeEntity,servicoEntity);

            AgendamentoFormReturn formReturn = mapper.entityParaFormReturn(repository.save(entity));
            formReturn.add(linkTo(methodOn(AgendamentoController.class).cadastroAgendamento(form, token)).withSelfRel());

            return formReturn;
        }
    }

    public AgendamentoFormReturn editarAgendamento(AgendamentoFormCreate form, String token, Long id){
        UnidadeEntity unidadeEntity = unidadeRepository.buscarUnidade(form.getIdUnidade());
        AnimalEntity animalEntity = animalRepository.buscarAnimal(form.getIdAnimal());
        ClienteEntity clienteEntity = clienteRepository.buscarPorId(form.getIdCliente());
        ServicoEntity servicoEntity = servicoRepository.buscarServico(form.getIdServico());

        if(repository.verificaIdDataHora(form.getDataAgendada(), form.getHoraAgendada(), id) != null && unidadeEntity.getServicos().contains(servicoEntity)){
            return null;
        }else{
            AgendamentoEntity entity = mapper.formCreateParaEntityEdit(form, animalEntity, clienteEntity,unidadeEntity,servicoEntity, id);

            AgendamentoFormReturn formReturn = mapper.entityParaFormReturn(repository.save(entity));
            formReturn.add(linkTo(methodOn(AgendamentoController.class).editarAgendamento(form, token, id)).withSelfRel());

            return formReturn;
        }
    }

    public List<AgendamentoFormReturn> buscarPorIdCliente(Long id,String token) {
        List<AgendamentoFormReturn> formReturnList = mapper.entitysParaFormsReturs(repository.buscarAgendamentos(id));

        if(formReturnList != null && formReturnList.size()>0 ){
            formReturnList.stream().forEach(a -> a.add(linkTo(methodOn(AgendamentoController.class).buscarPorIdCliente(id, token)).withSelfRel()));
        }

        return formReturnList;
    }

    public List<AgendamentoFormReturn> buscarPorIdAnimal(Long id, String token) {
        List<AgendamentoFormReturn> formReturnList = mapper.entitysParaFormsReturs(repository.buscarAgenamentoAnimal(id));

        if(formReturnList != null && formReturnList.size()>0 ){
            formReturnList.stream().forEach(a -> a.add(linkTo(methodOn(AgendamentoController.class).buscarPorIdAnimal(id, token)).withSelfRel()));
        }
        return formReturnList;
    }

    public void cancelarAgendamento(Long id) {
        AgendamentoEntity entity = repository.buscarAgendamento(id);
        entity.setCancelado(true);
        repository.save(entity);
    }
}
