package com.example.api.repository.Agendamento;

import com.example.api.entity.Agendamento.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {


    @Query(value = "SELECT * FROM agendamento WHERE id_cliente = ?1", nativeQuery = true)
    List<AgendamentoEntity> buscarAgendamentos(Long id);
}
