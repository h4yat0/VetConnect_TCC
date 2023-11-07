package br.vetconnect.api.repository.Agendamento;


import br.vetconnect.api.entity.Agendamento.FilaEsperaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilaDeEsperaRepository extends JpaRepository<FilaEsperaEntity, Long> {
}
