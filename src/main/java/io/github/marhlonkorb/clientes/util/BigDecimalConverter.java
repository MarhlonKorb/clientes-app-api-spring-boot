package io.github.marhlonkorb.clientes.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Classe responsável por realizar a conversão de variáveis para o tipo desejado
 */
@Component
public class BigDecimalConverter {
    
    public BigDecimal converter(String value){
        if(value == null){
            return null;
        }
        value = value.replace(".","").replace(",",".");
        return new BigDecimal(value);
    }
}
