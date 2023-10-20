package com.test_project;

import org.junit.jupiter.api.Test;
import java.sql.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class UsuarioTest {

    private Usuario cliente;

    @BeforeEach
    void setUp(){
        LocalDate data = LocalDate.of(2003, 7, 27);
        cliente = new Usuario("FRANCISCO RENAN LEITE DA COSTA", "07769719305", "renanleitedacosta@gmail.com", null, "renan123", "RenanCosta", data);
    }

    /**
     * Verifica se o método cadastra o usuário corretamente no banco de dados.
     * @throws Exception
     */
    @Test
    void cadastrarTest() throws Exception{

        try {
            Connection connection = PostgreSQLConnection.getInstance().getConnection();
            
            this.cliente.cadastrar();

            PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM " + 
                "cliente WHERE cpf = ?");

            pstmt.setString(1, this.cliente.getCpf());

            ResultSet rs = pstmt.executeQuery();

            Usuario usuario = null;

            while (rs.next()) {
                usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getString(6), rs.getDate(8).toLocalDate());
            }

            //Teste para verificar se o cliente foi cadastrado com sucesso.
            assertEquals(this.cliente, usuario);

            LocalDate data = LocalDate.of(2003, 7, 27);

            Usuario clienteCPF = new Usuario("FRANCISCO RENAN LEITE DA COSTA", "07769719305", "email@gmail.com", null, "renan123", "usuario", data);

            /*
            Teste para verificar se o método bloqueou o cadastro corretamente,
            tendo em vista que o usuário que está sendo cadastrado possui um CPF que já está cadastrado.
            */
            assertFalse(clienteCPF.cadastrar());

            Usuario clienteEmail = new Usuario("FRANCISCO RENAN LEITE DA COSTA", "418.256.950-40", "renanleitedacosta@gmail.com", null, "renan123", "usuario", data);

            /*
            Teste para verificar se o método bloqueou o cadastro corretamente,
            tendo em vista que o usuário que está sendo cadastrado possui um email que já está cadastrado.
            */
            assertFalse(clienteEmail.cadastrar());

            Usuario clienteUsuario = new Usuario("FRANCISCO RENAN LEITE DA COSTA", "418.256.950-40", "email@gmail.com", null, "renan123", "RenanCosta", data);

            /*
            Teste para verificar se o método bloqueou o cadastro corretamente,
            tendo em vista que o usuário que está sendo cadastrado possui um usuário que já está cadastrado.
            */
            assertFalse(clienteUsuario.cadastrar());
            
            cliente.excluirUsuario(this.cliente);

        } catch (Exception e) {
            throw e;
        }

    }
    
    /**
     * Verifica se o método efetua o login do usuário corretamente.
     * @throws Exception
     */
    @Test
    void loginTest() throws Exception{

        this.cliente.cadastrar();

        Usuario cliente2 = Usuario.login("RenanCosta", "renan123");

        this.cliente.setId(cliente2.getId());

        assertEquals(this.cliente, cliente2);

        Usuario cliente3 = Usuario.login("zezinho123", "coxinha123");

        assertNull(cliente3);

        this.cliente.excluirUsuario(this.cliente);

    }

    /**
     * Verifica se o método cadastra o endereço corretamente.
     * @throws Exception
     */
    @Test
    void adicionarEnderecoTest() throws Exception{

        try {
            Connection connection = PostgreSQLConnection.getInstance().getConnection();
            
            this.cliente.cadastrar();

            this.cliente = Usuario.login("RenanCosta", "renan123");

            Endereco endereco = new Endereco("rn", "Pau dos Ferros", "Vila Bela", "Rua das Acácias", "casa", "62980-000", 404);

            this.cliente.adicionarEndereco(endereco);

            assertTrue(this.cliente.getEnderecos().contains(endereco));

            PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM " + 
                "endereco WHERE id_endereco = ?");

            pstmt.setInt(1, this.cliente.getEnderecos().get(0).getId());

            ResultSet rs = pstmt.executeQuery();

            Endereco endereco2 = null;

            if (rs.next()) {
                endereco2 = new Endereco(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(8), rs.getString(9), rs.getInt(7));
            }

            assertEquals(this.cliente.getEnderecos().get(0), endereco2);

            pstmt = connection.prepareStatement(
                "DELETE FROM " + 
                "endereco WHERE id_endereco = ?");

                pstmt.setInt(1, this.cliente.getEnderecos().get(0).getId());
                pstmt.executeUpdate();
            
            cliente.excluirUsuario(this.cliente);

        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * Verifica se o método cadastra o endereço corretamente.
     * @throws Exception
     */
    @Test
    void excluirEnderecoTest() throws Exception{

        try {
            
            this.cliente.cadastrar();

            this.cliente = Usuario.login("RenanCosta", "renan123");

            Endereco endereco = new Endereco("rn", "Pau dos Ferros", "Vila Bela", "Rua das Acácias", "casa", "62980-000", 404);

            this.cliente.adicionarEndereco(endereco);

            assertTrue(cliente.excluirEndereco(endereco.getId(), cliente));

            Carrinho carrinho = new Carrinho(cliente);
            Produto produto1 = new Produto(2, "Mouse Gamer HyperX", 160, "periféricos", 50, "Oferece aos jogadores o melhor em estilo e conteúdo, oferecendo extrema precisão graças a seu sensor Pixart 3389 e efeitos de iluminação RGB espetaculares em 360°", "HyperX ", 0);
            Produto produto2 = new Produto(1, "celular A12", 1300, "smartphone", 50, "Um celular", "SAMSUNG", 0);

            carrinho.adicionarItem(produto1, 3);
            carrinho.adicionarItem(produto2, 3);

            Pedido pedido = carrinho.realizarPedido();

            this.cliente.adicionarEndereco(endereco);

            pedido.setTipoEnvio("envio padrão");
            pedido.setFormaPagamento("pix");
            pedido.setEndereco(cliente.getEnderecos().get(0));

            pedido.finalizarCompra(carrinho);

            assertFalse(cliente.excluirEndereco(endereco.getId(), cliente));
            
            cliente.excluirUsuario(this.cliente);

        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * Verifica se o método efetua o login do usuário corretamente.
     * @throws Exception
     */ 
    @Test
    void excluirUsuarioTest() throws Exception{

        this.cliente.cadastrar();

        assertTrue(this.cliente.excluirUsuario(this.cliente));

        try {
            Connection connection = PostgreSQLConnection.getInstance().getConnection();

            PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM " + 
                "cliente WHERE cpf = ?");

            pstmt.setString(1, this.cliente.getCpf());

            ResultSet rs = pstmt.executeQuery();

            assertFalse(rs.next());

        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * Verifica se o método retorna a lista de todos os pedidos do usuário corretamente.
     * @throws Exception
     */ 
    @Test
    void listarPedidosTest() throws Exception{

        try {

            this.cliente.cadastrar();

            this.cliente = Usuario.login("RenanCosta", "renan123");

            Carrinho carrinho = new Carrinho(cliente);
            Produto produto1 = new Produto(2, "Mouse Gamer HyperX", 160, "periféricos", 50, "Oferece aos jogadores o melhor em estilo e conteúdo, oferecendo extrema precisão graças a seu sensor Pixart 3389 e efeitos de iluminação RGB espetaculares em 360°", "HyperX ", 0);
            Produto produto2 = new Produto(1, "celular A12", 1300, "smartphone", 50, "Um celular", "SAMSUNG", 0);
            carrinho.esvaziarCarrinho();
            carrinho.adicionarItem(produto1, 1);
            carrinho.adicionarItem(produto1, 1);
            carrinho.adicionarItem(produto2, 1);
            

            Pedido pedido = carrinho.realizarPedido();

            pedido.setTipoEnvio("envio padrão");
            pedido.setFormaPagamento("pix");

            Endereco endereco = new Endereco("rn", "Pau dos Ferros", "Vila Bela", "Rua das Acácias", "casa", "62980-000", 404);

            cliente.adicionarEndereco(endereco);
            
            pedido.setEndereco(cliente.getEnderecos().get(0));

            pedido.finalizarCompra(carrinho);
            int idPedido1 = pedido.getNumPedido();

            carrinho.removerItem(produto1);

            pedido.finalizarCompra(carrinho);
            int idPedido2 = pedido.getNumPedido();

            assertNotNull(cliente.listarPedidos(cliente));

            try {
                Connection connection = PostgreSQLConnection.getInstance().getConnection();

                PreparedStatement pstmt = connection.prepareStatement(
                "DELETE FROM " +
                "produtos_pedido WHERE id_pedido = ?; " +
                "DELETE FROM " +
                "pedido WHERE id_pedido = ?;");

                pstmt.setInt(1, idPedido1);
                pstmt.setInt(2, idPedido1);
                pstmt.executeUpdate();

                pstmt.setInt(1, idPedido2);
                pstmt.setInt(2, idPedido2);
                pstmt.executeUpdate();

            } catch (Exception e) {
                throw e;
            }

            cliente.excluirUsuario(cliente);

        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * Verifica se o método está validando os emails corretamente.
     */
    @Test
    void setEmailTest(){

        // Caso de sucesso: email válido
        String email1 = "usuario@example.com";

        // Caso de erro: email inválido
        String email2 = "email.invalido";

        // Caso de erro: email vazio
        String email3 = "";

        // Caso de erro: email nulo
        String email4 = null;

        assertTrue(cliente.setEmail(email1));
        assertFalse(cliente.setEmail(email2));
        assertFalse(cliente.setEmail(email3));
        assertFalse(cliente.setEmail(email4));
    }

    /**
     * Verifica se o método está validando os nomes corretamente.
     */
    @Test
    void setNomeCompletoTest(){

        // Caso de sucesso: nome válido
        String nome1 = "Paulo";

        // Caso de erro: nome muito pequeno
        String nome2 = "Edu";

        // Caso de erro: nome vazio
        String nome3 = "";

        // Caso de erro: nome nulo
        String nome4 = null;

        // Caso de erro: nome possui números
        String nome5 = "Roberto123";

        // Caso de erro: nome possui caracteres inválidos
        String nome6 = "Roberto!!";

        assertTrue(cliente.setNome_completo(nome1));
        assertFalse(cliente.setNome_completo(nome2));
        assertFalse(cliente.setNome_completo(nome3));
        assertFalse(cliente.setNome_completo(nome4));
        assertFalse(cliente.setNome_completo(nome5));
        assertFalse(cliente.setNome_completo(nome6));
    }

    /**
     * Verifica se o método está validando os telefones corretamente.
     */
    @Test
    void setTelefoneTest(){

        // Caso de sucesso: telefone válido
        String telefone1 = "88981435736";

        // Caso de erro: telefone inválido
        String telefone2 = "889814357360";

        // Caso de sucesso: telefone válido em outro formato
        String telefone3 = "+55 11 98765-4321";

        // Caso de erro: telefone com código de área inválida
        String telefone4 = "+55 1 98765-4321";

        // Caso de sucesso: telefone válido de outro país, dos Estados Unidos nesse exemplo.
        String telefone5 = "+1 650-253-0000";

        // Caso de sucesso: telefone válido pois o telefone é opcional
        String telefone6 = "";

        // Caso de sucesso: telefone válido pois o telefone é opcional
        String telefone7 = null;

        assertTrue(cliente.setTelefone(telefone1, "BR"));
        assertFalse(cliente.setTelefone(telefone2, "BR"));
        assertTrue(cliente.setTelefone(telefone3, "BR"));
        assertFalse(cliente.setTelefone(telefone4, "BR"));
        assertTrue(cliente.setTelefone(telefone5, "US"));
        assertTrue(cliente.setTelefone(telefone6, "BR"));
        assertTrue(cliente.setTelefone(telefone7, "BR"));
    }

    /**
     * Verifica se o método está validando as senhas corretamente.
     */
    @Test
    void setSenhaTest(){

        // Caso de sucesso: senha válida
        String senha1 = "BdaU2E4UfweU492fw234HDU9";

        // Caso de erro: senha inválida, menos que 8 dígitos.
        String senha2 = "1234567";

        // Caso de erro: senha inválida, mais que 40 dígitos.
        String senha3 = "8hTjWnQxYzR6mVpD1Ls4G9cX7vF2bNqA3KtPfVGKD8";

        // Caso de erro: senha nula
        String senha4 = null;

        assertTrue(cliente.setSenha(senha1));
        assertFalse(cliente.setSenha(senha2));
        assertFalse(cliente.setSenha(senha3));
        assertFalse(cliente.setSenha(senha4));
    }

    /**
     * Verifica se o método está validando os usuarios corretamente.
     */
    @Test
    void setUsuarioTest(){

        // Caso de sucesso: usuario válido
        String usuario1 = "Usuario123";

        // Caso de erro: usuario inválido, menos que 3 dígitos.
        String usuario2 = "FP";

        // Caso de erro: usuario inválido, mais que 50 dígitos.
        String usuario3 = "8hTjWnQxYzR6mVpD1Ls4G9cX7vF2bNqA3KtPfVGKD8FGJNRWEU9O312453Ffwefwerfwer";

        // Caso de erro: usuario nulo
        String usuario4 = null;

        // Caso de erro: usuario com símbolos.
        String usuario5 = "Usuario!!";

        assertTrue(cliente.setUsuario(usuario1));
        assertFalse(cliente.setUsuario(usuario2));
        assertFalse(cliente.setUsuario(usuario3));
        assertFalse(cliente.setUsuario(usuario4));
        assertFalse(cliente.setUsuario(usuario5));
    }

    /**
     * Verifica se o método está validando as datas corretamente.
     */
    @Test
    void setDataNascTest(){

        LocalDate dataAtual = LocalDate.now();

        // Caso de sucesso: data válida
        LocalDate data1 = LocalDate.of(2003, 7, 27);

        // Caso de erro: data no futuro.
        LocalDate data2 = LocalDate.of(2100, 7, 27);

        // Caso de erro: data com idade menor que a mínima.
        LocalDate data3 = LocalDate.of(2014, 7, 27);

        // Caso de erro: data nula
        LocalDate data4 = null;

        // Caso de erro: data faltando 1 dia para completar 18 anos.
        LocalDate data5 = dataAtual.minusDays(1);

        assertTrue(cliente.setDataNasc(data1));
        assertFalse(cliente.setDataNasc(data2));
        assertFalse(cliente.setDataNasc(data3));
        assertFalse(cliente.setDataNasc(data4));
        assertFalse(cliente.setDataNasc(data5));
    }

    /**
     * Verifica se o método está validando os cpfs corretamente.
     */
    @Test
    void setCPFTest(){

        // Caso de sucesso: cpf válid0
        String cpf1 = "07769719305";

        // Caso de erro: cpf válido.
        String cpf2 = "077.697.193-05";

        // Caso de erro: cpf inválido.
        String cpf3 = "000.000.000-00";

        // Caso de erro: cpf inválido.
        String cpf4 = "123.456.789-01";

        // Caso de erro: cpf inválido.
        String cpf5 = "12345678901";

        // Caso de erro: cpf nulo
        String cpf6 = null;

        assertTrue(cliente.setCpf(cpf1));
        assertTrue(cliente.setCpf(cpf2));
        assertFalse(cliente.setCpf(cpf3));
        assertFalse(cliente.setCpf(cpf4));
        assertFalse(cliente.setCpf(cpf5));
        assertFalse(cliente.setCpf(cpf6));
    }
}
