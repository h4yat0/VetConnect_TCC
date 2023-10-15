package br.vetconnect.api.repository.Agendamento;

import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import br.vetconnect.api.form.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {


    @Query(value = "SELECT * FROM agendamento WHERE id_cliente = ?1", nativeQuery = true)
    List<AgendamentoEntity> buscarAgendamentos(Long id);

    @Query(value = "SELECT * FROM agendamento WHERE id_animal = ?1", nativeQuery = true)
    List<AgendamentoEntity> buscarAgenamentoAnimal(Long id);

    @Query(value = "SELECT * FROM agendamento WHERE id = ?1", nativeQuery = true)
    AgendamentoEntity buscarAgendamento(Long id);

    @Query(value = "select * from agendamento where data_agendada = ?1 and hora_agendada = ?2 and id_servico = ?3 and id_unidade = ?4", nativeQuery = true)
    AgendamentoEntity verificaDataHora(String dataAgendada, String horaAgendada, Long idServico, Long idUnidade);

    @Query(value = "select * from agendamento where data_agendada = ?1 and hora_agendada = ?2 and id != ?3", nativeQuery = true)
    AgendamentoEntity verificaIdDataHora(String dataAgendada, String horaAgendada, Long id);
}
