package io.github.marhlonkorb.clientes.rest;

import io.github.marhlonkorb.clientes.model.entity.Cliente;
import io.github.marhlonkorb.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    /*Duas formas de injetar dependências: a primeira ou a segunda utilizando o método construtor da classe a ser injetada.*/
    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{id}")
    public Cliente buscarPorId(@PathVariable Integer id){
        return repository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /*@RequestBody realiza o parse de uma requisição recebendo um objeto do tipo JSON para um objeto do tipo Cliente*/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)/*Caso a solicitação seja concluída com sucesso, a aplicação retorna o status de CREATED para o cliente*/
    public Cliente salvar(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    /*O método foi criado para encontrar primeiramente o cliente pelo ID e caso não seja encontrado, lançará uma exception.*/
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id).
                map(cliente -> {
                    repository.delete(cliente);
                    /*retorno Void.TYPE inserido pela necessidade do método map ter algum tipo de retorno.*/
                    return Void.TYPE;
                }).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
