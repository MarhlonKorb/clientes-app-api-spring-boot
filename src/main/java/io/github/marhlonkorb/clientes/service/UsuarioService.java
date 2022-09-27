package io.github.marhlonkorb.clientes.service;

import io.github.marhlonkorb.clientes.model.entity.Usuario;
import io.github.marhlonkorb.clientes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Classe responsável por carregar os dados de acesso do usuário para efetuar a autenticação
 */
@Service
public class UsuarioService implements UserDetailsService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    /**
     * Método responsável por realizar a busca e validação pelo username no database
     * @param username
     * @return Optional<Usuario>
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.
                findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));
        return User.builder().
                username(usuario.getUsername()).
                password(usuario.getPassword()).
                roles("USER").build();
    }
}
