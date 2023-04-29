package com.example.api.repository.Prontuario;

import com.example.api.entity.Prontuario.ItemProntuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemProntuarioRepository extends JpaRepository<ItemProntuarioEntity, Long> {
}
