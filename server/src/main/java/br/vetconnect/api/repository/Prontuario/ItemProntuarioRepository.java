package br.vetconnect.api.repository.Prontuario;

import br.vetconnect.api.entity.Prontuario.ItemProntuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemProntuarioRepository extends JpaRepository<ItemProntuarioEntity, Long> {
}
