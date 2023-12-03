package br.vetconnect.api.service;

import br.vetconnect.api.mapper.LoginMapper;
import br.vetconnect.api.controller.ClienteController;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.form.Cliente.ClienteFormCreate;
import br.vetconnect.api.form.Cliente.ClienteFormReturn;
import br.vetconnect.api.form.Login;
import br.vetconnect.api.mapper.ClienteMapper;
import br.vetconnect.api.repository.ClienteRepository;
import br.vetconnect.api.service.Animal.AnimalService;
import br.vetconnect.api.service.security.AuthService;
import br.vetconnect.api.utils.MetodosAuxiliares;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private  PasswordEncoder encoder;

    @Autowired
    private AnimalService service;




    private static Logger logger = LoggerFactory.getLogger(ClienteService.class);


    public ClienteFormReturn getClienteByEmailSenha(Login login){
        String password = repository.buscarSenha(login.getEmail());
        boolean valid = encoder.matches(login.getSenha(), password);

        if(!valid || password == null){
            return null;
        }else{
            ClienteEntity entity = repository.buscarPorEmailSenha(login.getEmail(), password);
            ClienteFormReturn clienteForm = mapper.convertEntityToForm(entity);

            clienteForm.add(linkTo(methodOn(ClienteController.class).getLogin(login)).withSelfRel());

            clienteForm.setAnimalFormReturnList(service.buscarAnimal(entity.getId()));

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

    public ClienteEntity getClientByIdEntity(Long id){
       return repository.buscarPorId(id);
    }

    public ClienteFormReturn buscarPorEmail(String email){
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

    public ClienteFormReturn getClienteByCpf1(String cpf){
        ClienteEntity result = repository.buscarPorCpf(cpf);
        if(result == null){
            return null;
        }else{
            return mapper.convertEntityToForm1(repository.buscarPorCpf(cpf));
        }
    }



    public ClienteFormReturn saveCliente(ClienteFormCreate cliente)  {
        try{
            if(buscarCpf(cliente.getCpf()) != null || buscarEmail(cliente.getEmail()) != null ||
                    idadeUsuario(cliente.getDataNascimento()) < 18 || cliente.getCpf().length() > 11){
                return null;
            }else{
                var entity = mapper.convertFormToEntity(cliente);
                String senhaEnconder = authService.createPassword(entity.getSenha());
                entity.setSenha(senhaEnconder);
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
        ClienteFormReturn clienteReturnEmail = buscarPorEmail(cliente.getEmail());

        if(cliente.getCpf().length() >11 || clienteFormReturn == null || (clienteReturnCpf != null && !clienteReturnCpf.getId().equals(id)) ||
                (clienteReturnEmail != null && !clienteReturnEmail.getId().equals(id))){
            return null;
        }else{
            String senha = repository.buscarSenha(cliente.getEmail());
            var entity = mapper.convertFormToEntity(cliente, id, senha);
            var form = mapper.convertEntityToForm(repository.save(entity));
            form.add(linkTo(methodOn(ClienteController.class).putCliente(id, cliente)).withSelfRel() );

            authService.updateUser(form.getEmail(), id);

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


    public void updatePassword(String email, String senha) {
        ClienteEntity entity = repository.buscarPorEmail(email);
        String senhaNova = authService.createPassword(senha);
        entity.setSenha(senhaNova);
        repository.save(entity);
        authService.updatePasswordUser(senhaNova, entity.getId());
    }
}
