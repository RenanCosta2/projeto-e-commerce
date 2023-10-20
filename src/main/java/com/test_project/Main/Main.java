package com.test_project.Main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.test_project.Carrinho;
import com.test_project.Endereco;
import com.test_project.Pedido;
import com.test_project.PostgreSQLConnection;
import com.test_project.Produto;
import com.test_project.Usuario;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in,"CP850");
        Usuario cliente = new Usuario(null, null, null, null, null, null, null);
        Carrinho carrinho = null;
        ArrayList<Produto> produtos = Produto.listarProdutos();

        String osName = System.getProperty("os.name").toLowerCase();
        String limpar1, limpar2, limpar3;
        if (osName.contains("windows")) {
            limpar1 = "cmd";
            limpar2 = "/c";
            limpar3 = "cls";
        } else {
            limpar1 = "sh";
            limpar2 = "-c";
            limpar3 = "clear";
        }

        int op = 0;

        do {
            new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();
            Menus.telaInicial();
            System.out.print("  >> ");
            op = sc.nextInt();

            switch (op) {
                case 1:

                    sc.nextLine();

                    new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();
                    
                    System.out.println(" ====  LOGIN  ====");
                    System.out.print("Usuário: ");
                    String usuarioLogin = sc.nextLine();
                    System.out.print("Senha: ");
                    String senhaLogin = sc.nextLine();

                    cliente = Usuario.login(usuarioLogin, senhaLogin);

                    if (cliente == null) {
                        System.out.println("\nUsuário e/ou senha incorreto(s)!");
                        System.out.println("Pressione Enter para continuar!");
                        sc.nextLine();
                        op = 0;
                    }

                    if (cliente != null) {
                        carrinho = new Carrinho(cliente);
                        do {

                            carrinho.atualizarBanco();
                            
                            new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                            if (cliente == null) {
                                op = 4;
                                cliente = new Usuario(null, null, null, null, null, null, null);
                            } else{
                                Menus.telaUsuario();
                                System.out.print("  >> ");
                                op = sc.nextInt();
                                
                            }
    
                            switch (op) {
                                case 1:

                                    do {
                                        new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                        Menus.perfilUsuario(cliente);
                                        System.out.print("  >> ");
                                        op = sc.nextInt();

                                        switch (op) {
                                            case 1:

                                                 new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();


                                                Menus.editarUsuario();
                                                System.out.print("  >> ");
                                                op = sc.nextInt();

                                                switch (op) {
                                                    case 1:

                                                        new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                        sc.nextLine();
                                                        System.out.print("Novo nome completo: ");
                                                        String nome_completo = sc.nextLine();

                                                        if (cliente.setNome_completo(nome_completo)) {
                                                            cliente.editarInformacoes(nome_completo, null, null, null, null, null);
                                                            System.out.println("Nome completo atualizado com sucesso!");
                                                            sc.nextLine();
                                                        } else{
                                                            System.out.println("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                            sc.nextLine();
                                                        }

                                                        op = 0;
                                                        
                                                        break;

                                                    case 2:

                                                        new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                        sc.nextLine();
                                                        System.out.print("Novo email: ");
                                                        String email = sc.nextLine();

                                                        if (cliente.setEmail(email)) {
                                                            cliente.editarInformacoes(null, email, null, null, null, null);
                                                            System.out.println("Email atualizado com sucesso!");
                                                            sc.nextLine();
                                                        } else{
                                                            System.out.println("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                            sc.nextLine();
                                                        }

                                                        op = 0;
                                                        
                                                        break;

                                                    case 3:

                                                        new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                        sc.nextLine();
                                                        System.out.print("Novo telefone: ");
                                                        String telefone = sc.nextLine();

                                                        if (cliente.setTelefone(telefone, "BR")) {
                                                            cliente.editarInformacoes(null, null, telefone, null, null, null);
                                                            System.out.println("Telefone atualizado com sucesso!");
                                                            sc.nextLine();
                                                        } else{
                                                            System.out.println("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                        }

                                                        op = 0;
                                                        
                                                        break;

                                                    case 4:

                                                        new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                        sc.nextLine();
                                                        System.out.print("Novo usuário: ");
                                                        String usuario = sc.nextLine();

                                                        if (cliente.setUsuario(usuario)) {
                                                            cliente.editarInformacoes(null, null, null, null, usuario, null);
                                                            System.out.println("Usuário atualizado com sucesso!");
                                                            sc.nextLine();
                                                        } else{
                                                            System.out.println("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                            sc.nextLine();
                                                        }

                                                        op = 0;
                                                        
                                                        break;

                                                    case 5:

                                                        new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                        sc.nextLine();
                                                        System.out.print("Nova senha: ");
                                                        String senha = sc.nextLine();
                                                        System.out.print("Confirmar nova senha: ");
                                                        String senhaConfirmar = sc.nextLine();

                                                        if (senha.equals(senhaConfirmar)) {
                                                            
                                                            if (cliente.setSenha(senha)) {
                                                                cliente.editarInformacoes(null, null, null, senha, null, null);
                                                                System.out.println("Senha atualizada com sucesso!");
                                                                sc.nextLine();
                                                            } else{
                                                                System.out.println("Pressione Enter para continuar!");
                                                                sc.nextLine();
                                                            }
                                                        } else{
                                                            System.out.println("As senhas não estão compatíveis!");
                                                            System.out.println("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                        }

                                                        op = 0;
                                                        
                                                        break;

                                                    case 6:

                                                        new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                        sc.nextLine();
                                                        System.out.print("Nova data de nascimento (ex: 27-07-2003): ");
                                                        String data = sc.nextLine();
                                                        LocalDate dataNasc = null;
                                                        try {
                                                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                                            dataNasc = LocalDate.parse(data, formatter);

                                                            if (cliente.setDataNasc(dataNasc)) {
                                                            cliente.editarInformacoes(null, null, null, null, null, dataNasc);
                                                            System.out.println("Data de nascimento atualizada com sucesso!");
                                                            sc.nextLine();
                                                            } else{
                                                                System.out.println("Pressione Enter para continuar!");
                                                                sc.nextLine();
                                                            }
                                                            
                                                        } catch (Exception e) {
                                                            System.out.println("Formato de data inválido. Use o formato 'dd-MM-yyyy'.\"");
                                                            System.out.println("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                        }

                                                        op = 0;
                                                        
                                                        break;

                                                    case 7:

                                                        new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                        Endereco endereco = new Endereco(null, null, null, null, null, null, 0);

                                                        sc.nextLine();
                                                        System.out.println(" ====  ADICIONAR ENDEREÇO  ====");
                                                        System.out.print("Estado (ex: RN): ");
                                                        String estado  = sc.nextLine();
                                                        if (!endereco.setEstado(estado)) {
                                                            System.out.println("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                            break;
                                                        }

                                                        System.out.print("Cidade: ");
                                                        String cidade = sc.nextLine();
                                                        if (!endereco.setCidade(cidade)) {
                                                            System.out.println("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                            break;
                                                        }

                                                        System.out.print("Bairro: ");
                                                        String bairro = sc.nextLine();
                                                        if (!endereco.setBairro(bairro)) {
                                                            System.out.println("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                            break;
                                                        }

                                                        System.out.print("Rua: ");
                                                        String rua = sc.nextLine();
                                                        if (!endereco.setRua(rua)) {
                                                            System.out.println("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                            break;
                                                        }

                                                        System.out.print("Complemento: ");
                                                        String complemento = sc.nextLine();
                                                        if (!endereco.setComplemento(complemento)) {
                                                            System.out.println("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                            break;
                                                        }

                                                        System.out.print("CEP (ex: 62980-000): ");
                                                        String cep = sc.nextLine();
                                                        if (!endereco.setCep(cep)) {
                                                            System.out.println("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                            break;
                                                        }

                                                        System.out.print("Número: ");
                                                        int numero = sc.nextInt();
                                                        if (!endereco.setNumero(numero)) {
                                                            System.out.println("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                            break;
                                                        }

                                                        if (cliente.adicionarEndereco(endereco)) {
                                                            System.out.println("\nEndereço cadastrado com sucesso!");
                                                        } else{
                                                            System.out.println("Não foi possível cadastrar o endereço!");
                                                        }

                                                        System.out.println("Pressione Enter para continuar!");
                                                        sc.nextLine();
                                                        
                                                        break;

                                                    case 8:

                                                        new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                        ArrayList<Endereco> enderecos = cliente.getEnderecos();

                                                        for (Endereco enderecoE : enderecos) {
                                                            System.out.println(enderecoE);
                                                            System.out.println();
                                                        }

                                                        System.out.println("ID do endereço que será excluído: ");
                                                        int idEndereco = sc.nextInt();

                                                        if (cliente.excluirEndereco(idEndereco, cliente)) {
                                                            System.out.println("Endereço excluído com sucesso!");
                                                        } else{
                                                            System.out.println("Não foi possível excluir o endereço!");
                                                        }

                                                        System.out.print("Pressione Enter para continuar!");
                                                        sc.nextLine();
                                                        sc.nextLine();

                                                        op = 0;
                                                        
                                                        break;

                                                    case 9:
                                                        
                                                        break;
                                                }
                                                
                                                break;

                                            case 2:

                                                ArrayList<Pedido> pedidos = cliente.listarPedidos(cliente);

                                                do {
                                                    
                                                    new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();
    
                                                    if (pedidos.isEmpty()) {
                                                        System.out.println("O usuário não possui pedidos!");
                                                        System.out.print("Pressione Enter para continuar!");
                                                        sc.nextLine();
                                                        sc.nextLine();
                                                        break;
                                                    }

                                                    Menus.telaPedidos(pedidos, cliente);
                                                    System.out.print("  >> ");
                                                    op = sc.nextInt();

                                                    switch (op) {
                                                        case 1:

                                                            new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                            for (Pedido pedido : pedidos) {
                                                                System.out.println(pedido.toString(cliente));
                                                            }

                                                            System.out.print("\nDigite o ID do pedido que será cancelado: ");
                                                            int idPedido = sc.nextInt();

                                                            boolean encontrado = false;

                                                            for (Pedido pedido : pedidos) {
                                                                if (pedido.getNumPedido() == idPedido) {
                                                                    encontrado = true;
                                                                    if (pedido.cancelarPedido()) {
                                                                        pedidos = cliente.listarPedidos(cliente);
                                                                        System.out.println("Pedido cancelado com sucesso!");
                                                                    } else{
                                                                        System.out.println("Não foi possível cancelar o pedido!");
                                                                    }
                                                                }
                                                            }

                                                            if (!encontrado) {
                                                                System.out.println("Pedido não está cadastrado!");
                                                            }

                                                            System.out.print("Pressione Enter para continuar!");
                                                            sc.nextLine();
                                                            sc.nextLine();
                                                            
                                                            break;
                                                    
                                                        case 2:

                                                            break;
                                                    }

                                                } while (op != 2);

                                                op = 0;

                                                break;

                                            case 3:

                                                System.out.println("\nDigite 1 se deseja realmente excluir o seu perfil");
                                                int excluirPerfil = sc.nextInt();

                                                if (excluirPerfil == 1) {
                                                    if (cliente.excluirUsuario(cliente)) {
                                                        System.out.println("Seu perfil foi excluído com sucesso!");
                                                        cliente = null;
                                                    }else{
                                                        System.out.println("Não foi possível excluir o seu perfil!");
                                                    }
                                                } else{
                                                    System.out.println("Não foi possível excluir o seu perfil!");
                                                }

                                                System.out.print("Pressione Enter para continuar!");
                                                sc.nextLine();
                                                sc.nextLine();

                                                op = 4;

                                                break;

                                            case 4:

                                                break;
                                        }
                                        
                                    } while (op != 4);

                                    op = 0;
                                    
                                    break;
    
                                case 2:

                                    do {

                                        new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                        carrinho.atualizarBanco();

                                        Menus.carrinhoUsuario(carrinho);
                                        System.out.print("  >> ");
                                        op = sc.nextInt();

                                        switch (op) {
                                            case 1:
                                                boolean removeu = false;
                                                boolean encontrou = false;

                                                do {

                                                    new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();
                                                    System.out.println(carrinho.toString());

                                                    System.out.print("ID do produto: ");
                                                    int idProduto = sc.nextInt();
                                                    System.out.print("Quantidade: ");
                                                    int quantidade = sc.nextInt();

                                                    ArrayList<Produto> produtosCarrinho = new ArrayList<>();

                                                    for (Produto produto : carrinho.getProdutos().keySet()) {
                                                        produtosCarrinho.add(produto);
                                                    }

                                                    for (Produto produto : produtosCarrinho) {
                                                        if (produto.getId() == idProduto) {
                                                            encontrou = true;
                                                            if (quantidade > 0 || quantidade < 0) {
                                                                if (quantidade > 0) {
                                                                    
                                                                    if (quantidade > carrinho.quantidadeProduto(produto)) {
                                                                        System.out.println("\nQuantidade inválida!");
                                                                        System.out.println("Digite 1 se deseja tentar novamente!");
                                                                        op = sc.nextInt();
                                                                        removeu = false;
                                                                    } else{
                                                                        quantidade = -quantidade;
                                                                        if (carrinho.removerItem(produto, quantidade)) {
                                                                            removeu = true;
                                                                        }
                                                                    }
                                                                } else{
                                                                    if (quantidade < -carrinho.quantidadeProduto(produto)) {
                                                                        System.out.println("\nQuantidade inválida!");
                                                                        System.out.println("Digite 1 se deseja tentar novamente!");
                                                                        op = sc.nextInt();
                                                                        removeu = false;
                                                                    } else{
                                                                        
                                                                        if (carrinho.removerItem(produto, quantidade)) {
                                                                            removeu = true;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }

                                                    if (!encontrou) {
                                                        System.out.println("\nID inválido!");
                                                        System.out.println("Digite 1 se deseja tentar novamente!");
                                                        op = sc.nextInt();
                                                    }
                                                    if (!removeu) {
                                                    }
                                                    
                                                } while (!removeu && op == 1);

                                                if (removeu) {
                                                    
                                                    System.out.println("\nItem removido com sucesso!");
                                                    System.out.println("Pressione Enter para continuar!");
                                                    sc.nextLine();
                                                    sc.nextLine();
                                                }

                                                op = 1;
                                                
                                                break;

                                            case 2:

                                                if (carrinho.esvaziarCarrinho()) {
                                                    System.out.println("\nCarrinho esvaziado com sucesso!");
                                                    System.out.println("Pressione Enter para continuar!");
                                                    sc.nextLine();
                                                    sc.nextLine();
                                                } else{
                                                    System.out.println("\nO carrinho já está vazio!");
                                                    System.out.println("Pressione Enter para continuar!");
                                                    sc.nextLine();
                                                    sc.nextLine();
                                                }

                                                op = 0;

                                                break;

                                            case 3:

                                                Pedido pedido = carrinho.realizarPedido();

                                                if (pedido == null) {
                                                    System.out.println("O carrinho está vazio, não é possível realizar um pedido!");
                                                    System.out.println("Pressione Enter para continuar!");
                                                    sc.nextLine();
                                                    sc.nextLine();
                                                    break;
                                                }

                                                new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                System.out.println(" ====  REALIZAR PEDIDO  ====");

                                                sc.nextLine();
                                                System.out.println(" 1 -> Envio Padrão (R$10.0)");
                                                System.out.println(" 2 -> Envio Expresso (R$20.0)");
                                                System.out.print("Selecione um tipo de envio: ");
                                                int idTipoEnvio = sc.nextInt();
                                                String tipoEnvio = "";
                                                if (idTipoEnvio == 1) {
                                                    tipoEnvio = "envio padrão";
                                                } else if (idTipoEnvio == 2) {
                                                    tipoEnvio = "envio expresso";
                                                }
                                                if (!pedido.setTipoEnvio(tipoEnvio)) {
                                                    System.out.println("Pressione Enter para continuar!");
                                                    sc.nextLine();
                                                    sc.nextLine();
                                                    break;
                                                }

                                                System.out.println("\n 1 -> Cartão de Crédito");
                                                System.out.println(" 2 -> Cartão de Débito");
                                                System.out.println(" 3 -> Boleto Bancário");
                                                System.out.println(" 4 -> PIX");
                                                System.out.print("Selecione uma forma de pagamento: ");
                                                int idFormaPagamento = sc.nextInt();
                                                String formaPagamento = "";
                                                if (idFormaPagamento == 1) {
                                                    formaPagamento = "cartão de crédito";
                                                } else if (idFormaPagamento == 2) {
                                                    formaPagamento = "cartão de débito";
                                                } else if (idFormaPagamento == 3) {
                                                    formaPagamento = "boleto bancário";
                                                } else if (idFormaPagamento == 4) {
                                                    formaPagamento = "pix";
                                                }
                                                if (!pedido.setFormaPagamento(formaPagamento)) {
                                                    System.out.println("Pressione Enter para continuar!");
                                                    sc.nextLine();
                                                    sc.nextLine();
                                                    break;
                                                }

                                                System.out.println();

                                                if (!cliente.getEnderecos().isEmpty()) {
                                                    for (Endereco endereco : cliente.getEnderecos()) {
                                                        System.out.println(endereco.toString());
                                                    }

                                                    System.out.print("\nSelecione um endereço de destino: ");
                                                    int idEndereco = sc.nextInt();

                                                    for (Endereco endereco : cliente.getEnderecos()) {
                                                        if (idEndereco == endereco.getId()) {
                                                            pedido.setEndereco(endereco);
                                                        }
                                                    }

                                                    if (pedido.getEndereco() == null) {
                                                        System.out.println("Endereço inválido!");
                                                        System.out.println("Pressione Enter para continuar!");
                                                        sc.nextLine();
                                                        sc.nextLine();
                                                        break;
                                                    }
                                                } else{
                                                    System.out.println("\nSeu perfil não possui nenhum endereço cadastrado!");
                                                    System.out.println("Pressione Enter para continuar!");
                                                    sc.nextLine();
                                                    sc.nextLine();
                                                    break;
                                                }

                                                new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                pedido.setStatus("em andamento");
                                                pedido.setDataCriacao(LocalDate.now());
                                                pedido.setCustoEnvio();
                                                pedido.setValorTotal();
                                                System.out.println(pedido.toString(cliente));

                                                System.out.println("\nDigite 1 se deseja confirmar essa compra!");
                                                int confirmar = sc.nextInt();

                                                if (confirmar == 1 && pedido.finalizarCompra(carrinho)) {
                                                    System.out.println("Compra finalizada!");
                                                } else{
                                                    System.out.println("Não foi possível finalizar a compra!");
                                                }

                                                op = 0;
                                                
                                                System.out.println("Pressione Enter para continuar!");
                                                sc.nextLine();
                                                sc.nextLine();
                                                break;

                                            case 4:
                                            
                                                break;
                                        }
                                        
                                    } while (op != 4);
    
                                    op = 0;

                                    break;
    
                                case 3:

                                    do {
                                        
                                        new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                        carrinho.atualizarBanco();
    
                                        Menus.telaProdutos(produtos);
                                        System.out.print("  >> ");
                                        op = sc.nextInt();
    
                                        switch (op) {
                                            case 1:                                            
                                                boolean adicionou = false;
                                                boolean encontrou = false;

                                                do {

                                                    new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                    for (Produto produto : produtos) {
                                                        System.out.println(produto.toString());
                                                    }

                                                    System.out.print("ID do produto: ");
                                                    int idProduto = sc.nextInt();
                                                    System.out.print("Quantidade: ");
                                                    int quantidade = sc.nextInt();

                                                    for (Produto produto : produtos) {
                                                        if (produto.getId() == idProduto) {
                                                            encontrou = true;
                                                            if (quantidade > 0 && quantidade < produto.getEstoque()) {
                                                                if (carrinho.adicionarItem(produto, quantidade)) {
                                                                    adicionou = true;
                                                                }
                                                            } else{
                                                                System.out.println("\nQuantidade inválida!");
                                                                System.out.println("Digite 1 se deseja tentar novamente!");
                                                                op = sc.nextInt();
                                                            }
                                                        }
                                                    }

                                                    if (!encontrou) {
                                                        System.out.println("\nID inválido!");
                                                        System.out.println("Digite 1 se deseja tentar novamente!");
                                                        op = sc.nextInt();
                                                    }
                                                    
                                                } while (!adicionou && op == 1);

                                                if (adicionou) {
                                                    
                                                    System.out.println("\nItem adicionado com sucesso!");
                                                    System.out.println("Pressione Enter para continuar!");
                                                    sc.nextLine();
                                                    sc.nextLine();
                                                }


                                                op = 1;
                                                
                                                break;
    
                                            case 2:
                                                
                                                do {
                                                    new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();
                                                    Menus.telaFiltro();
                                                    System.out.print("  >> ");
                                                    op = sc.nextInt();

                                                    ArrayList<Produto> produtosFiltrados = new ArrayList<>();

                                                    switch (op) {
                                                        case 1:

                                                            sc.nextLine();
                                                            System.out.print("\nNome do produto: ");
                                                            String nome = sc.nextLine();

                                                            for (Produto produto : produtos) {
                                                                if (produto.getNome().equals(nome)) {
                                                                    produtosFiltrados.add(produto);
                                                                }
                                                            }

                                                            if (produtosFiltrados.isEmpty()) {
                                                                System.out.println("Não há nenhum produto com esse nome!");   
                                                                System.out.println("Pressione Enter para continuar!");
                                                                sc.nextLine();
                                                                sc.nextLine();

                                                            } else{
                                                                new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();
                                                                Menus.telaProdutosFiltrados(produtosFiltrados);
                                                                System.out.print("  >> ");
                                                                op = sc.nextInt();

                                                                switch (op) {
                                                                    case 1:

                                                                        adicionou = false;
                                                                        encontrou = false;

                                                                        do {

                                                                            new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                                            for (Produto produto : produtosFiltrados) {
                                                                                System.out.println(produto.toString());
                                                                            }

                                                                            System.out.print("ID do produto: ");
                                                                            int idProduto = sc.nextInt();
                                                                            System.out.print("Quantidade: ");
                                                                            int quantidade = sc.nextInt();

                                                                            for (Produto produto : produtosFiltrados) {
                                                                                if (produto.getId() == idProduto) {
                                                                                    encontrou = true;
                                                                                    if (quantidade > 0 && quantidade < produto.getEstoque()) {
                                                                                        if (carrinho.adicionarItem(produto, quantidade)) {
                                                                                            adicionou = true;
                                                                                        }
                                                                                    } else{
                                                                                        System.out.println("\nQuantidade inválida!");
                                                                                        System.out.println("Digite 1 se deseja tentar novamente!");
                                                                                        op = sc.nextInt();
                                                                                    }
                                                                                }
                                                                            }

                                                                            if (!encontrou) {
                                                                                System.out.println("\nID inválido!");
                                                                                System.out.println("Digite 1 se deseja tentar novamente!");
                                                                                op = sc.nextInt();
                                                                            }
                                                                            
                                                                        } while (!adicionou && op == 1);

                                                                        if (adicionou) {
                                                                            
                                                                            System.out.println("\nItem adicionado com sucesso!");
                                                                            System.out.println("Pressione Enter para continuar!");
                                                                            sc.nextLine();
                                                                            sc.nextLine();
                                                                        }


                                                                        op = 1;
                                                                        
                                                                        break;
                                                                                                
                                                                    case 2:
                                                                        break;
                                                                }
                                                            }
                                                            
                                                            break;

                                                        case 2:

                                                            System.out.print("\nPreço mínimo: ");
                                                            double preco = sc.nextDouble();

                                                            for (Produto produto : produtos) {
                                                                if (produto.getPreco() <= preco) {
                                                                    produtosFiltrados.add(produto);
                                                                }
                                                            }

                                                            if (produtosFiltrados.isEmpty()) {
                                                                System.out.println("Não há nenhum produto com esse preço ou menor!");
                                                                System.out.println("Pressione Enter para continuar!");
                                                                sc.nextLine();
                                                                sc.nextLine();   

                                                            } else{
                                                                new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();
                                                                Menus.telaProdutosFiltrados(produtosFiltrados);
                                                                System.out.print("  >> ");
                                                                op = sc.nextInt();

                                                                switch (op) {
                                                                    case 1:

                                                                        adicionou = false;
                                                                        encontrou = false;

                                                                        do {

                                                                            new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                                            for (Produto produto : produtosFiltrados) {
                                                                                System.out.println(produto.toString());
                                                                            }

                                                                            System.out.print("ID do produto: ");
                                                                            int idProduto = sc.nextInt();
                                                                            System.out.print("Quantidade: ");
                                                                            int quantidade = sc.nextInt();

                                                                            for (Produto produto : produtosFiltrados) {
                                                                                if (produto.getId() == idProduto) {
                                                                                    encontrou = true;
                                                                                    if (quantidade > 0 && quantidade < produto.getEstoque()) {
                                                                                        if (carrinho.adicionarItem(produto, quantidade)) {
                                                                                            adicionou = true;
                                                                                        }
                                                                                    } else{
                                                                                        System.out.println("\nQuantidade inválida!");
                                                                                        System.out.println("Digite 1 se deseja tentar novamente!");
                                                                                        op = sc.nextInt();
                                                                                    }
                                                                                }
                                                                            }

                                                                            if (!encontrou) {
                                                                                System.out.println("\nID inválido!");
                                                                                System.out.println("Digite 1 se deseja tentar novamente!");
                                                                                op = sc.nextInt();
                                                                            }
                                                                            
                                                                        } while (!adicionou && op == 1);

                                                                        if (adicionou) {
                                                                            
                                                                            System.out.println("\nItem adicionado com sucesso!");
                                                                            System.out.println("Pressione Enter para continuar!");
                                                                            sc.nextLine();
                                                                            sc.nextLine();
                                                                        }


                                                                        op = 1;
                                                                        
                                                                        break;
                                                                                                
                                                                    case 2:
                                                                        break;
                                                                }
                                                            }
                                                            
                                                            break;
                                                        case 3:

                                                            sc.nextLine();
                                                            System.out.println("\nSelecione uma categoria: ");
                                                            ArrayList<String> categorias = Menus.telaCategoria(produtos);
                                                            System.out.print("  >> ");
                                                            int idCategoria = sc.nextInt();

                                                            if (idCategoria > categorias.size() || idCategoria < 1) {
                                                                System.out.println("Categoria inválida!");   
                                                                System.out.println("Pressione Enter para continuar!");
                                                                sc.nextLine();
                                                                sc.nextLine();
                                                                break;
                                                            }

                                                            for (Produto produto : produtos) {
                                                                if (produto.getCategoria().equals(categorias.get(idCategoria-1))) {
                                                                    produtosFiltrados.add(produto);
                                                                }
                                                            }

                                                            if (produtosFiltrados.isEmpty()) {
                                                                System.out.println("Não há nenhum produto com essa categoria!");   
                                                                System.out.println("Pressione Enter para continuar!");
                                                                sc.nextLine();
                                                                sc.nextLine();

                                                            } else{
                                                                new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();
                                                                Menus.telaProdutosFiltrados(produtosFiltrados);
                                                                System.out.print("  >> ");
                                                                op = sc.nextInt();

                                                                switch (op) {
                                                                    case 1:

                                                                        adicionou = false;
                                                                        encontrou = false;

                                                                        do {

                                                                            new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();

                                                                            for (Produto produto : produtosFiltrados) {
                                                                                System.out.println(produto.toString());
                                                                            }

                                                                            System.out.print("ID do produto: ");
                                                                            int idProduto = sc.nextInt();
                                                                            System.out.print("Quantidade: ");
                                                                            int quantidade = sc.nextInt();

                                                                            for (Produto produto : produtosFiltrados) {
                                                                                if (produto.getId() == idProduto) {
                                                                                    encontrou = true;
                                                                                    if (quantidade > 0 && quantidade < produto.getEstoque()) {
                                                                                        if (carrinho.adicionarItem(produto, quantidade)) {
                                                                                            adicionou = true;
                                                                                        }
                                                                                    } else{
                                                                                        System.out.println("\nQuantidade inválida!");
                                                                                        System.out.println("Digite 1 se deseja tentar novamente!");
                                                                                        op = sc.nextInt();
                                                                                    }
                                                                                }
                                                                            }

                                                                            if (!encontrou) {
                                                                                System.out.println("\nID inválido!");
                                                                                System.out.println("Digite 1 se deseja tentar novamente!");
                                                                                op = sc.nextInt();
                                                                            }
                                                                            
                                                                        } while (!adicionou && op == 1);

                                                                        if (adicionou) {
                                                                            
                                                                            System.out.println("\nItem adicionado com sucesso!");
                                                                            System.out.println("Pressione Enter para continuar!");
                                                                            sc.nextLine();
                                                                            sc.nextLine();
                                                                        }


                                                                        op = 1;
                                                                        
                                                                        break;
                                                                                                
                                                                    case 2:
                                                                        break;
                                                                }
                                                            }
                                                            
                                                            break;
                                                            
                                                        case 4:
                                                            
                                                            break;
                                                    }
                                                } while (op != 4);
    
                                                break;

                                            case 3:
                                                break;
                                        }

                                    } while (op !=3);

                                    op = 0;
    
                                    break;
    
                                case 4:

                                    op = 4;

                                    cliente = new Usuario(null, null, null, null, null, null, null);
                                    
                                    System.out.println("\nVocê foi desconectado!");
                                    System.out.println("Pressione Enter para continuar!");
                                    sc.nextLine();
                                    sc.nextLine();
    
                                    break;
                            }
                        } while (op != 4);
                        op = 0;
                    }
                    
                    break;

                case 2:
                    new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();
                    
                    cliente = new Usuario(null, null, null, null, null, null, null);

                    sc.nextLine();
                    System.out.println(" ====  CADASTRAR  ====");
                    System.out.print("Nome Completo: ");
                    String nome_completo  = sc.nextLine();
                    if (!cliente.setNome_completo(nome_completo)) {
                        System.out.println("Pressione Enter para continuar!");
                        sc.nextLine();
                        break;
                    }

                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    if (!cliente.setEmail(email)) {
                        System.out.println("Pressione Enter para continuar!");
                        sc.nextLine();
                        break;
                    }

                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    if (!cliente.setCpf(cpf)) {
                        System.out.println("Pressione Enter para continuar!");
                        sc.nextLine();
                        break;
                    }

                    System.out.print("Telefone (opcional): ");
                    String telefone = sc.nextLine();
                    if (!cliente.setTelefone(telefone, "BR")) {
                        System.out.println("Pressione Enter para continuar!");
                        sc.nextLine();
                        break;
                    }

                    System.out.print("Usuario: ");
                    String usuario = sc.nextLine();
                    if (!cliente.setUsuario(usuario)) {
                        System.out.println("Pressione Enter para continuar!");
                        sc.nextLine();
                        break;
                    }

                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    if (!cliente.setSenha(senha)) {
                        System.out.println("Pressione Enter para continuar!");
                        sc.nextLine();
                        break;
                    }

                    System.out.print("Confirmar senha: ");
                    String confirmarSenha = sc.nextLine();
                    if (!senha.equals(confirmarSenha)) {
                        System.out.println("\nSenhas incompatíveis!");
                        System.out.println("Pressione Enter para continuar!");
                        sc.nextLine();
                        break;
                    }

                    System.out.print("Data de Nascimento (ex: 27-07-2003): ");
                    String data = sc.nextLine();
                    LocalDate dataNasc = null;
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        dataNasc = LocalDate.parse(data, formatter);
                        
                    } catch (Exception e) {
                        System.out.println("Formato de data inválido. Use o formato 'dd-MM-yyyy'.\"");
                        System.out.println("Pressione Enter para continuar!");
                        sc.nextLine();
                    }
                    if (!cliente.setDataNasc(dataNasc)) {
                        System.out.println("Pressione Enter para continuar!");
                        sc.nextLine();
                        break;
                    }

                    if (cliente.cadastrar()) {
                        
                        System.out.println("\nCadastro realizado!");
                    }

                    System.out.println("Pressione Enter para continuar!");
                    sc.nextLine();

                    break;

                case 3:
                    new ProcessBuilder(limpar1, limpar2, limpar3).inheritIO().start().waitFor();
                    op = -1;

                    PostgreSQLConnection.getInstance().closeConnection();
                    System.out.println("Finalizando programa...");
                    sc.close();

                    break;
            }
            
        } while (op != -1);
    }
}
