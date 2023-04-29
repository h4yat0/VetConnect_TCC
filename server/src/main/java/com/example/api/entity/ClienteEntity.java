package com.example.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cliente")
@Entity
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String nome;
    @Column(nullable = false)
    private String dataNascimento;


    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(nullable = false)
    private String endereco;
    private String telefone;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;

   // @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL) se não tiver animal não tem cliente
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST) //excluir todos os animais desse cliente não faz ele ser apagado
    private List<AnimalEntity> animais = new ArrayList<>();
}
