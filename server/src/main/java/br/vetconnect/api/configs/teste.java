package br.vetconnect.api.configs;

import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.entity.security.PermissionEntity;
import br.vetconnect.api.entity.security.UsersEntity;
import br.vetconnect.api.repository.ClienteRepository;
import br.vetconnect.api.repository.security.PermissionRepository;
import br.vetconnect.api.repository.security.UsersRepository;
import br.vetconnect.api.service.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class teste implements CommandLineRunner {

    @Autowired
    private PermissionRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthService service;


    @Override
    public void run(String... args) throws Exception {
        if(repository.count() ==0){
            repository.save(new PermissionEntity(1L, "Cliente"));
            repository.save(new PermissionEntity(2L, "Funcionario"));
            repository.save(new PermissionEntity(3L, "Gerente"));
        }

        if(usersRepository.count() == 0){
            usersRepository.save(new UsersEntity(1L, "admin", 3, 1L, service.createPassword("senha123"),
                    true, true, true, true, new ArrayList<>(Arrays.asList(new PermissionEntity(3L, "Gerente")))));
        }
    }
}
