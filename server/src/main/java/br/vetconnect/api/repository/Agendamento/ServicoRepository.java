package br.vetconnect.api.repository.Agendamento;

import br.vetconnect.api.entity.UnidadeServico.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<ServicoEntity, Long> {


    @Query(value = "SELECT * from servico where id = ?1", nativeQuery = true)
    ServicoEntity buscarServico(Long id);

    @Query(value = "SELECT * FROM servico where id in (select servico_id from servico_unidade)", nativeQuery = true)
    List<ServicoEntity> buscarServicosComUnidade();
}
