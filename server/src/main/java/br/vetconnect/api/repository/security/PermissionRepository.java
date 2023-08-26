package br.vetconnect.api.repository.security;

import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.entity.security.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {


}
