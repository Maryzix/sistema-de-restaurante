package br.com.restaurante.app;

import br.com.restaurante.cardapio.*;
import br.com.restaurante.financeiro.Caixa;
import br.com.restaurante.financeiro.Transacao;
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

    // Lê valores monetários, aceitando 5,50 ou 5.50.
    public double lerValorMonetario(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String entrada = scanner.nextLine()
                    .trim()
                    .replace(",", ".");

            try {

                double valor = Double.parseDouble(entrada);

                if (Double.isFinite(valor) && valor >= 0) {
                    return valor;
                }

                System.out.println(
                        "Digite um valor válido e não negativo."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Valor inválido! Exemplo: 5,50."
                );
            }
        }
    }

    public void exibirMenu() {

        System.out.println("\n=== RESTAURANTE ===");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Fechar restaurante");
        System.out.println("4 - Modificar pedido");
        System.out.println("5 - Registrar pagamento");
        System.out.println("0 - Sair");
    }

    public int lerOpcao() {
        return lerOpcaoDentroDoIntervalo(
                "Escolha uma opção: ", 0, 5
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

    public ItemCardapio personalizarLanche(Lanche lanche) {

        ItemCardapio itemPersonalizado = lanche;
        boolean personalizando = true;

        while (personalizando) {

            System.out.println("\n=== PERSONALIZE SEU LANCHE ===");
            System.out.println(
                    "Lanche: " + itemPersonalizado.getDescricao()
            );
            System.out.printf(
                    "Preço atual: R$ %.2f%n",
                    itemPersonalizado.getPreco()
            );

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
                    System.out.println(
                            "Esse lanche não possui queijo."
                    );
                }
                break;

            case 2:
                if (lanche.isAlface()) {
                    lanche.setAlface(false);
                    System.out.println("Alface removida!");
                } else {
                    System.out.println(
                            "Esse lanche não possui alface."
                    );
                }
                break;

            case 3:
                if (lanche.isTomate()) {
                    lanche.setTomate(false);
                    System.out.println("Tomate removido!");
                } else {
                    System.out.println(
                            "Esse lanche não possui tomate."
                    );
                }
                break;

            case 4:
                if (lanche.isCebola()) {
                    lanche.setCebola(false);
                    System.out.println("Cebola removida!");
                } else {
                    System.out.println(
                            "Esse lanche não possui cebola."
                    );
                }
                break;

            case 0:
                break;
        }
    }

    private ItemCardapio adicionarAdicional(ItemCardapio item) {

        System.out.println("\n=== ADICIONAR INGREDIENTES ===");

        System.out.println("1 - Queijo extra | R$ 3,00");
        System.out.println("2 - Alface extra | R$ 1,50");
        System.out.println("3 - Tomate extra | R$ 1,50");
        System.out.println("4 - Cebola extra | R$ 1,00");
        System.out.println("5 - Cheddar      | R$ 4,00");
        System.out.println("6 - Bacon extra  | R$ 6,00");
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

            System.out.printf(
                    "%d - %s | R$ %.2f%n",
                    i + 1,
                    item.getDescricao(),
                    item.getPreco()
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

            System.out.printf(
                    "%s - R$ %.2f%n",
                    item.getDescricao(),
                    item.getPreco()
            );
        }

        System.out.printf(
                "Total: R$ %.2f%n",
                pedido.calcularTotal()
        );

        System.out.println(
                "Status: " + (pedido.isPago() ? "Pago" : "Pendente")
        );
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

    public void exibirFechamentoDoDia(
            Caixa caixa,
            Collection<String> combinacoes,
            Collection<String> lanchesBase
    ) {

        System.out.println("\n========== FECHAMENTO DO DIA ==========");

        System.out.println(
                "Pedidos pagos: " + caixa.getQuantidadePedidosPagos()
        );

        System.out.printf(
                "Faturamento dos pedidos: R$ %.2f%n",
                caixa.calcularFaturamento()
        );

        System.out.printf(
                "Total de gorjetas: R$ %.2f%n",
                caixa.calcularTotalGorjetas()
        );

        System.out.printf(
                "TOTAL RECEBIDO: R$ %.2f%n",
                caixa.calcularTotalRecebido()
        );

        System.out.println(
                "Combinações diferentes: " + combinacoes.size()
        );

        for (String combinacao : combinacoes) {
            System.out.println("- " + combinacao);
        }

        System.out.println(
                "Lanches base diferentes: " + lanchesBase.size()
        );

        for (String lanche : lanchesBase) {
            System.out.println("- " + lanche);
        }

        System.out.println("=======================================");
        System.out.println("Restaurante fechado!");
    }

    public void exibirTransacoes(Caixa caixa) {

        System.out.println("\n=== TRANSAÇÕES REGISTRADAS ===");

        if (caixa.getTransacoes().isEmpty()) {
            System.out.println("Nenhum pagamento registrado.");
            return;
        }

        for (Transacao transacao : caixa.getTransacoes()) {

            System.out.printf(
                    "Pedido nº %d | Pedido: R$ %.2f | Gorjeta: R$ %.2f | Total: R$ %.2f%n",
                    transacao.getNumeroPedido(),
                    transacao.getValorPedido(),
                    transacao.getGorjeta(),
                    transacao.getTotalPago()
            );
        }
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}