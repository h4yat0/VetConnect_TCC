package com.example.api.repository.Agendamento;

import com.example.api.entity.Agendamento.ServicoEntity;
import com.example.api.entity.Agendamento.UnidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<UnidadeEntity,Long> {

    @Query(value = "SELECT * from tb_unidade where id = ?1", nativeQuery = true)
    UnidadeEntity buscarUnidade(Long id);
}
