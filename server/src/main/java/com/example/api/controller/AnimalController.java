package com.example.api.controller;


import com.example.api.entity.AnimalEntity;
import com.example.api.entity.ClienteEntity;
import com.example.api.service.AnimalService;
import com.example.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastro")
    public AnimalEntity cadastroAnimal(@RequestBody AnimalEntity animal){
        ClienteEntity cliente = clienteService.getById(animal.getId_cliente());
        if(cliente == null){
            return null;
        }else{
            return animalService.saveAnimal(animal);
        }
    }
}
