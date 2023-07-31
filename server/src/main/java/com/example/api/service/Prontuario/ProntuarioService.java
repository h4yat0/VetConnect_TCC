package com.example.api.service.Prontuario;

import com.example.api.entity.AnimalEntity;
import com.example.api.entity.Prontuario.ProntuarioEntity;
import com.example.api.entity.Prontuario.VacinaEntity;
import com.example.api.form.Prontuario.ProntuarioForm;
import com.example.api.repository.AnimalRepository;
import com.example.api.repository.Prontuario.ProntuarioRepository;
import com.example.api.repository.Prontuario.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  ProntuarioService {

    @Autowired
    private ProntuarioRepository repository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private VacinaRepository vacinaRepository;

    public ProntuarioEntity salvarProntuario(ProntuarioForm prontuarioForm){
        AnimalEntity animal = animalRepository.buscarAnimal(prontuarioForm.getIdAnimal());

        if(animal == null){
            return null;
        }else{
            ProntuarioEntity entity = new ProntuarioEntity();
            entity.setAlergia(prontuarioForm.getAlergia());
            entity.setEnfermidade(prontuarioForm.getEnfermidade());
            entity.setMedicamento(prontuarioForm.getMedicamento());
            entity.setDataAbertura(prontuarioForm.getDataAbertura());
            entity.setIdAnimal(animal);
            return repository.save(entity);
        }

    }

    public ProntuarioEntity buscarPronatuario(Long id) {
        ProntuarioEntity prontuario = repository.buscarProntuarioPorId(id);
        List<VacinaEntity> vacina = vacinaRepository.buscarVacinaPorId(id);
        prontuario.setVacinas(vacinaRepository.buscarVacinaPorId(id));
        return prontuario;
    }

    public void deleteProntuario(Long id){repository.deleteById(id);}
}
