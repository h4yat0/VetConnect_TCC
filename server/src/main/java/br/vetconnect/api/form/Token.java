package br.vetconnect.api.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    private String email;
    private Boolean authenticated;
    private Date create;
    private Date expiration;
    private String accessToken;
    private String refreshToken;
}
