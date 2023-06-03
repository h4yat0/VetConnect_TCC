package com.example.api.repository;

import com.example.api.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {


    @Query(value = "SELECT * FROM tb_cliente WHERE email = ?1 AND senha = ?2", nativeQuery = true)
    ClienteEntity buscarPorEmailSenha(String email, String senha);


    @Query(value = "SELECT * FROM tb_cliente WHERE id = ?1", nativeQuery = true)
    ClienteEntity burcarPorId(Long id);

    @Query(value = "SELECT cpf FROM tb_cliente WHERE cpf =?1", nativeQuery = true)
    String buscarCpf(String cpf);

    @Query(value = "SELECT email FROM tb_cliente WHERE email =?1", nativeQuery = true)
    String buscarEmail(String cpf);

}
