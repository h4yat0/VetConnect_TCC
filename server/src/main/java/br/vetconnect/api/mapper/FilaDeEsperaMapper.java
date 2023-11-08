package br.vetconnect.api.mapper;

import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import br.vetconnect.api.entity.Agendamento.FilaEsperaEntity;
import br.vetconnect.api.form.FilaDeEsperaFormCreate;
import br.vetconnect.api.form.FilaDeEsperaFormReturn;
import org.springframework.stereotype.Service;

@Service
public class FilaDeEsperaMapper {
    public FilaEsperaEntity formCreateParaEntity(FilaDeEsperaFormCreate formCreate, AgendamentoEntity agendamentoEntity) {
        FilaEsperaEntity entity = new FilaEsperaEntity();
        entity.setAtivo(true);
        entity.setDataDesejada(formCreate.getDataDesejada());
        entity.setHorarioDesejavel(formCreate.getHoraDesejada());
        entity.setIdAgendamento(agendamentoEntity);
        return entity;

    }

    public FilaDeEsperaFormReturn entityParaFormReturn(FilaEsperaEntity entity) {
        FilaDeEsperaFormReturn formReturn = new FilaDeEsperaFormReturn();
        formReturn.setDataDesejada(entity.getDataDesejada());
        formReturn.setHoraDesejada(entity.getDataDesejada());
        formReturn.setId(entity.getId());
        formReturn.setIdAgendamento(entity.getId());
        return formReturn;
    }
}
