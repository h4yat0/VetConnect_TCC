package com.example.api.repository;

import com.example.api.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {


    @Query(value = "SELECT * FROM cliente WHERE email = ?1 AND senha = ?2", nativeQuery = true)
    ClienteEntity buscarPorEmailSenha(String email, String senha);

    @Query(value = "SELECT * FROM cliente WHERE id = ?1 ", nativeQuery = true)
    ClienteEntity buscarPorId(Long id);

    @Query(value = "SELECT * FROM cliente WHERE id = ?1 AND email = ?2 AND senha = ?3", nativeQuery = true)
    ClienteEntity buscarPorIdEmailCpf(Long id, String email, String senha);

    @Query(value = "SELECT * FROM cliente WHERE cpf =?1", nativeQuery = true)
    ClienteEntity buscarPorCpf(String cpf);

    @Query(value = "SELECT * FROM cliente WHERE email =?1", nativeQuery = true)
    ClienteEntity buscarPorEmail(String cpf);

    @Query(value = "SELECT cpf FROM cliente WHERE cpf =?1", nativeQuery = true)
    String buscarCpf(String cpf);

    @Query(value = "SELECT email FROM cliente WHERE email =?1", nativeQuery = true)
    String buscarEmail(String cpf);

}
