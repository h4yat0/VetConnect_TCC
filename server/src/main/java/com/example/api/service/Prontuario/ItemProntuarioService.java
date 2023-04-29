package com.example.api.service.Prontuario;

import com.example.api.repository.Prontuario.ItemProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemProntuarioService {

    @Autowired
    private ItemProntuarioRepository repository;
}
