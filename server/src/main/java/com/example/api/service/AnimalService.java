package com.example.api.service;


import com.example.api.entity.AnimalEntity;
import com.example.api.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    public AnimalEntity saveAnimal(AnimalEntity animal) {
        return repository.save(animal);
    }
}
