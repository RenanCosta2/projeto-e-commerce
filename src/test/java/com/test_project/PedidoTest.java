package com.test_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoTest {

    private Carrinho carrinho;
    private Produto produto1;
    private Produto produto2;
    private Usuario cliente;
    private Pedido pedido;
    
    @BeforeEach
    void setUp(){
        LocalDate data = LocalDate.of(2003, 7, 27);
        Usuario cliente2 = new Usuario("FRANCISCO RENAN LEITE DA COSTA", "07769719305", "renanleitedacosta@gmail.com", null, "renan123", "RenanCosta", data);
        cliente2.cadastrar();

        this.cliente = Usuario.login("RenanCosta", "renan123");
        this.carrinho = new Carrinho(cliente);
        this.produto1 = new Produto(2, "Mouse Gamer HyperX", 160, "periféricos", 50, "Oferece aos jogadores o melhor em estilo e conteúdo, oferecendo extrema precisão graças a seu sensor Pixart 3389 e efeitos de iluminação RGB espetaculares em 360°", "HyperX ", 0);
        this.produto2 = new Produto(1, "celular A12", 1300, "smartphone", 50, "Um celular", "SAMSUNG", 0);
        carrinho.esvaziarCarrinho();
        this.carrinho.adicionarItem(produto1, 1);
        this.carrinho.adicionarItem(produto1, 1);
        this.carrinho.adicionarItem(produto2, 1);
        

        this.pedido = this.carrinho.realizarPedido();
    }

    /**
     * Verifica se o pedido está sendo finalizado corretamente.
     * @throws Exception
     */
    @Test
    void finalizarCompraTest() throws Exception{
        this.pedido.setTipoEnvio("envio padrão");
        this.pedido.setFormaPagamento("pix");

        Endereco endereco = new Endereco("rn", "Pau dos Ferros", "Vila Bela", "Rua das Acácias", "casa", "62980-000", 404);

        this.cliente.adicionarEndereco(endereco);
        
        this.pedido.setEndereco(cliente.getEnderecos().get(0));

        assertTrue(this.pedido.finalizarCompra(this.carrinho));

        try {
            Connection connection = PostgreSQLConnection.getInstance().getConnection();

            PreparedStatement pstmt = connection.prepareStatement(
                "DELETE FROM " +
                "produtos_pedido WHERE id_pedido = ?"
            );

            pstmt.setInt(1, this.pedido.getNumPedido());
            pstmt.executeUpdate();

            pstmt = connection.prepareStatement(
                "DELETE FROM " +
                "pedido WHERE id_pedido = ?"
            );

            pstmt.setInt(1, this.pedido.getNumPedido());
            pstmt.executeUpdate();

            this.cliente.excluirUsuario(cliente);

        } catch (Exception e) {
            
        }

    }

    /**
     * Verifica se um pedido que já 
     * @throws Exception
     */
    @Test
    void cancelarPedidoTest() throws Exception{

        this.pedido.setTipoEnvio("envio padrão");
        this.pedido.setFormaPagamento("pix");

        Endereco endereco = new Endereco("rn", "Pau dos Ferros", "Vila Bela", "Rua das Acácias", "casa", "62980-000", 404);

        this.cliente.adicionarEndereco(endereco);
        
        this.pedido.setEndereco(cliente.getEnderecos().get(0));

        assertTrue(this.pedido.finalizarCompra(this.carrinho));

        this.pedido.cancelarPedido();

        try {
            Connection connection = PostgreSQLConnection.getInstance().getConnection();

            PreparedStatement pstmt = connection.prepareStatement(
                "SELECT status FROM " +
                "pedido WHERE id_pedido = ?"
            );

            pstmt.setInt(1, this.pedido.getNumPedido());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                assertEquals("cancelado", rs.getString(1));
            }

            pstmt = connection.prepareStatement(
                "DELETE FROM " +
                "produtos_pedido WHERE id_pedido = ?"
            );

            pstmt.setInt(1, this.pedido.getNumPedido());
            pstmt.executeUpdate();

            pstmt = connection.prepareStatement(
                "DELETE FROM " +
                "pedido WHERE id_pedido = ?"
            );

            pstmt.setInt(1, this.pedido.getNumPedido());
            pstmt.executeUpdate();

        } catch (Exception e) {
            
        }
        
    }

    /**
     * Verifica se o método está validando os status corretamente.
     */
    @Test
    void setStatusTest(){
        
        //Caso de sucesso: status válido
        String status1 = "Atrasado";

        //Caso de erro: status inválido
        String status2 = "em aguardo";

        //Caso de erro: status nulo
        String status3 = null;

        assertTrue(this.pedido.setStatus(status1));
        assertFalse(this.pedido.setStatus(status2));
        assertFalse(this.pedido.setStatus(status3));
    }

    /**
     * Verifica se o método está validando as formas de pagamento corretamente.
     */
    @Test
    void setFormaPagamentoTest(){
        
        //Caso de sucesso: forma de pagamento válido
        String formaPagamento1 = "PIX";

        //Caso de erro: forma de pagamento inválido
        String formaPagamento2 = "cheque";

        //Caso de erro: forma de pagamento nulo
        String formaPagamento3 = null;

        assertTrue(this.pedido.setFormaPagamento(formaPagamento1));
        assertFalse(this.pedido.setFormaPagamento(formaPagamento2));
        assertFalse(this.pedido.setFormaPagamento(formaPagamento3));
    }

    /**
     * Verifica se o método está validando os endereços corretamente.
     */
    @Test
    void setEnderecoTest(){
        
        //Caso de sucesso: endereço válido
        Endereco endereco1 = new Endereco("São Paulo", "São Paulo", "Vila Mariana", "Rua da Amostra", "Apto 102", "04101-001", 123);

        ArrayList<Endereco> enderecos = new ArrayList<>();
        enderecos.add(new Endereco("Rio de Janeiro", "Rio de Janeiro", "Copacabana", "Avenida da Praia", "Casa 5", "22010-001", 456));
        this.cliente.setEnderecos(enderecos);
        //Caso de sucesso: endereço válido
        Endereco endereco2 = this.cliente.getEnderecos().get(0);

        //Caso de erro: endereço nulo
        Endereco endereco3 = null;

        assertTrue(this.pedido.setEndereco(endereco1));
        assertTrue(this.pedido.setEndereco(endereco2));
        assertFalse(this.pedido.setEndereco(endereco3));
    }

    /**
     * Verifica se o método está validando os tipos de envio corretamente.
     */
    @Test
    void setTipoEnvioTest(){
        
        //Caso de sucesso: tipo de envio válido
        String tipoEnvio1 = "Envio Padrão";

        //Caso de erro: tipo de envio inválido
        String tipoEnvio2 = "Entrega no mesmo dia";

        //Caso de erro: tipo de envio nulo
        String tipoEnvio3 = null;

        assertTrue(this.pedido.setTipoEnvio(tipoEnvio1));
        assertFalse(this.pedido.setTipoEnvio(tipoEnvio2));
        assertFalse(this.pedido.setTipoEnvio(tipoEnvio3));
    }

    /**
     * Verifica se o método está validando os custos de envio corretamente.
     */
    @Test
    void setCustoEnvioTest(){

        Endereco endereco1 = new Endereco("São Paulo", "São Paulo", "Vila Mariana", "Rua da Amostra", "Apto 102", "04101-001", 123);
        this.pedido.setEndereco(endereco1);

        //Caso de erro: o tipo de envio não foi definido ainda
        assertFalse(this.pedido.setCustoEnvio());

        //Caso de sucesso: o tipo de envio foi definido
        String tipoEnvio1 = "Envio Padrão";
        this.pedido.setTipoEnvio(tipoEnvio1);
        assertTrue(this.pedido.setCustoEnvio());

        //Caso de sucesso: o custo do envio foi calculado corretamente
        assertEquals(10 + pedido.getValorFrete(), pedido.getCustoEnvio());

        //Caso de sucesso: o tipo de envio foi definido
        String tipoEnvio2 = "Envio Expresso";
        this.pedido.setTipoEnvio(tipoEnvio2);
        assertTrue(this.pedido.setCustoEnvio());

        //Caso de sucesso: o custo do envio foi calculado corretamente
        assertEquals(20 + pedido.getValorFrete(), pedido.getCustoEnvio());

    }

    /**
     * Verifica se o método está validando o valor dos produtos corretamente.
     */
    @Test
    void setValorProdutosTest(){
        
        pedido.setValorProdutos();

        //Verifica se o valor total dos produtos é o mesmo valor do total dos produtos do carrinho que gerou esse pedido
        assertEquals(carrinho.calcularTotal(), pedido.getValorProdutos());

    }

    @AfterEach
    void tearDown() throws Exception {
        cliente.excluirUsuario(this.cliente);
    }
}
