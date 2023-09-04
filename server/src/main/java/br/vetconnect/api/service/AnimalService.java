package br.vetconnect.api.service;


import br.vetconnect.api.controller.AnimalController;
import br.vetconnect.api.controller.ClienteController;
import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.form.Animal.AnimalFormCreate;
import br.vetconnect.api.form.Animal.AnimalFormReturn;
import br.vetconnect.api.mapper.Animal.AnimalMapper;
import br.vetconnect.api.repository.AnimalRepository;
import br.vetconnect.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public AnimalFormReturn saveAnimal(AnimalFormCreate animal) {
        ClienteEntity cliente = clienteRepository.buscarPorId(animal.getIdCliente());
        if(cliente == null){
            return null;
        }else {
            AnimalEntity animalEntity = animalMapper.formCreateToEntity(animal, cliente);
            AnimalFormReturn animalFormReturn = animalMapper.entityToFormReturn(animalRepository.save(animalEntity));
            animalFormReturn.add(linkTo(methodOn(AnimalController.class).cadastroAnimal(animal)).withSelfRel());
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
        return  animalMapper.entityToFormReturn(animalRepository.save(animalEntity));
    }
}
