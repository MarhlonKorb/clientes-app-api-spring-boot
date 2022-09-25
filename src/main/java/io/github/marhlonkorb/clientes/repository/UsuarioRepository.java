package io.github.marhlonkorb.clientes.repository;

import io.github.marhlonkorb.clientes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
