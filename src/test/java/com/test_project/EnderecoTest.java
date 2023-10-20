package com.test_project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EnderecoTest {

    private Endereco endereco = new Endereco(0, "rn", "Pau dos Ferros", "Vila Bela", "Rua das Acácias", "casa", "62980-000", 404);
    
    /**
     * Verifica se o método está validando os estados corretamente.
     */
    @Test
    void setEstadoTest(){

        // Caso de sucesso: estado válido
        String estado1 = "rn";

        // Caso de erro: estado inválido, com poucos caracteres
        String estado2 = "a";

        // Caso de erro: estado vazio
        String estado3 = "";

        // Caso de erro: estado nulo
        String estado4 = null;

        // Caso de erro: estado inválido, com muitos caracteres
        String estado5 = "aBeDfGhIJkLmNpQrStUvWxYzCcFwHjKlNpOqRsVtZbDfGhXjLmNpRtVwYzCcFaBeDfGhIJkLmNpQrStUvWxYzCcFwHjKlNpOqRsVt";

        assertTrue(endereco.setEstado(estado1));
        assertFalse(endereco.setEstado(estado2));
        assertFalse(endereco.setEstado(estado3));
        assertFalse(endereco.setEstado(estado4));
        assertFalse(endereco.setEstado(estado5));
    }

    /**
     * Verifica se o método está validando as cidades corretamente.
     */
    @Test
    void setCidadeTest(){

        // Caso de sucesso: cidade válida
        String cidade1 = "Pau dos Ferros";

        // Caso de erro: cidade inválida, com poucos caracteres
        String cidade2 = "a";

        // Caso de erro: cidade vazia
        String cidade3 = "";

        // Caso de erro: cidade nula
        String cidade4 = null;

        // Caso de erro: cidade com números
        String cidade5 = "Pau dos Ferros 1";

        // Caso de erro: cidade com símbolos
        String cidade6 = "Pau dos Ferros!!";

        // Caso de erro: cidade inválida, com muitos caracteres
        String cidade7 = "aBeDfGhIJkLmNpQrStUvWxYzCcFwHjKlNpOqRsVtZbDfGhXjLmNpRtVwYzCcFaBeDfGhIJkLmNpQrStUvWxYzCcFwHjKlNpOqRsVt";

        assertTrue(endereco.setCidade(cidade1));
        assertFalse(endereco.setCidade(cidade2));
        assertFalse(endereco.setCidade(cidade3));
        assertFalse(endereco.setCidade(cidade4));
        assertFalse(endereco.setCidade(cidade5));
        assertFalse(endereco.setCidade(cidade6));
        assertFalse(endereco.setCidade(cidade7));
    }

    /**
     * Verifica se o método está validando os bairros corretamente.
     */
    @Test
    void setBairroTest(){

        // Caso de sucesso: bairro válido
        String bairro1 = "Vila Bela";

        // Caso de erro: bairro inválido, com poucos caracteres
        String bairro2 = "a";

        // Caso de erro: bairro vazio
        String bairro3 = "";

        // Caso de erro: bairro nulo
        String bairro4 = null;

        // Caso de erro: bairro com números
        String bairro5 = "Vila Bela 1";

        // Caso de erro: bairro com símbolos
        String bairro6 = "Vila Bela!!";

        // Caso de erro: bairro inválido, com muitos caracteres
        String bairro7 = "aBeDfGhIJkLmNpQrStUvWxYzCcFwHjKlNpOqRsVtZbDfGhXjLmNpRtVwYzCcFaBeDfGhIJkLmNpQrStUvWxYzCcFwHjKlNpOqRsVt";

        assertTrue(endereco.setBairro(bairro1));
        assertFalse(endereco.setBairro(bairro2));
        assertFalse(endereco.setBairro(bairro3));
        assertFalse(endereco.setBairro(bairro4));
        assertFalse(endereco.setBairro(bairro5));
        assertFalse(endereco.setBairro(bairro6));
        assertFalse(endereco.setBairro(bairro7));
    }

    /**
     * Verifica se o método está validando as ruas corretamente.
     */
    @Test
    void setRuaTest(){

        // Caso de sucesso: rua válida
        String rua1 = "Rua das Acácias";

        // Caso de erro: rua inválida, com poucos caracteres
        String rua2 = "a";

        // Caso de erro: rua vazia
        String rua3 = "";

        // Caso de erro: rua nula
        String rua4 = null;

        // Caso de erro: rua com números
        String rua5 = "Rua das Acácias 1";

        // Caso de erro: rua com símbolos
        String rua6 = "Rua das Acácias!!";

        // Caso de erro: rua inválida, com muitos caracteres
        String rua7 = "aBeDfGhIJkLmNpQrStUvWxYzCcFwHjKlNpOqRsVtZbDfGhXjLmNpRtVwYzCcFaBeDfGhIJkLmNpQrStUvWxYzCcFwHjKlNpOqRsVt";

        assertTrue(endereco.setRua(rua1));
        assertFalse(endereco.setRua(rua2));
        assertFalse(endereco.setRua(rua3));
        assertFalse(endereco.setRua(rua4));
        assertFalse(endereco.setRua(rua5));
        assertFalse(endereco.setRua(rua6));
        assertFalse(endereco.setRua(rua7));
    }

    /**
     * Verifica se o método está validando os complementos corretamente.
     */
    @Test
    void setComplementoTest(){

        // Caso de sucesso: complemento válido
        String complemento1 = "casa";

        // Caso de erro: complemento inválido, com poucos caracteres
        String complemento2 = "a";

        // Caso de erro: complemento vazio
        String complemento3 = "";

        // Caso de erro: complemento nulo
        String complemento4 = null;

        // Caso de erro: complemento com números
        String complemento5 = "casa 1";

        // Caso de erro: complemento com símbolos
        String complemento6 = "casa!!";

        // Caso de erro: complemento inválido, com muitos caracteres
        String complemento7 = "abcdefghijklmnopq";

        assertTrue(endereco.setComplemento(complemento1));
        assertFalse(endereco.setComplemento(complemento2));
        assertFalse(endereco.setComplemento(complemento3));
        assertFalse(endereco.setComplemento(complemento4));
        assertFalse(endereco.setComplemento(complemento5));
        assertFalse(endereco.setComplemento(complemento6));
        assertFalse(endereco.setComplemento(complemento7));
    }

    /**
     * Verifica se o método está validando os ceps corretamente.
     */
    @Test
    void setCEPTest(){

        // Caso de sucesso: cep válido
        String cep1 = "62980-000";

        // Caso de erro: cep inválido
        String cep2 = "12345678";

        // Caso de erro: cep vazio
        String cep3 = "";

        // Caso de erro: cep vazio
        String cep4 = null;

        assertTrue(endereco.setCep(cep1));
        assertFalse(endereco.setCep(cep2));
        assertFalse(endereco.setCep(cep3));
        assertFalse(endereco.setCep(cep4));
    }

    /**
     * Verifica se o método está validando os numeros corretamente.
     */
    @Test
    void setNumeroTest(){

        // Caso de sucesso: numero válido
        int numero1 = 0;

        // Caso de erro: numero válido
        int numero2 = 10;

        // Caso de erro: numero inválido
        int numero3 = -1;

        assertTrue(endereco.setNumero(numero1));
        assertTrue(endereco.setNumero(numero2));
        assertFalse(endereco.setNumero(numero3));
    }
}
