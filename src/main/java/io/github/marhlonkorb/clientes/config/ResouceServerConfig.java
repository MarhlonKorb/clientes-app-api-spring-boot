package io.github.marhlonkorb.clientes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Classe responsável pelo gerenciamento de URL que terão acesso de acordo com a ROLE do usuário
 */
@Configuration
@EnableResourceServer
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {
    
    @Override
    public void configure (HttpSecurity http) throws Exception {
    http.authorizeRequests().
            // Os dois asteríscos informam que todo usuário que acessar o sistema
            // precisa ser um usuário do tipo USER
            // antMatchers("/api/clientes/**").
            // ROLES de usuário precisam ser em letras maiúsculas
            // hasAnyRole("USER").
            // Método que pode inserir todos os usuários que podem ter esse tipo de acesso ao sistema
            // antMatchers("/api/usuarios").permitAll();
        
        antMatchers("/api/usuarios").permitAll().
            antMatchers("/api/clientes/**", "/api/servicos-prestados/**").
            authenticated().
            // denyAll() serve para certificar que apenas as API(URL) que foram configuradas tem realmente acesso ao sistema
            anyRequest().denyAll();
    }
}
