package br.vetconnect.api.repository.Agendamento.FiilaEspera;


import br.vetconnect.api.entity.Agendamento.FilaEsperaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilaDeEsperaRepository extends JpaRepository<FilaEsperaEntity, Long> {



    @Query(value = "SELECT * FROM fila_espera WHERE id_agendamento_desejado = ?1 limit 1",nativeQuery = true)
    FilaEsperaEntity buscaPorIdAgendamentoDesejado(Long idAgendamento);


    @Query(value = "SELECT * FROM fila_espera WHERE id_unidade = ?1 and id_servico = ?2 and data_agendada = ?3 and hora_agendada = ?4", nativeQuery = true)
    List<FilaEsperaEntity> buscarPorIdUnidadeIdServicoDataHora(Long idUnidade, Long idServico, String dataAgendada, String horaAgendada);
}
