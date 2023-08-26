package br.vetconnect.api.service.Prontuario;

import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.Prontuario.ProntuarioEntity;
import br.vetconnect.api.entity.Prontuario.VacinaEntity;
import br.vetconnect.api.form.Prontuario.ProntuarioForm;
import br.vetconnect.api.repository.AnimalRepository;
import br.vetconnect.api.repository.Prontuario.ProntuarioRepository;
import br.vetconnect.api.repository.Prontuario.VacinaRepository;
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
