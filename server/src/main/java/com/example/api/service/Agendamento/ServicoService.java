package com.example.api.service.Agendamento;

import com.example.api.entity.Agendamento.ServicoEntity;
import com.example.api.repository.Agendamento.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public List<ServicoEntity> getAll(){
        return repository.findAll();
    }
}
