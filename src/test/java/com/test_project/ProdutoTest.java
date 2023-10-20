package com.test_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ProdutoTest {
    private Produto produto1 = new Produto(1, "celular A12", 1300, "smartphone", 50, "Um celular", "SAMSUNG", 0);
    private Produto produto2 = new Produto(2, "Mouse Gamer HyperX", 160, "periféricos", 50, "Oferece aos jogadores o melhor em estilo e conteúdo, oferecendo extrema precisão graças a seu sensor Pixart 3389 e efeitos de iluminação RGB espetaculares em 360°", "HyperX ", 0);

    /**
     * Verifica se o método é capaz de buscar o produto no banco de dados corretamente. 
    * @throws Exception
     */
    @Test
    void buscarProdutoTest() throws Exception{
        Produto produto = Produto.buscaProduto(produto1.getId());

        //Produto está no banco de dados
        assertEquals(produto1, produto);

        Produto produto3 = new Produto(3, "Teclado Mecânico RGB", 80.50, "periféricos", 30, "Design elegante com retroiluminação RGB personalizável. Teclas mecânicas para uma experiência de digitação superior.", "Logitech", 10);

        produto = Produto.buscaProduto(produto3.getId());
        assertEquals(null, produto);
    }

    /**
     * Verifica se o método é capaz de listar todos os produtos do banco de dados corretamente. 
    * @throws Exception
     */
    @Test
    void listarProdutosTest() throws Exception{
        ArrayList<Produto> produtos = Produto.listarProdutos();

        assertTrue(produtos.contains(produto1));
        assertTrue(produtos.contains(produto2));
    }
}
