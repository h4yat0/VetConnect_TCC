package br.vetconnect.api.repository.Agendamento;

import br.vetconnect.api.entity.Agendamento.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<ServicoEntity, Long> {


    @Query(value = "SELECT * from servico where id = ?1", nativeQuery = true)
    ServicoEntity buscarServico(Long id);
}