package br.vetconnect.api.repository.Agendamento.FiilaEspera;


import br.vetconnect.api.entity.Agendamento.FilaEsperaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FilaDeEsperaRepository extends JpaRepository<FilaEsperaEntity, Long> {



    @Query(value = "SELECT * FROM fila_espera WHERE id_agendamento_desejado = ?1 limit 1",nativeQuery = true)
    FilaEsperaEntity buscaPorIdAgendamentoDesejado(Long idAgendamento);
}
