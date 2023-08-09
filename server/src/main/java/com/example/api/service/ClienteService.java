package com.example.api.service;

import com.example.api.entity.ClienteEntity;
import com.example.api.form.Cliente.ClienteFormCreate;
import com.example.api.form.Cliente.ClienteFormReturn;
import com.example.api.mapper.ClienteMapper;
import com.example.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteMapper mapper;

    public ClienteFormReturn getClienteByEmailSenha(String email, String senha){
        var clienteForm = mapper.convertEntityToForm(repository.buscarPorEmailSenha(email, senha));
        return clienteForm;
    }

    public ClienteFormReturn getClienteByIdCpfEmail(Long id, String email, String cpf){
        ClienteEntity result = repository.buscarPorIdEmailCpf(id, email, cpf);
        if(result == null){
            return null;
        }else{
        ClienteFormReturn clienteForm = mapper.convertEntityToForm(result);
        return clienteForm;
        }
    }

    public ClienteFormReturn getClientById(Long id){
        ClienteEntity result = repository.buscarPorId(id);
        if(result == null){
            return null;
        }else{
            return mapper.convertEntityToForm(result);
        }
    }

    public ClienteFormReturn getClienteByEmail(String email){
        ClienteEntity result = repository.buscarPorEmail(email);
        if(result == null){
            return null;
        }else {
            ClienteFormReturn clienteFormReturn = mapper.convertEntityToForm(repository.buscarPorEmail(email));
            return clienteFormReturn;
        }
    }

    public ClienteFormReturn getClienteByCpf(String cpf){
        ClienteEntity result = repository.buscarPorCpf(cpf);
        if(result == null){
            return null;
        }else{
        ClienteFormReturn clienteFormReturn = mapper.convertEntityToForm(repository.buscarPorCpf(cpf));
        return clienteFormReturn;
        }
    }



    public ClienteFormReturn saveCliente(ClienteFormCreate cliente){
        var entity = mapper.convertFormToEntity(cliente);
        var clienteForm = mapper.convertEntityToForm(repository.save(entity));
        return clienteForm;
    }


    public ClienteFormReturn alteararCliente(ClienteFormCreate cliente, Long id){
        var entity = mapper.convertFormToEntity(cliente, id);
        var form = mapper.convertEntityToForm(repository.save(entity));
        return form;
    }

    public void delete(Long id){repository.deleteById(id);}

    public String buscarCpf(String cpf){
        String cpfBuscado = repository.buscarCpf(cpf);
        return cpfBuscado;
    }

    public String buscarEmail(String email){
        String emailBuscado = repository.buscarEmail(email);
        return emailBuscado;
    }


}
