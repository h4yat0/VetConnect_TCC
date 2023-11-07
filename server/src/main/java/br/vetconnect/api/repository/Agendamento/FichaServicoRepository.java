package br.vetconnect.api.repository.Agendamento;

import br.vetconnect.api.entity.Agendamento.FichaServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaServicoRepository extends JpaRepository<FichaServicoEntity, Long> {

    @Query(value = "SELECT * FROM ficha_servico where id_agendamento = ?1", nativeQuery = true)
    FichaServicoEntity buscaPorIdAgendamento(Long id);
}
