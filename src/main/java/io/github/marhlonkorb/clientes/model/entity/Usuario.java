package io.github.marhlonkorb.clientes.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique = true, name = "login")
    @NotEmpty(message = "{campo.username.obrigatorio}")
    private String username;
    
    @Column(unique = true, name = "senha")
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String password;
}
