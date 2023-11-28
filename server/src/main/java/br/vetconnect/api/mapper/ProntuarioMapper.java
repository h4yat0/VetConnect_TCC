package br.vetconnect.api.mapper;

import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.Prontuario.ProntuarioEntity;
import br.vetconnect.api.form.Prontuario.ProntuarioForm;
import org.springframework.stereotype.Service;

@Service
public class ProntuarioMapper {


    public ProntuarioEntity prontuarioParaEntity(ProntuarioForm prontuarioForm, AnimalEntity animal) {
        ProntuarioEntity prontuario = new ProntuarioEntity();
        prontuario.setAlergia(prontuarioForm.getAlergia());
        prontuario.setEnfermidade(prontuarioForm.getEnfermidade());
        prontuario.setDataAbertura(prontuarioForm.getDataAbertura());
        prontuario.setIdAnimal(animal);
        prontuario.setMedicamento(prontuarioForm.getMedicamento());
        return prontuario;
    }

    public ProntuarioForm entityParaProntuario(ProntuarioEntity entity) {
        ProntuarioForm prontuarioForm = new ProntuarioForm();
        prontuarioForm.setId(entity.getId());
        prontuarioForm.setAlergia(entity.getAlergia());
        prontuarioForm.setEnfermidade(entity.getEnfermidade());
        prontuarioForm.setDataAbertura(entity.getDataAbertura());
        prontuarioForm.setIdAnimal(entity.getIdAnimal().getId());
        prontuarioForm.setMedicamento(entity.getMedicamento());
        return prontuarioForm;
    }

    public ProntuarioEntity prontuarioParaEntity(ProntuarioForm form, AnimalEntity animal, Long id) {
        ProntuarioEntity prontuario = new ProntuarioEntity();
        prontuario.setAlergia(form.getAlergia());
        prontuario.setEnfermidade(form.getEnfermidade());
        prontuario.setDataAbertura(form.getDataAbertura());
        prontuario.setIdAnimal(animal);
        prontuario.setMedicamento(form.getMedicamento());
        prontuario.setId(id);
        return prontuario;
    }

    public ProntuarioEntity prontuarioVazio(AnimalEntity animal, String data) {
        ProntuarioEntity entity = new ProntuarioEntity();
        entity.setMedicamento("");
        entity.setAlergia("");
        entity.setEnfermidade("");
        entity.setDataAbertura(data);
        entity.setIdAnimal(animal);
        return entity;
    }
}
