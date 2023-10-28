package br.vetconnect.api.mapper.Servico;


import br.vetconnect.api.entity.Agendamento.ServicoEntity;
import br.vetconnect.api.entity.Agendamento.UnidadeEntity;
import br.vetconnect.api.form.servico.ServicoFormCreate;
import br.vetconnect.api.form.servico.ServicoFormReturn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicoMapper {

    public List<ServicoFormReturn> entityParaFormReturn(List<ServicoEntity> entityList) {
        List<ServicoFormReturn> formReturnList = new ArrayList<>();
        if(entityList == null || entityList.isEmpty()){
            return null;
        }else{
            for(ServicoEntity servico: entityList){
                ServicoFormReturn formReturn = new ServicoFormReturn();
                formReturn.setPreco(servico.getPreco());
                formReturn.setId(servico.getId());
                formReturn.setNome(servico.getNome());


                List<Long> idUnidade = new ArrayList<>();
                for(UnidadeEntity unidade : servico.getUnidades()){
                    idUnidade.add(unidade.getId());
                }
                formReturn.setUnidades(idUnidade);

                formReturnList.add(formReturn);
            }
            return formReturnList;
        }




    }

    public List<ServicoFormReturn> formCreateToEntitysSemUnidades(List<ServicoEntity> entityList) {
        List<ServicoFormReturn> formReturnList = new ArrayList<>();

        if(entityList == null || entityList.isEmpty()){
            return null;
        }else{
            for(ServicoEntity servico: entityList) {
                ServicoFormReturn formReturn = new ServicoFormReturn();
                formReturn.setPreco(servico.getPreco());
                formReturn.setId(servico.getId());
                formReturn.setNome(servico.getNome());
                formReturnList.add(formReturn);
            }
            return formReturnList;
        }

    }

    public ServicoEntity formCreateToEntitysSemUnidades(ServicoFormCreate servico){
        ServicoEntity entity = new ServicoEntity();
        entity.setNome(servico.getNome());
        entity.setPreco(servico.getPreco());
        return entity;
    }
}
