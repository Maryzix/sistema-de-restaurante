package br.com.restaurante.app;

import br.com.restaurante.cardapio.*;
import br.com.restaurante.pedido.Pedido;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuConsole {

    private Scanner scanner = new Scanner(System.in);

    private int lerNumeroInteiro(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            try {

                int numero = scanner.nextInt();
                scanner.nextLine();

                return numero;

            } catch (InputMismatchException e) {

                scanner.nextLine();

                System.out.println(
                        "Entrada inválida! Digite um número inteiro."
                );
            }
        }
    }

    private int lerOpcaoDentroDoIntervalo(
            String mensagem,
            int minimo,
            int maximo
    ) {

        while (true) {

            int opcao = lerNumeroInteiro(mensagem);

            if (opcao >= minimo && opcao <= maximo) {
                return opcao;
            }

            System.out.println(
                    "Opção inválida! Escolha entre "
                            + minimo + " e " + maximo + "."
            );
        }
    }

    private boolean lerSimNao(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String resposta = scanner.nextLine().trim();

            if (resposta.equalsIgnoreCase("s")) {
                return true;
            }

            if (resposta.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println(
                    "Resposta inválida! Digite apenas S ou N."
            );
        }
    }

    public void exibirMenu() {

        System.out.println("\n=== RESTAURANTE ===");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Fechar restaurante");
        System.out.println("4 - Modificar pedido");
        System.out.println("0 - Sair");
    }

    public int lerOpcao() {

        return lerOpcaoDentroDoIntervalo(
                "Escolha uma opção: ", 0, 4
        );
    }

    public String lerNomeCliente() {

        System.out.print("Nome do cliente: ");
        return scanner.nextLine();
    }

    public int lerOpcaoLanche() {

        System.out.println("\n=== CARDÁPIO ===");
        System.out.println("1 - X-Burger | R$ 16,00");
        System.out.println("2 - X-Salada | R$ 18,00");
        System.out.println("3 - X-Bacon  | R$ 22,00");

        return lerOpcaoDentroDoIntervalo(
                "Escolha seu lanche: ", 1, 3
        );
    }

    // Personalização principal do lanche
    public ItemCardapio personalizarLanche(Lanche lanche) {

        ItemCardapio itemPersonalizado = lanche;

        boolean personalizando = true;

        while (personalizando) {

            System.out.println("\n=== PERSONALIZE SEU LANCHE ===");
            System.out.println("Lanche: " + itemPersonalizado.getDescricao());
            System.out.println("Preço atual: R$ " + itemPersonalizado.getPreco());

            System.out.println("\n1 - Remover ingredientes");
            System.out.println("2 - Adicionar ingredientes");
            System.out.println("3 - Prosseguir com o pedido");

            int opcao = lerOpcaoDentroDoIntervalo(
                    "Escolha uma opção: ", 1, 3
            );

            switch (opcao) {

                case 1:
                    removerIngrediente(lanche);
                    break;

                case 2:
                    itemPersonalizado = adicionarAdicional(
                            itemPersonalizado
                    );
                    break;

                case 3:
                    personalizando = false;
                    break;
            }
        }

        return itemPersonalizado;
    }

    // Menu para remover ingredientes originais
    private void removerIngrediente(Lanche lanche) {

        System.out.println("\n=== REMOVER INGREDIENTES ===");

        if (lanche.isQueijo()) {
            System.out.println("1 - Queijo");
        }

        if (lanche.isAlface()) {
            System.out.println("2 - Alface");
        }

        if (lanche.isTomate()) {
            System.out.println("3 - Tomate");
        }

        if (lanche.isCebola()) {
            System.out.println("4 - Cebola");
        }

        System.out.println("0 - Voltar");

        int opcao = lerOpcaoDentroDoIntervalo(
                "Qual ingrediente deseja retirar? ", 0, 4
        );

        switch (opcao) {

            case 1:
                if (lanche.isQueijo()) {
                    lanche.setQueijo(false);
                    System.out.println("Queijo removido!");
                } else {
                    System.out.println("Esse lanche não possui queijo.");
                }
                break;

            case 2:
                if (lanche.isAlface()) {
                    lanche.setAlface(false);
                    System.out.println("Alface removida!");
                } else {
                    System.out.println("Esse lanche não possui alface.");
                }
                break;

            case 3:
                if (lanche.isTomate()) {
                    lanche.setTomate(false);
                    System.out.println("Tomate removido!");
                } else {
                    System.out.println("Esse lanche não possui tomate.");
                }
                break;

            case 4:
                if (lanche.isCebola()) {
                    lanche.setCebola(false);
                    System.out.println("Cebola removida!");
                } else {
                    System.out.println("Esse lanche não possui cebola.");
                }
                break;

            case 0:
                break;
        }
    }

    // Menu para adicionar ingredientes extras
    private ItemCardapio adicionarAdicional(ItemCardapio item) {

        System.out.println("\n=== ADICIONAR INGREDIENTES ===");

        System.out.println("1 - Queijo extra     | R$ 3,00");
        System.out.println("2 - Alface extra     | R$ 1,50");
        System.out.println("3 - Tomate extra     | R$ 1,50");
        System.out.println("4 - Cebola extra     | R$ 1,00");
        System.out.println("5 - Cheddar          | R$ 4,00");
        System.out.println("6 - Bacon extra      | R$ 6,00");
        System.out.println("0 - Voltar");

        int opcao = lerOpcaoDentroDoIntervalo(
                "Escolha o adicional: ", 0, 6
        );

        switch (opcao) {

            case 1:
                item = new ComQueijoExtra(item);
                break;

            case 2:
                item = new ComAlfaceExtra(item);
                break;

            case 3:
                item = new ComTomateExtra(item);
                break;

            case 4:
                item = new ComCebolaExtra(item);
                break;

            case 5:
                item = new ComCheddar(item);
                break;

            case 6:
                item = new ComBaconExtra(item);
                break;

            case 0:
                break;
        }

        if (opcao != 0) {
            System.out.println("Adicional acrescentado!");
        }

        return item;
    }

    public boolean querAdicionarItem() {

        return lerSimNao(
                "Deseja adicionar outro lanche? (s/n): "
        );
    }

    public int lerNumeroPedido() {

        while (true) {

            int numero = lerNumeroInteiro(
                    "Digite o número do pedido: "
            );

            if (numero > 0) {
                return numero;
            }

            System.out.println(
                    "O número do pedido deve ser maior que zero."
            );
        }
    }

    public void exibirMenuModificacao() {

        System.out.println("\n=== MODIFICAR PEDIDO ===");
        System.out.println("1 - Acrescentar item");
        System.out.println("2 - Remover item");
        System.out.println("3 - Cancelar pedido");
        System.out.println("0 - Voltar");
    }

    public int lerOpcaoModificacao() {

        return lerOpcaoDentroDoIntervalo(
                "Escolha uma opção: ", 0, 3
        );
    }

    public void exibirItensNumerados(Pedido pedido) {

        System.out.println("\n=== ITENS DO PEDIDO ===");

        if (pedido.getItens().isEmpty()) {
            System.out.println("Este pedido não possui itens.");
            return;
        }

        for (int i = 0; i < pedido.getItens().size(); i++) {

            ItemCardapio item = pedido.getItens().get(i);

            System.out.println(
                    (i + 1) + " - " + item.getDescricao()
                            + " | R$ " + item.getPreco()
            );
        }
    }

    public int lerNumeroItem(int quantidadeItens) {

        return lerOpcaoDentroDoIntervalo(
                "Digite o número do item que deseja remover: ",
                1,
                quantidadeItens
        );
    }

    public void exibirPedido(Pedido pedido) {

        System.out.println("\nPedido nº " + pedido.getNumero());
        System.out.println("Cliente: " + pedido.getNomeCliente());

        for (ItemCardapio item : pedido.getItens()) {

            System.out.println(
                    item.getDescricao() + " - R$ " + item.getPreco()
            );
        }

        System.out.println("Total: R$ " + pedido.calcularTotal());
    }

    public void exibirPedidos(Collection<Pedido> pedidos) {

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }

        for (Pedido pedido : pedidos) {
            exibirPedido(pedido);
        }
    }

    public void exibirFechamentoDoDia(double total) {

        System.out.println("\n=== FECHAMENTO DO DIA ===");
        System.out.println("Faturamento total: R$ " + total);
        System.out.println("Restaurante fechado!");
    }

    public void exibirCombinacoesDiferentes(
            Collection<String> combinacoes
    ) {

        System.out.println("\n=== COMBINAÇÕES DIFERENTES ===");
        System.out.println("Quantidade: " + combinacoes.size());

        for (String combinacao : combinacoes) {
            System.out.println("- " + combinacao);
        }
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}