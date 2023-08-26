package br.vetconnect.api.entity.security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@Table(name = "permission")
public class PermissionEntity implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public PermissionEntity(){}

    @Override
    public String getAuthority() {
        return this.description;
    }
}
