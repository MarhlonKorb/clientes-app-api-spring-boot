package io.github.marhlonkorb.clientes.repository;

import io.github.marhlonkorb.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
