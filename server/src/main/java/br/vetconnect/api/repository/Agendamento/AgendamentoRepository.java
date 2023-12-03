package br.vetconnect.api.repository.Agendamento;

import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import br.vetconnect.api.form.AgendamentoEmail;
import br.vetconnect.api.form.EmailFilaDeEspera;
import br.vetconnect.api.form.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

//    @Query(value = "select new br.vetconnect.api.form.EmailFilaDeEspera( age.dataAgendada, age.horaAgendada, cli.nome as nomeCliente, ani.nome as nomeAnimal, ser.nome as nomeServico, uni.nome as nomeUnidade, cli.email as emailCliente) " +
//            "FROM AgendamentoEntity as age " +
//            "LEFT JOIN FilaEsperaEntity as fe ON fe.idAgendamentoDesejado = age.id " +
//            "LEFT JOIN AgendamentoEntity as age2 ON fe.idAgendamento = age2.id " +
//            "LEFT JOIN servico as ser ON ser.id = age2.idServico " +
//            "LEFT JOIN unidade as uni ON uni.id = age2.idUnidade " +
//            "LEFT JOIN cliente as cli ON cli.id = age2.idCliente " +
//            "LEFT JOIN animal as ani ON ani.id = age2.idAnimal " +
//            "where age.id = :agend and ativo = 1")
//    EmailFilaDeEspera criaEmail(@Param("agend")Long id);


    @Query(value = "select new br.vetconnect.api.form.AgendamentoEmail( cli.nome as nomeCliente, ani.nome as nomeAnimal, ser.nome as nomeServico, uni.nome as nomeUnidade, age.horaAgendada as horaAgendamento, uni.rua as ruaUnidade, uni.bairro as bairroUnidade, uni.cidade as cidadeUnidade, cli.email as emailCliente, uni.contato as contatoUnidade, age.dataAgendada as dataAgendamento )" +
            "FROM AgendamentoEntity as age " +
            "LEFT JOIN cliente as cli ON cli.id = age.idCliente " +
            "LEFT JOIN animal as ani ON ani.id = age.idAnimal " +
            "LEFT JOIN servico as ser ON ser.id = age.idServico " +
            "LEFT JOIN unidade as uni ON uni.id = age.idUnidade " +
            "WHERE (age.dataAgendada = :dataAtual OR age.dataAgendada = :dataDaquiDoisDias) AND age.status = '1'")
    List<AgendamentoEmail> criaNotificaoEmail(@Param("dataAtual") String dataAtual, @Param("dataDaquiDoisDias") String dataDaquiDoisDias);

    @Query(value = "SELECT hora_agendada FROM agendamento where id_unidade = ?1 and id_servico = ?2 and data_agendada = ?3 and status = 1", nativeQuery = true)
    List<String> buscarHorariosDisponiveis(Long idUnidade, Long idServico, String data);
}
