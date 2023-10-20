package com.test_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private String categoria;
    private int estoque;
    private String descricao;
    private String fabricante;
    private int desconto;

    public Produto(int id, String nome, double preco, String categoria, int estoque, String descricao,
            String fabricante, int desconto) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.estoque = estoque;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.desconto = desconto;
        setPreco(preco);
    }

    public boolean exibirDetalhes(){
        try {
            //Colocar uma forma legal de imprimir os detalhes talvez?
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Produto buscaProduto(int id_produto) throws Exception{
        try {

            Connection connection = PostgreSQLConnection.getInstance().getConnection();

            PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM " +
                "produto WHERE id_produto = ?");

            pstmt.setInt(1, id_produto);

            ResultSet rs = pstmt.executeQuery();

            Produto produto = null;

            if (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                double preco = rs.getDouble(3);
                String categoria = rs.getString(4);
                int estoque = rs.getInt(5);
                String descricao = rs.getString(6);
                String fabricante = rs.getString(7);
                int desconto = rs.getInt(8);

                produto = new Produto(id, nome, preco, categoria, estoque, descricao, fabricante, desconto);

            }

            return produto;
            
        } catch (Exception e) {
            throw e;
        }
    }

    public static ArrayList<Produto> listarProdutos() throws Exception{
        try {
            ArrayList<Produto> produtos = new ArrayList<>();

            Connection connection = PostgreSQLConnection.getInstance().getConnection();

            PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM " +
                "produto");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Produto produto;
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                double preco = rs.getDouble(3);
                String categoria = rs.getString(4);
                int estoque = rs.getInt(5);
                String descricao = rs.getString(6);
                String fabricante = rs.getString(7);
                int desconto = rs.getInt(8);
                produto = new Produto(id, nome, preco, categoria, estoque, descricao, fabricante, desconto);

                produtos.add(produto);
            }

            return produtos;
        } catch (Exception e) {
            throw e;
        }
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
    public String getCategoria() {
        return categoria;
    }
    public int getEstoque() {
        return estoque;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getFabricante() {
        return fabricante;
    }
    public int getDesconto() {
        return desconto;
    }

    public void setPreco(double preco) {
        this.preco = preco * (100 - desconto)/100;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID: ").append(id).append("\n");
        sb.append("Produto: ").append(nome).append("\n");
        sb.append("Preço: ").append(preco).append("\n");
        sb.append("Categoria: ").append(categoria).append("\n");
        sb.append("Estoque: ").append(estoque).append("\n");
        sb.append("Descrição: ").append(descricao).append("\n");
        sb.append("Fabricante: ").append(fabricante).append("\n");
        sb.append("Desconto: ").append(desconto).append("\n");

        return sb.toString();
    }

    public String imprimirSimples() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID: ").append(id).append("\n");
        sb.append("Produto: ").append(nome).append("\n");
        sb.append("Preço: ").append(preco).append("\n");
        sb.append("Categoria: ").append(categoria).append("\n");
    
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Produto other = (Produto) obj;

        return id == other.id &&
            Double.compare(other.preco, preco) == 0 &&
            estoque == other.estoque &&
            desconto == other.desconto &&
            Objects.equals(nome, other.nome) &&
            Objects.equals(categoria, other.categoria) &&
            Objects.equals(descricao, other.descricao) &&
            Objects.equals(fabricante, other.fabricante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, preco, categoria, estoque, descricao, fabricante, desconto);
    }

}

