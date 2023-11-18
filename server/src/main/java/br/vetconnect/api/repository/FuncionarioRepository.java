package br.vetconnect.api.repository;


import br.vetconnect.api.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {

    @Query(value = "SELECT senha FROM funcionario WHERE email = ?1", nativeQuery = true)
    String buscarSenha(String email);

    @Query(value = "SELECT * FROM funcionario WHERE email = ?1  and senha = ?2", nativeQuery = true)
    FuncionarioEntity buscarPorEmailSenha(String email, String password);
}
