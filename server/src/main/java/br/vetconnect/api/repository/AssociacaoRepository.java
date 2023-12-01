package br.vetconnect.api.repository;

import br.vetconnect.api.entity.UnidadeServico.AssociacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AssociacaoRepository extends JpaRepository<AssociacaoEntity, Long> {

    @Query(value = "SELECT tempo_servico FROM servico_unidade where unidade_id = ?1 and servico_id = ?2", nativeQuery = true)
    String buscaTempoServico(Long idUnidade, Long idServico);

}
