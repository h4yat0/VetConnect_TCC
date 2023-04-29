package com.example.api.service.Prontuario;


import com.example.api.repository.Prontuario.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository repository;

}
