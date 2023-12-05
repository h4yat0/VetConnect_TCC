package br.vetconnect.api.mapper;


import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import br.vetconnect.api.entity.UnidadeServico.ServicoEntity;
import br.vetconnect.api.entity.UnidadeServico.UnidadeEntity;
import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.form.Agendamento.AgendamentoFormCreate;
import br.vetconnect.api.form.Agendamento.AgendamentoFormReturn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgendamentoMapper {

    public AgendamentoEntity formCreateParaEntity(AgendamentoFormCreate form, AnimalEntity animal, ClienteEntity cliente, UnidadeEntity unidade, ServicoEntity servico){
        AgendamentoEntity entity = new AgendamentoEntity();
        entity.setIdAnimal(animal);
        entity.setIdCliente(cliente);
        entity.setIdServico(servico);
        entity.setIdUnidade(unidade);
        entity.setDataAgendada(form.getDataAgendada());
        entity.setHoraAgendada(form.getHoraAgendada());
        entity.setValorAgendado(form.getValorAgendado());
        entity.setStatus('1');
        if(form.getObservacao() !=null){
            entity.setObservacao(form.getObservacao());
        }else{
            entity.setObservacao("");
        }

        return entity;

    }

    public AgendamentoEntity formCreateParaEntityEdit(AgendamentoFormCreate form, AnimalEntity animal, ClienteEntity cliente, UnidadeEntity unidade, ServicoEntity servico, Long id){
        AgendamentoEntity entity = new AgendamentoEntity();
        entity.setId(id);
        entity.setIdAnimal(animal);
        entity.setIdCliente(cliente);
        entity.setIdServico(servico);
        entity.setIdUnidade(unidade);
        entity.setDataAgendada(form.getDataAgendada());
        entity.setHoraAgendada(form.getHoraAgendada());
        entity.setValorAgendado(form.getValorAgendado());
        if(form.getObservacao() !=null){
            entity.setObservacao(form.getObservacao());
        }else{
            entity.setObservacao("");
        }

        return entity;

    }

    public AgendamentoFormReturn entityParaFormReturn(AgendamentoEntity entity) {
        AgendamentoFormReturn formReturn = new AgendamentoFormReturn();
        formReturn.setDataAgendada(entity.getDataAgendada());
        formReturn.setObservacao(entity.getObservacao());
        formReturn.setValorAgendado(entity.getValorAgendado());
        formReturn.setHoraAgendada(entity.getHoraAgendada());
        formReturn.setCliente(entity.getIdCliente().getNome());
        formReturn.setAnimal(entity.getIdAnimal().getNome());
        formReturn.setServico(entity.getIdServico().getNome());
        formReturn.setUnidade(entity.getIdUnidade().getNome());
        formReturn.setIdAnimal(entity.getIdAnimal().getId());
        formReturn.setIdUnidade(entity.getIdUnidade().getId());
        formReturn.setIdCliente(entity.getIdCliente().getId());
        formReturn.setIdServico(entity.getIdServico().getId());
        formReturn.setStatus(entity.getStatus());
        formReturn.setId(entity.getId());
        return  formReturn;
    }

    public List<AgendamentoFormReturn> entitysParaFormsReturs(List<AgendamentoEntity> entities) {
        List<AgendamentoFormReturn> formReturnList = new ArrayList<>();

        for(AgendamentoEntity entity: entities){
            AgendamentoFormReturn formReturn = new AgendamentoFormReturn();
            formReturn.setHoraAgendada(entity.getHoraAgendada());
            formReturn.setDataAgendada(entity.getDataAgendada());
            formReturn.setValorAgendado(entity.getValorAgendado());
            if(entity.getObservacao() != null){
                formReturn.setObservacao(entity.getObservacao());
            }

            formReturn.setCliente(entity.getIdCliente().getNome());
            formReturn.setAnimal(entity.getIdAnimal().getNome());
            formReturn.setServico(entity.getIdServico().getNome());
            formReturn.setUnidade(entity.getIdUnidade().getNome());
            formReturn.setStatus(entity.getStatus());
            formReturn.setId(entity.getId());
            formReturn.setIdAnimal(entity.getIdAnimal().getId());
            formReturn.setIdUnidade(entity.getIdUnidade().getId());
            formReturn.setIdCliente(entity.getIdCliente().getId());
            formReturn.setIdServico(entity.getIdServico().getId());
            formReturnList.add(formReturn);
        }

        return formReturnList;

    }
}
