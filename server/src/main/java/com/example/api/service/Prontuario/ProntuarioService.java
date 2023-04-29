package com.example.api.service.Prontuario;

import com.example.api.repository.Prontuario.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository repository;
}
