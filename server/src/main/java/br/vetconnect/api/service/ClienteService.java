package br.vetconnect.api.service;

import br.vetconnect.api.mapper.LoginMapper;
import br.vetconnect.api.controller.ClienteController;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.form.Cliente.ClienteFormCreate;
import br.vetconnect.api.form.Cliente.ClienteFormReturn;
import br.vetconnect.api.form.Login;
import br.vetconnect.api.mapper.ClienteMapper;
import br.vetconnect.api.repository.ClienteRepository;
import br.vetconnect.api.service.security.AuthService;
import br.vetconnect.api.utils.MetodosAuxiliares;
import exceptions.ExecptionNovos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private AuthService authService;

    private static Logger logger = LoggerFactory.getLogger(ClienteService.class);

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



    public ClienteFormReturn saveCliente(ClienteFormCreate cliente)  {
        try{
            if(buscarCpf(cliente.getCpf()) != null || buscarEmail(cliente.getEmail()) != null ||
                    idadeUsuario(cliente.getDataNascimento()) < 18 || cliente.getCpf().length() > 11){
                return null;
            }else{
                var entity = mapper.convertFormToEntity(cliente);
                var clienteForm = mapper.convertEntityToForm(repository.save(entity));
                clienteForm.add(linkTo(methodOn(ClienteController.class).postCliente(cliente)).withSelfRel() );

                authService.createUserCliente(cliente.getEmail(), cliente.getSenha(), clienteForm.getId());

                return clienteForm;
            }
        }catch (Exception e){
             logger.error(e.getMessage());
             return null;
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

            authService.updateUser(form.getEmail(), form.getSenha(), id);

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
