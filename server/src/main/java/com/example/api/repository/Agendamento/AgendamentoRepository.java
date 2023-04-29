package com.example.api.repository.Agendamento;

import com.example.api.entity.Agendamento.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {
}
