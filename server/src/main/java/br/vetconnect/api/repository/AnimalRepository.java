package br.vetconnect.api.repository;

import br.vetconnect.api.entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {


    @Query(value = "SELECT * FROM animal WHERE id_cliente = ?1", nativeQuery = true)
    List<AnimalEntity> buscarAnimais(Long id);

    @Query(value = "SELECT * FROM animal WHERE id = ?1", nativeQuery = true)
    AnimalEntity buscarAnimal(Long id);

}
