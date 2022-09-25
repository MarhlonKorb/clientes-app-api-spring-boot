package io.github.marhlonkorb.clientes.rest;

import io.github.marhlonkorb.clientes.model.entity.Usuario;
import io.github.marhlonkorb.clientes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    
    @Autowired
    private final UsuarioRepository usuarioRepository;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
