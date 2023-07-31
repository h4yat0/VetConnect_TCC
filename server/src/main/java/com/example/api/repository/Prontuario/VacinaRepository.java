package com.example.api.repository.Prontuario;


import com.example.api.entity.Prontuario.VacinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacinaRepository extends JpaRepository<VacinaEntity, Long> {

    @Query(value = "SELECT * FROM vacina WHERE id_prontuario = ?1", nativeQuery = true)
    List<VacinaEntity> buscarVacinaPorId(Long id);
}
