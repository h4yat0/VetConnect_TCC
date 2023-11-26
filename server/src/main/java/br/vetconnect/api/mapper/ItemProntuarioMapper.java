package br.vetconnect.api.mapper;

import br.vetconnect.api.entity.Prontuario.ItemProntuarioEntity;
import br.vetconnect.api.entity.Prontuario.ProntuarioEntity;
import br.vetconnect.api.form.Prontuario.ItemProntuarioForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemProntuarioMapper {


    public ItemProntuarioEntity formParaEntity(ItemProntuarioForm form, ProntuarioEntity entity) {
        ItemProntuarioEntity entity1 = new ItemProntuarioEntity();
        entity1.setIdProntuario(entity);
        entity1.setData(form.getData());
        entity1.setObservacoes(form.getObservacoes());
        entity1.setExamesSolicitados(form.getExamesSolicitados());
        entity1.setPrescricao(form.getPrescricao());
        entity1.setSintomas(form.getSintomas());
        entity1.setDiagnostico(form.getDiagnostico());
        return entity1;
    }

    public ItemProntuarioForm entityParaForm(ItemProntuarioEntity entity) {
        ItemProntuarioForm formReturn = new ItemProntuarioForm();
        formReturn.setIdProntuario(entity.getIdProntuario().getId());
        formReturn.setData(entity.getData());
        formReturn.setObservacoes(entity.getObservacoes());
        formReturn.setExamesSolicitados(entity.getExamesSolicitados());
        formReturn.setPrescricao(entity.getPrescricao());
        formReturn.setSintomas(entity.getSintomas());
        formReturn.setDiagnostico(entity.getDiagnostico());
        formReturn.setId(entity.getId());
        return formReturn;
    }

    public List<ItemProntuarioForm> entitysParaForms(List<ItemProntuarioEntity> entities) {
        List<ItemProntuarioForm> forms = new ArrayList<>();

        for(ItemProntuarioEntity entity: entities){
            ItemProntuarioForm formReturn = new ItemProntuarioForm();
            formReturn.setIdProntuario(entity.getIdProntuario().getId());
            formReturn.setData(entity.getData());
            formReturn.setObservacoes(entity.getObservacoes());
            formReturn.setExamesSolicitados(entity.getExamesSolicitados());
            formReturn.setPrescricao(entity.getPrescricao());
            formReturn.setSintomas(entity.getSintomas());
            formReturn.setDiagnostico(entity.getDiagnostico());
            formReturn.setId(entity.getId());

            forms.add(formReturn);
        }
        return forms;
    }

    public ItemProntuarioEntity formParaEntity(ItemProntuarioForm form, ProntuarioEntity entity, Long id) {
        ItemProntuarioEntity entity1 = new ItemProntuarioEntity();
        entity1.setIdProntuario(entity);
        entity1.setData(form.getData());
        entity1.setObservacoes(form.getObservacoes());
        entity1.setExamesSolicitados(form.getExamesSolicitados());
        entity1.setPrescricao(form.getPrescricao());
        entity1.setSintomas(form.getSintomas());
        entity1.setDiagnostico(form.getDiagnostico());
        entity1.setId(id);
        return entity1;
    }
}
