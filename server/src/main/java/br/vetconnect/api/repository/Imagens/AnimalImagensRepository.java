package br.vetconnect.api.repository.Imagens;

import br.vetconnect.api.entity.imagens.AnimalImagensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Repository
public interface AnimalImagensRepository extends JpaRepository<AnimalImagensEntity, Long> {

    @Query(value = "SELECT * FROM img_unidade WHERE id_animal = ?1", nativeQuery = true)
    List<AnimalImagensEntity> buscarPorIdAnimal(Long id);



    @Query(value = "DELETE FROM img_animal WHERE id_animal = ?1", nativeQuery = true)
    @Transactional
    @Modifying
    void deletePorIdAnimal(Long id);
}
