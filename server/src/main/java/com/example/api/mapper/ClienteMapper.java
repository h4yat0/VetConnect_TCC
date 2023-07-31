package com.example.api.mapper;

import com.example.api.entity.ClienteEntity;
import com.example.api.form.Cliente.ClienteFormCreate;
import com.example.api.form.Cliente.ClienteFormReturn;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {

    public ClienteFormReturn convertEntityToForm(ClienteEntity entity){
        ClienteFormReturn clienteForm = new ClienteFormReturn();
        clienteForm.setId(entity.getId());
        clienteForm.setCpf(entity.getCpf());
        clienteForm.setEmail(entity.getEmail());
        clienteForm.setSenha(entity.getSenha());
        clienteForm.setTelefone(entity.getTelefone());
        clienteForm.setEndereco(entity.getEndereco());
        clienteForm.setDataNascimento(entity.getDataNascimento());
        clienteForm.setNome(entity.getNome());
        return clienteForm;
    }

    public ClienteEntity convertFormToEntity(ClienteFormCreate form){
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf(form.getCpf());
        clienteEntity.setEmail(form.getEmail());
        clienteEntity.setSenha(form.getSenha());
        clienteEntity.setTelefone(form.getTelefone());
        clienteEntity.setEndereco(form.getEndereco());
        clienteEntity.setDataNascimento(form.getDataNascimento());
        clienteEntity.setNome(form.getNome());
        return clienteEntity;
    }

    public ClienteEntity convertFormToEntity(ClienteFormCreate form, Long id){
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(id);
        clienteEntity.setCpf(form.getCpf());
        clienteEntity.setEmail(form.getEmail());
        clienteEntity.setSenha(form.getSenha());
        clienteEntity.setTelefone(form.getTelefone());
        clienteEntity.setEndereco(form.getEndereco());
        clienteEntity.setDataNascimento(form.getDataNascimento());
        clienteEntity.setNome(form.getNome());
        return clienteEntity;
    }
}
