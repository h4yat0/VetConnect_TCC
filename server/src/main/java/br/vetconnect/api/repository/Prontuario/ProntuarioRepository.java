package br.vetconnect.api.repository.Prontuario;

import br.vetconnect.api.entity.Prontuario.ProntuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProntuarioRepository extends JpaRepository<ProntuarioEntity, Long> {


    @Query(value = "SELECT * FROM prontuario WHERE id = ?1", nativeQuery = true)
    ProntuarioEntity buscarProntuarioPorId(Long id);
}
