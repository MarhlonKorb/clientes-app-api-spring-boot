package io.github.marhlonkorb.clientes.rest;

import io.github.marhlonkorb.clientes.model.entity.Cliente;
import io.github.marhlonkorb.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

    /* Duas formas de injetar dependências: a primeira ou a segunda utilizando o método construtor da classe a ser injetada.*/
    
    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public List<Cliente> getListaClientes(){
        return repository.findAll();
    }
    
    @GetMapping("{id}")
    public Cliente findById(@PathVariable Integer id){
        return repository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    /* @RequestBody realiza o parse de uma requisição recebendo um objeto do tipo JSON para um objeto do tipo Cliente */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)/* Caso a solicitação seja concluída com sucesso, a aplicação retorna o status de CREATED para o cliente */
    public Cliente salvar(@RequestBody @Valid Cliente cliente){
        return repository.save(cliente);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado){
        repository.findById(id).
                map(cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    return repository.save(clienteAtualizado);
                }).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    /*O método foi criado para encontrar primeiramente o cliente pelo ID e caso não seja encontrado, lançará uma exception.*/
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id).
                map(cliente -> {
                    repository.delete(cliente);
                    /*retorno Void.TYPE inserido pela necessidade do método map ter algum tipo de retorno.*/
                    return Void.TYPE;
                }).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

}
