package io.github.marhlonkorb.clientes.rest.exception;


public class UsuarioCadastradoException extends RuntimeException{
    
    public UsuarioCadastradoException(String login){
        super("Usuário já cadastrado com o login \"" + login + "\".");
    }
}
