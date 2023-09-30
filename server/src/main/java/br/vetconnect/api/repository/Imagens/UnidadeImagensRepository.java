package br.vetconnect.api.repository.Imagens;

import br.vetconnect.api.entity.imagens.UnidadeImagensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeImagensRepository extends JpaRepository<UnidadeImagensEntity, Long> {
}
