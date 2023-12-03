package br.vetconnect.api.mapper;

import br.vetconnect.api.entity.Agendamento.FilaEsperaEntity;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.entity.UnidadeServico.ServicoEntity;
import br.vetconnect.api.entity.UnidadeServico.UnidadeEntity;
import br.vetconnect.api.form.FilaDeEsperaFormCreate;
import br.vetconnect.api.form.FilaDeEsperaFormReturn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilaDeEsperaMapper {
    public FilaEsperaEntity formCreateParaEntity(FilaDeEsperaFormCreate formCreate, ClienteEntity clientByIdEntity, UnidadeEntity byId, ServicoEntity servicoEntity) {
        FilaEsperaEntity entity = new FilaEsperaEntity();
        entity.setIdCliente(clientByIdEntity);
        entity.setHoraAgendada(formCreate.getHoraDesejada());
        entity.setDataAgendada(formCreate.getDataDesejada());
        entity.setIdServico(servicoEntity);
        entity.setIdUnidade(byId);
        entity.setAtivo(true);
        return entity;

    }

    public FilaDeEsperaFormReturn entityParaFormReturn(FilaEsperaEntity entity) {
        FilaDeEsperaFormReturn formReturn = new FilaDeEsperaFormReturn();
        formReturn.setId(entity.getId());

        return formReturn;
    }

    public List<FilaDeEsperaFormReturn> entitysParaFormReturn(List<FilaEsperaEntity> filaEsperaEntities) {
        List<FilaDeEsperaFormReturn> filaDeEsperaFormReturns = new ArrayList<>();
        for(FilaEsperaEntity filaEsperaEntity : filaEsperaEntities){
            FilaDeEsperaFormReturn filaDeEsperaFormReturn = new FilaDeEsperaFormReturn();
            filaDeEsperaFormReturn.setId(filaEsperaEntity.getId());
            filaDeEsperaFormReturn.setHoraDesejada(filaEsperaEntity.getHoraAgendada());
            filaDeEsperaFormReturn.setDataDesejada(filaEsperaEntity.getDataAgendada());
            filaDeEsperaFormReturn.setIdUnidade(filaEsperaEntity.getIdUnidade().getId());
            filaDeEsperaFormReturn.setIdServico(filaEsperaEntity.getIdServico().getId());
            filaDeEsperaFormReturns.add(filaDeEsperaFormReturn);
        }
        return filaDeEsperaFormReturns;
    }
}
