package br.vetconnect.api.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

     private String email;
     private String senha;
     private Boolean account_non_expired;
     private Boolean account_non_locked;
     private Boolean credentials_non_expired;
     private Boolean enabled;

}
