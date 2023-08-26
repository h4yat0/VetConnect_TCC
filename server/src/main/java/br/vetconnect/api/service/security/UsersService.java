package br.vetconnect.api.service.security;

import br.vetconnect.api.controller.ClienteController;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.form.Cliente.ClienteFormReturn;
import br.vetconnect.api.form.Login;
import br.vetconnect.api.repository.security.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    UsersRepository repository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }

    }
}
