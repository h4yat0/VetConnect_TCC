package br.vetconnect.api.service;


import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.form.Animal.AnimalFormCreate;
import br.vetconnect.api.repository.AnimalRepository;
import br.vetconnect.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public AnimalEntity saveAnimal(AnimalFormCreate animal) {
        ClienteEntity cliente = clienteRepository.buscarPorId(animal.getIdCliente());
        if(cliente == null){
            return null;
        }else {
            AnimalEntity entity = new AnimalEntity();
            entity.setNome(animal.getNome());
            entity.setIdCliente(cliente);
            entity.setCor(animal.getCor());
            entity.setDataNascimento(animal.getDataNascimento());
            entity.setPeso(animal.getPeso());
            entity.setTamanho(animal.getTamanho());
            entity.setEspecie(animal.getEspecie());
            entity.setSexo(animal.getSexo());
            if(animal.getRaca() != null){
                entity.setRaca(animal.getRaca());
            }
            return animalRepository.save(entity);
        }
    }

    public List<AnimalEntity> buscarAnimal(Long id){
        return animalRepository.buscarAnimais(id);
    }

    public void deleteAnimal(Long id){animalRepository.deleteById(id);}

    public AnimalEntity alterarAnimal(AnimalEntity animal) {
        return  animalRepository.save(animal);
    }
}
