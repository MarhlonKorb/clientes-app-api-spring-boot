package io.github.marhlonkorb.clientes.repository;

import io.github.marhlonkorb.clientes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);
    
    /**
     * Faz um select na tabela usuario e retorna true caso exista
     * @param username
     * @return
     */
    boolean existsByUsername (String username);
}
