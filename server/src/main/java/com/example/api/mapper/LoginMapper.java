package com.example.api.mapper;

import com.example.api.form.Login;
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
