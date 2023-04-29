package com.example.api.service.Agendamento;

import com.example.api.repository.Agendamento.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;
}
