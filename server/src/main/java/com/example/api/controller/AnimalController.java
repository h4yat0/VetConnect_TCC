package com.example.api.controller;


import com.example.api.entity.AnimalEntity;
import com.example.api.entity.ClienteEntity;
import com.example.api.entity.form.AnimalForm;
import com.example.api.service.AnimalService;
import com.example.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;



    @PostMapping("/cadastro")
    public AnimalForm cadastroAnimal(@RequestBody AnimalForm animal){
        if(animalService.saveAnimal(animal) == null){
            return null;
        }else{
            return animal;
        }
    }

    @GetMapping("/buscar/{id}")
    public List<AnimalEntity> buscarAnimal(@PathVariable Long id){
        List<AnimalEntity> animais = animalService.buscarAnimal(id);
        if(animais.size() ==0){
            return null;
        }else{
            return animais;
        }
    }

    @PutMapping("/alterar")
    public AnimalEntity alterarAnimal(@RequestBody AnimalEntity animal){
        return animalService.alterarAnimal(animal);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){animalService.deleteAnimal(id);}

}
