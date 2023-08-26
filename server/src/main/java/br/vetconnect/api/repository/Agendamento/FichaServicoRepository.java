package br.vetconnect.api.repository.Agendamento;

import br.vetconnect.api.entity.Agendamento.FichaServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaServicoRepository extends JpaRepository<FichaServicoEntity, Long> {
}
