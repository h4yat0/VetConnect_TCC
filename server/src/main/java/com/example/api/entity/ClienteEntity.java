package com.example.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String nome;
    private String dataNascimento;
    private String senha;

    @Column(unique = true)
    private String cpf;
    private String endereco;
    private String telefone;

    @Column(unique = true)
    private String email;

   // @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL) se não tiver animal não tem cliente
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST) //excluir todos os animais desse cliente não faz ele ser apagado
    private List<AnimalEntity> animais = new ArrayList<>();
}
