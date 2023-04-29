package com.example.api.repository.Prontuario;

import com.example.api.entity.Prontuario.ExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExameRepository extends JpaRepository<ExameEntity, Long> {
}
