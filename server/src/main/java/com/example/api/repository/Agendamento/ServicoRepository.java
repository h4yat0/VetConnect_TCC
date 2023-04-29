package com.example.api.repository.Agendamento;

import com.example.api.entity.Agendamento.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<ServicoEntity, Long> {
}
