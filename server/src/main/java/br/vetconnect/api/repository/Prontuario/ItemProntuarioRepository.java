package br.vetconnect.api.repository.Prontuario;

import br.vetconnect.api.entity.Prontuario.ItemProntuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemProntuarioRepository extends JpaRepository<ItemProntuarioEntity, Long> {

    @Query(value = "SELECT * FROM item_prontuario WHERE id_prontuario = ?1", nativeQuery = true)
    List<ItemProntuarioEntity> procuraPorId(Long id);
}
