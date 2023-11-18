package br.vetconnect.api.service;

import br.vetconnect.api.controller.ClienteController;
import br.vetconnect.api.controller.FuncionarioController;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.entity.FuncionarioEntity;
import br.vetconnect.api.entity.UnidadeServico.UnidadeEntity;
import br.vetconnect.api.form.Cliente.ClienteFormReturn;
import br.vetconnect.api.form.Funcionario.FuncionarioFormCreate;
import br.vetconnect.api.form.Funcionario.FuncionarioFormReturn;
import br.vetconnect.api.form.Login;
import br.vetconnect.api.mapper.FuncionarioMapper;
import br.vetconnect.api.repository.FuncionarioRepository;
import br.vetconnect.api.service.Agendamento.UnidadeService;
import br.vetconnect.api.service.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private FuncionarioMapper mapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private AssociacaoService associacaoService;

    @Autowired
    private UnidadeService unidadeService;

    public FuncionarioFormReturn getFuncionarioByEmailSenha(Login login) {
        String password = repository.buscarSenha(login.getEmail());
        boolean valid = encoder.matches(login.getSenha(), password);

        if(!valid || password == null){
            return null;
        }else{
            FuncionarioEntity entity = repository.buscarPorEmailSenha(login.getEmail(), password);
            FuncionarioFormReturn funcionarioForm = mapper.convertEntityToForm(entity);

            funcionarioForm.add(linkTo(methodOn(ClienteController.class).getLogin(login)).withSelfRel());



            return funcionarioForm;
        }
    }

    public FuncionarioFormReturn cadastrarFuncionario(FuncionarioFormCreate formCreate) {
        try{
            var entity = mapper.convertFormToEntity(formCreate, unidadeService.getById(formCreate.getIdUnidade()));
            String senhaEnconder = authService.createPassword(entity.getSenha());
            entity.setSenha(senhaEnconder);


            var clienteForm = mapper.convertEntityToForm(repository.save(entity));
            clienteForm.add(linkTo(methodOn(FuncionarioController.class).cadastrarFuncionario(formCreate)).withSelfRel() );

            authService.createUserFuncionario(formCreate.getEmail(), senhaEnconder, clienteForm.getId());

            return clienteForm;
        }catch (Exception e){
            return null;
        }
    }
}
