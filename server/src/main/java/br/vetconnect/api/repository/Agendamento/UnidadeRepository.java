package br.vetconnect.api.repository.Agendamento;


import br.vetconnect.api.entity.UnidadeServico.UnidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeRepository extends JpaRepository<UnidadeEntity,Long> {

    @Query(value = "SELECT * from unidade where id = ?1", nativeQuery = true)
    UnidadeEntity buscarUnidade(Long id);

    @Query(value = "SELECT * from unidade where cnpj = ?1", nativeQuery = true)
    UnidadeEntity buscarUnidadePorCnpj(String cnpj);

    @Query(value = "select * from unidade where id in (select unidade_id from servico_unidade);", nativeQuery = true)
    List<UnidadeEntity> buscarUnidadesComServico();

}
