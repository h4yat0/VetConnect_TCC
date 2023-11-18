package br.vetconnect.api.configs;

import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.entity.security.PermissionEntity;
import br.vetconnect.api.repository.ClienteRepository;
import br.vetconnect.api.repository.security.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class teste implements CommandLineRunner {

    @Autowired
    private PermissionRepository repository;


    @Override
    public void run(String... args) throws Exception {
        if(repository.count() ==0){
            repository.save(new PermissionEntity(1L, "Cliente"));
            repository.save(new PermissionEntity(2L, "Funcionario"));
        }
    }
}
