package io.github.marhlonkorb.clientes.rest;

import io.github.marhlonkorb.clientes.model.entity.Usuario;
import io.github.marhlonkorb.clientes.repository.UsuarioRepository;
import io.github.marhlonkorb.clientes.rest.exception.UsuarioCadastradoException;
import io.github.marhlonkorb.clientes.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){
        try {
            usuarioService.salvar(usuario);
        }catch (UsuarioCadastradoException e){
            // Utilizar exception na camada de controller
            // pois a camada de service não tem responsabilidade de tratar erros da API,
            // apenas regras de negócio
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
