package io.github.marhlonkorb.clientes.config;

import io.github.marhlonkorb.clientes.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Classe responsável por gerenciar token de acesso do usuário
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UsuarioService usuarioService;
    
    /**
     * Método que utilizará a configuração da classe UserDetails para buscar os usuários na base de dados
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).
        passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    
    /**
     * Método responsável por gerenciar os tokens de acesso ao sistema
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.csrf().disable().cors().
                and().
                sessionManagement().
                // Método necessário, pois desabilita gerenciamento de sessão
                // e será utilizado o token do usuário por determinado tempo
                sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
