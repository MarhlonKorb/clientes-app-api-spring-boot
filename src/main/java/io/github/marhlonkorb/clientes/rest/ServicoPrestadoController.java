package io.github.marhlonkorb.clientes.rest;

import io.github.marhlonkorb.clientes.model.entity.Cliente;
import io.github.marhlonkorb.clientes.model.entity.ServicoPrestado;
import io.github.marhlonkorb.clientes.repository.ClienteRepository;
import io.github.marhlonkorb.clientes.repository.ServicoPrestadoRepository;
import io.github.marhlonkorb.clientes.rest.dto.ServicoPrestadoDto;
import io.github.marhlonkorb.clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor // Remove a necessidade de construtor e annotation para a injeção de dependências
public class ServicoPrestadoController {
    
    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final ClienteRepository clienteRepository;
    private final BigDecimalConverter bigDecimalConverter;
    
    /**
     * Realiza o salvamento do serviço prestado para o cliente
     * @param dto
     * @return servicoPrestado
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody ServicoPrestadoDto dto){
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();
        Cliente cliente = clienteRepository.
                findById(idCliente).
                orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente Inexistente!"));
        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));
        return servicoPrestadoRepository.save(servicoPrestado);
    }
    
    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
            // Default value lista todos os servicos prestados caso não seja passado
            // nome como parâmetro na requisação
    ){
        return servicoPrestadoRepository.findByNomeClienteANdMes("%" + nome + "%", mes);
    }
    
}
