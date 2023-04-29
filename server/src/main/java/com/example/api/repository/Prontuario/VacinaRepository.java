package com.example.api.repository.Prontuario;

import com.example.api.entity.Prontuario.VacinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacinaRepository extends JpaRepository<VacinaEntity, Long> {
}
