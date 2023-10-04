package br.vetconnect.api.repository.Imagens;

import br.vetconnect.api.entity.imagens.AnimalImagensEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalImagensRepository extends JpaRepository<AnimalImagensEntity, Long> {
}
