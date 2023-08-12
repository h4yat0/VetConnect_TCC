package com.example.api.service;

import com.example.api.controller.ClienteController;
import com.example.api.entity.ClienteEntity;
import com.example.api.form.Cliente.ClienteFormCreate;
import com.example.api.form.Cliente.ClienteFormReturn;
import com.example.api.form.Login;
import com.example.api.mapper.ClienteMapper;
import com.example.api.mapper.LoginMapper;
import com.example.api.repository.ClienteRepository;
import com.example.api.utils.MetodosAuxiliares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class ClienteService extends MetodosAuxiliares {


    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteMapper mapper;

    @Autowired
    private LoginMapper loginMapper;

    public ClienteFormReturn getClienteByEmailSenha(Login login){
        ClienteEntity entity = repository.buscarPorEmailSenha(login.getEmail(),login.getSenha());
        if(entity == null){
            return null;
        }else{
            ClienteFormReturn clienteForm = mapper.convertEntityToForm(entity);

            clienteForm.add(linkTo(methodOn(ClienteController.class).getLogin(login)).withSelfRel());
            return clienteForm;
        }

    }

    public ClienteFormReturn getClienteByIdCpfEmail(Long id, String email, String cpf){
        ClienteEntity result = repository.buscarPorIdEmailCpf(id, email, cpf);
        if(result == null){
            return null;
        }else{
            return mapper.convertEntityToForm(result);
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
            return mapper.convertEntityToForm(repository.buscarPorEmail(email));
        }
    }

    public ClienteFormReturn getClienteByCpf(String cpf){
        ClienteEntity result = repository.buscarPorCpf(cpf);
        if(result == null){
            return null;
        }else{
            return mapper.convertEntityToForm(repository.buscarPorCpf(cpf));
        }
    }



    public ClienteFormReturn saveCliente(ClienteFormCreate cliente){
        if(buscarCpf(cliente.getCpf()) != null || buscarEmail(cliente.getEmail()) != null ||
                idadeUsuario(cliente.getDataNascimento()) < 18 || cliente.getCpf().length() > 11){
            return null;
        }else{
            var entity = mapper.convertFormToEntity(cliente);
            var clienteForm = mapper.convertEntityToForm(repository.save(entity));
            clienteForm.add(linkTo(methodOn(ClienteController.class).postCliente(cliente)).withSelfRel() );
            return clienteForm;
        }

    }


    public ClienteFormReturn alteararCliente(ClienteFormCreate cliente, Long id){
        ClienteFormReturn clienteFormReturn = getClientById(id);
        ClienteFormReturn clienteReturnCpf = getClienteByCpf(cliente.getCpf());
        ClienteFormReturn clienteReturnEmail = getClienteByEmail(cliente.getEmail());

        if(cliente.getCpf().length() >11 || clienteFormReturn == null || (clienteReturnCpf != null && !clienteReturnCpf.getId().equals(id)) ||
                (clienteReturnEmail != null && !clienteReturnEmail.getId().equals(id))){
            return null;
        }else{
            var entity = mapper.convertFormToEntity(cliente, id);
            var form = mapper.convertEntityToForm(repository.save(entity));
            form.add(linkTo(methodOn(ClienteController.class).putCliente(id, cliente)).withSelfRel() );
            return form;
        }



    }

    public void delete(Long id){repository.deleteById(id);}

    public String buscarCpf(String cpf){
        return repository.buscarCpf(cpf);
    }

    public String buscarEmail(String email){
        return repository.buscarEmail(email);
    }


}
