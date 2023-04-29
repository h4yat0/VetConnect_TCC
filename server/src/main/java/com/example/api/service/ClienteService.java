package com.example.api.service;

import com.example.api.entity.ClienteEntity;
import com.example.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository repository;

    public ClienteEntity getClienteByEmailSenha(String email, String senha){
        return repository.buscarPorEmailSenha(email, senha);
    }

    @Transactional
    public ClienteEntity saveCliente(ClienteEntity cliente){
        return repository.save(cliente);
    }

    public ClienteEntity getById(Long idCliente) {
        return repository.burcarPorId(idCliente);
    }
}
