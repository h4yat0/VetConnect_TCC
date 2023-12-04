package br.vetconnect.api.service.Animal;


import br.vetconnect.api.controller.AnimalController;
import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.form.Animal.AnimalFormCreate;
import br.vetconnect.api.form.Animal.AnimalFormReturn;
import br.vetconnect.api.mapper.Animal.AnimalMapper;
import br.vetconnect.api.repository.AnimalRepository;
import br.vetconnect.api.repository.ClienteRepository;
import br.vetconnect.api.service.Prontuario.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AnimalMapper animalMapper;

    @Autowired
    private ImagemAnimalService imagemService;

    @Autowired
    private ProntuarioService prontuarioService;

    public AnimalFormReturn saveAnimal(AnimalFormCreate animal) {
        ClienteEntity cliente = clienteRepository.buscarPorId(animal.getIdCliente());
        if(cliente == null){
            return null;
        }else {
            AnimalEntity animalEntity = animalMapper.formCreateToEntity(animal, cliente);
            animalEntity = animalRepository.save(animalEntity);
            AnimalFormReturn animalFormReturn = animalMapper.entityToFormReturn(animalEntity);
            animalFormReturn.add(linkTo(methodOn(AnimalController.class).cadastroAnimal(animal)).withSelfRel());
            if(animal.getImagens() != null && !animal.getImagens().isEmpty()){
                imagemService.salvarImagemAnimal(animal.getImagens(), animalEntity);
            }
            prontuarioService.insereProntuarioVazio(animalEntity, LocalDate.now().toString());

            return animalFormReturn;
        }
    }

    public List<AnimalFormReturn> buscarAnimal(Long id){
        List<AnimalFormReturn> animais = animalMapper.listEntityToFormReturn(animalRepository.buscarAnimais(id));
        if(animais == null){
            return  null;
        }else{
            animais.stream().forEach(a -> a.add(linkTo(methodOn(AnimalController.class).buscarAnimais(id)).withSelfRel()));
            return animais;
        }
    }

    public void deleteAnimal(Long id){animalRepository.deleteById(id);}

    public AnimalFormReturn alterarAnimal(AnimalFormCreate animal, long id) {
        ClienteEntity cliente = clienteRepository.buscarPorId(animal.getIdCliente());
        AnimalEntity animalEntity = animalMapper.formCreateToEntity(animal, id, cliente);
        AnimalFormReturn animalFormReturn = animalMapper.entityToFormReturn(animalRepository.save(animalEntity));
        animalFormReturn.add(linkTo(methodOn(AnimalController.class).alterarAnimal(animal, id)).withSelfRel());
        if(animal.getImagens() != null && !animal.getImagens().isEmpty()){
            imagemService.alterarImagem(animal.getImagens(), animalEntity);
        }
        return  animalFormReturn;
    }
}
