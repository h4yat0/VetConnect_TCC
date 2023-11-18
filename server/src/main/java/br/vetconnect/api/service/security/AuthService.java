package br.vetconnect.api.service.security;

import br.vetconnect.api.entity.security.PermissionEntity;
import br.vetconnect.api.entity.security.UsersEntity;
import br.vetconnect.api.form.Login;
import br.vetconnect.api.form.Token;
import br.vetconnect.api.repository.security.UsersRepository;
import br.vetconnect.api.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UsersRepository repository;


    public ResponseEntity signIn(Login login){
        try {
            String email = login.getEmail();
            String senha = login.getSenha();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));

            UsersEntity entity = repository.findByUsername(email);
            var tokenResponse = new Token();


            if(entity != null){
                tokenResponse = tokenProvider.createAccessToken(email, entity.getRoles());
            }else {
                throw new UsernameNotFoundException("Não foi encontrado este usuario");
            }

            return  ResponseEntity.ok(tokenResponse);
        }catch (Exception e){
            throw  new BadCredentialsException("Email ou senha invalidos");
        }
    }

    public ResponseEntity refreshToken(String email, String refreshToken){

            UsersEntity entity = repository.findByUsername(email);
            var tokenResponse = new Token();


            if(entity != null){
                tokenResponse = tokenProvider.refreshToken(refreshToken);
            }else {
                throw new UsernameNotFoundException("Não foi encontrado este usuario");
            }

            return  ResponseEntity.ok(tokenResponse);

    }

    public void createUserCliente(String email, String senha, Long id){

        Map<String, PasswordEncoder> encoders = new HashMap<>();

        Pbkdf2PasswordEncoder pbkdf2Encoder =
                new Pbkdf2PasswordEncoder(
                        "", 8, 185000,
                        Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

        String result = passwordEncoder.encode(senha).replace("{pbkdf2}","");




        UsersEntity entity = new UsersEntity();
        entity.setUserName(email);
        entity.setPassword(result);
        entity.setAccountNonExpired(true);
        entity.setCredentialsNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setEnabled(true);
        entity.setPermissions(new ArrayList<>(Arrays.asList(new PermissionEntity(1L, "COMMON_USER"))));
        entity.setTipo(1);
        entity.setIdTipo(id);
        repository.save(entity);


    }

    public void createUserFuncionario(String email, String senha, Long id){
        UsersEntity entity = new UsersEntity();
        entity.setUserName(email);
        entity.setPassword(senha);
        entity.setAccountNonExpired(true);
        entity.setCredentialsNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setEnabled(true);
        entity.setPermissions(new ArrayList<>(Arrays.asList(new PermissionEntity(2L, "Funcionario"))));
        entity.setTipo(2);
        entity.setIdTipo(id);
        repository.save(entity);
    }

    public void updateUser(String email, Long id) {
        UsersEntity entity = repository.findByTipoEId(id);
        entity.setUserName(email);
        repository.save(entity);
    }


    public String createPassword(String senha){
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        Pbkdf2PasswordEncoder pbkdf2Encoder =
                new Pbkdf2PasswordEncoder(
                        "", 8, 185000,
                        Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

        return passwordEncoder.encode(senha).replace("{pbkdf2}","");
    }

    public void updatePasswordUser(String senhaNova, Long id) {
        UsersEntity entity = repository.findByTipoEId(id);
        entity.setPassword(senhaNova);
        repository.save(entity);
    }
}
