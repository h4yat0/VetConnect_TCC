package com.example.api.service;

import com.example.api.entity.ClienteEntity;
import com.example.api.entity.form.ClienteForm;
import com.example.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository repository;

    public ClienteEntity getClienteByEmailSenha(String email, String senha){
        Optional<ClienteEntity> entityOptional = Optional.ofNullable(repository.buscarPorEmailSenha(email, senha));
        return entityOptional.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }



    public ClienteEntity saveCliente(ClienteForm cliente){

        System.out.println(cliente);
        if(repository.buscarCpf(cliente.getCpf()) != null || repository.buscarEmail(cliente.getEmail()) != null) {
            return null;
        }else {
            ClienteEntity entity = new ClienteEntity();
            entity.setNome(cliente.getNome());
            entity.setDataNascimento(cliente.getDataNascimento());
            entity.setCpf(cliente.getCpf());
            entity.setEndereco(cliente.getEndereco());
            entity.setEmail(cliente.getEmail());
            entity.setSenha(cliente.getSenha());
            if(cliente.getTelefone() != null){
                entity.setTelefone(cliente.getTelefone());
            }
            System.out.println("criou objeto");
            return repository.save(entity);
        }
    }

    public ClienteEntity getById(Long idCliente) {
        return repository.burcarPorId(idCliente);
    }

    public ClienteEntity alteararCliente(ClienteEntity cliente){
        return repository.save(cliente);
    }

    public void delete(Long id){repository.deleteById(id);}

}
