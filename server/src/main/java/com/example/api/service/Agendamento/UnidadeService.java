package com.example.api.service.Agendamento;

import com.example.api.entity.Agendamento.ServicoEntity;
import com.example.api.entity.Agendamento.UnidadeEntity;
import com.example.api.repository.Agendamento.ServicoRepository;
import com.example.api.repository.Agendamento.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeService {


    @Autowired
    private UnidadeRepository repository;

    public List<UnidadeEntity> getAll(){
        return repository.findAll();
    }

    public List<ServicoEntity> getById(Long id) {
        UnidadeEntity entity = repository.buscarUnidade(id);
        return entity.getServicos();
    }
}
