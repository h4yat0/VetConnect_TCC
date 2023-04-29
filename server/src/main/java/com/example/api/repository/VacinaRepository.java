package com.example.api.repository;

import com.example.api.entity.VacinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacinaRepository extends JpaRepository<VacinaEntity, Long> {
}
