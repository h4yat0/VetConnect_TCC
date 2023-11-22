package br.vetconnect.api.service.Prontuario;

import br.vetconnect.api.controller.Prontuario.ProntuarioController;
import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.Prontuario.ProntuarioEntity;
import br.vetconnect.api.form.Prontuario.ProntuarioForm;
import br.vetconnect.api.mapper.ProntuarioMapper;
import br.vetconnect.api.repository.AnimalRepository;
import br.vetconnect.api.repository.Prontuario.ProntuarioRepository;
import br.vetconnect.api.repository.Prontuario.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class  ProntuarioService {

    @Autowired
    private ProntuarioRepository repository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private VacinaRepository vacinaRepository;

    @Autowired
    private ProntuarioMapper mapper;

    public ProntuarioForm salvarProntuario(ProntuarioForm prontuarioForm){
        try {
            ProntuarioEntity prontuarioEntity = repository.buscarProntuarioPorIdAnimal(prontuarioForm.getIdAnimal());
            AnimalEntity animal = animalRepository.buscarAnimal(prontuarioForm.getIdAnimal());

            if(animal == null && prontuarioEntity != null){
                return null;
            }else{
                ProntuarioEntity entity = mapper.prontuarioParaEntity(prontuarioForm, animal);
                ProntuarioForm formReturn = mapper.entityParaProntuario(repository.save(entity));
                formReturn.add(linkTo(methodOn(ProntuarioController.class).salvarProntuario(prontuarioForm)).withSelfRel());
                return formReturn;
            }

        }catch (Exception e){
            return null;
        }

    }

    public ProntuarioForm buscarPronatuario(Long id) {
        ProntuarioForm prontuario = mapper.entityParaProntuario(repository.buscarProntuarioPorIdAnimal(id));
        return prontuario;
    }

    public ProntuarioEntity buscarProntuarioPorId(Long id){
        return repository.buscarProntuarioPorId(id);
    }

    public void deleteProntuario(Long id){repository.deleteById(id);}

    public ProntuarioForm alterarProntuario(ProntuarioForm form, Long id) {
        AnimalEntity animal = animalRepository.buscarAnimal(form.getIdAnimal());

        if(animal == null){
            return null;
        }else{
            ProntuarioEntity entity = mapper.prontuarioParaEntity(form, animal, id);
            ProntuarioForm formReturn = mapper.entityParaProntuario(repository.save(entity));
            formReturn.add(linkTo(methodOn(ProntuarioController.class).alterarPontuario(form, id)).withSelfRel());
            return formReturn;
        }
    }
}
