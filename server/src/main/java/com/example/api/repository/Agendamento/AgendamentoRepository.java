package com.example.api.repository.Agendamento;

import com.example.api.entity.Agendamento.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {
}
