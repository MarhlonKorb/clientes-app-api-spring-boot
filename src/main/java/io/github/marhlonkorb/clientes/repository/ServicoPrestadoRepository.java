package io.github.marhlonkorb.clientes.repository;

import io.github.marhlonkorb.clientes.model.entity.ServicoPrestado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {
    
    /**
     * Realiza pesquisa por nome de cliente e mes do serviço prestado
     * @param nome
     * @param mes
     * @return List<ServicoPrestado>
     */
    @Query("select s from ServicoPrestado s join s.cliente c where upper(c.nome) like upper( :nome ) and month(s.data) =:mes")
    List<ServicoPrestado> findByNomeClienteANdMes(@Param("nome") String nome, @Param("mes") Integer mes);
}
