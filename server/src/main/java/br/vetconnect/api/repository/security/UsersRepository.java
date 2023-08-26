package br.vetconnect.api.repository.security;

import br.vetconnect.api.entity.security.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    @Query("SELECT u FROM UsersEntity u WHERE u.userName =:userName")
    UsersEntity findByUsername(@Param("userName") String userName);

    @Query(value = "SELECT * FROM Users WHERE tipo = 1 and id_tipo = ?1", nativeQuery = true)
    UsersEntity findByTipoEId(long id);
}
