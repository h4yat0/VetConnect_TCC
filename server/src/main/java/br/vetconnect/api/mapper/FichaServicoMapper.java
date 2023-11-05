package br.vetconnect.api.mapper;

import br.vetconnect.api.entity.Agendamento.FichaServicoEntity;
import br.vetconnect.api.form.FichaServico.FichaServicoFormReturn;
import org.springframework.stereotype.Service;

@Service
public class FichaServicoMapper {
    public FichaServicoFormReturn entityParaFormReturn(FichaServicoEntity fichaServicoEntity) {
        FichaServicoFormReturn formReturn = new FichaServicoFormReturn();
        if(fichaServicoEntity.getObservacaoServico() != null){
            formReturn.setObservacaoServico(fichaServicoEntity.getObservacaoServico());
        }
        formReturn.setValorFichaServico(fichaServicoEntity.getValorFichaServico());
        formReturn.setIdAgendamento(fichaServicoEntity.getIdAgendamento().getId());
        formReturn.setServicoRealizado(fichaServicoEntity.getServicoRealizado());
        formReturn.setNomeFuncionario(fichaServicoEntity.getNomeFuncionario().getNome());
        return formReturn;
    }
}
