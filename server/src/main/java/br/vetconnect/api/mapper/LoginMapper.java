package br.vetconnect.api.mapper;

import br.vetconnect.api.form.Login;
import org.springframework.stereotype.Service;

@Service
public class LoginMapper {

    public Login criaLogin(String email, String senha){
        Login login = new Login();
        login.setEmail(email);
        login.setSenha(senha);
        return login;
    }

}
