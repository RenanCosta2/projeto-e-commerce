package com.test_project.Main;

import java.util.ArrayList;

import com.test_project.Carrinho;
import com.test_project.Pedido;
import com.test_project.Produto;
import com.test_project.Usuario;

public class Menus {
    public static void telaInicial(){
        System.out.println(" ====  LOGIN  ====");
        System.out.println(" 1 -> Efetuar Login");
        System.out.println(" 2 -> Cadastrar-se");
        System.out.println(" 3 -> Sair");
    }

    public static void telaUsuario(){
        System.out.println(" ====  TELA PRINCIPAL  ====");
        System.out.println(" 1 -> Perfil");
        System.out.println(" 2 -> Vizualizar Carrinho");
        System.out.println(" 3 -> Produtos");
        System.out.println(" 4 -> LOGOUT");
    }

    public static void perfilUsuario(Usuario cliente){
        System.out.println(" ====  PERFIL  ====");
        System.out.println(cliente.toString());

        System.out.println(" 1 -> Editar Informações");
        System.out.println(" 2 -> Listar Pedidos");
        System.out.println(" 3 -> Excluir Usuário");
        System.out.println(" 4 -> Voltar");
    }

    public static void editarUsuario(){
        System.out.println(" ====  EDITAR PERFIL  ====");
        
        System.out.println(" 1 -> Nome completo");
        System.out.println(" 2 -> Email");
        System.out.println(" 3 -> Telefone");
        System.out.println(" 4 -> Usuario");
        System.out.println(" 5 -> Senha");
        System.out.println(" 6 -> Data Nascimento");
        System.out.println(" 7 -> Adicionar Endereço");
        System.out.println(" 8 -> Excluir Endereço");
        System.out.println(" 9 -> Voltar");
    }

    public static void carrinhoUsuario(Carrinho carrinho){
        System.out.println(" ====  CARRINHO  ====");

        System.out.println(carrinho.toString());

        System.out.println(" 1 -> Remover Item");
        System.out.println(" 2 -> Esvaziar carrinho");
        System.out.println(" 3 -> Realizar Pedido");
        System.out.println(" 4 -> Voltar");
    }

    public static void telaProdutos(ArrayList<Produto> produtos){
        System.out.println(" ====  PRODUTOS  ====");

        for (Produto produto : produtos) {
            System.out.println(produto.toString());
        }

        System.out.println(" 1 -> Adicionar um Produto ao Carrinho");
        System.out.println(" 2 -> Filtrar Produtos");
        System.out.println(" 3 -> Voltar");
    }

    public static void telaProdutosFiltrados(ArrayList<Produto> produtos){
        System.out.println(" ====  PRODUTOS  ====");

        for (Produto produto : produtos) {
            System.out.println(produto.toString());
        }

        System.out.println(" 1 -> Adicionar um Produto ao Carrinho");
        System.out.println(" 2 -> Voltar");
    }

    public static void telaPedidos(ArrayList<Pedido> pedidos, Usuario cliente){

        for (Pedido pedido : pedidos) {
            System.out.println(pedido.toString(cliente));
            System.out.println();
        }

        System.out.println(" 1 -> Cancelar Pedido");
        System.out.println(" 2 -> Voltar");

    }

    public static void telaFiltro(){
        System.out.println("Filtre a busca pelo produto por:");
        System.out.println(" 1 -> Nome");
        System.out.println(" 2 -> Preço");
        System.out.println(" 3 -> Categoria");
        System.out.println(" 4 -> Voltar");
    }

    public static ArrayList<String> telaCategoria(ArrayList<Produto> produtos){
        ArrayList<String> categorias = new ArrayList<>();
        for (int i = 0; i < produtos.size(); i++) {
            if (!categorias.contains(produtos.get(i).getCategoria())) {
                categorias.add(produtos.get(i).getCategoria());
            }
        }

        for (int i = 0; i < categorias.size(); i++) {
            System.out.println(" " + (i+1) + " -> " + categorias.get(i));
        }

        return categorias;
    }
}
