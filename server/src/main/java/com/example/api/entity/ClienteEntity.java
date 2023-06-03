package com.example.api.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "animais"}) // anotação adicionada aqui
@Entity
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String nome;
    @Column(nullable = false, name = "data_nascimento")
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
    @OneToMany(mappedBy = "idCliente", cascade = CascadeType.PERSIST) //excluir todos os animais desse cliente não faz ele ser apagado
    private List<AnimalEntity> animais = new ArrayList<>();
}
