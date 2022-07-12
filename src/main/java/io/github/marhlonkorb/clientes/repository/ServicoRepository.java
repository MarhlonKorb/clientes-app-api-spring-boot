package io.github.marhlonkorb.clientes.repository;

import io.github.marhlonkorb.clientes.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
