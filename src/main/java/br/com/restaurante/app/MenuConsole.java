package br.com.restaurante.app;

import br.com.restaurante.cardapio.ItemCardapio;
import br.com.restaurante.cardapio.Lanche;
import br.com.restaurante.pedido.Pedido;

import java.util.Collection;
import java.util.Scanner;

public class MenuConsole {

    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        System.out.println("\n=== RESTAURANTE ===");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Fechar restaurante");
        System.out.println("0 - Sair");
    }

    public int lerOpcao() {
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(scanner.nextLine());
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

        System.out.print("Escolha seu lanche: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public Lanche personalizarLanche(Lanche lanche) {

        System.out.println("\n=== PERSONALIZE SEU LANCHE ===");

        System.out.print("Deseja retirar o queijo? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            lanche.setQueijo(false);
        }

        System.out.print("Deseja retirar a alface? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            lanche.setAlface(false);
        }

        System.out.print("Deseja retirar o tomate? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            lanche.setTomate(false);
        }

        System.out.print("Deseja retirar a cebola? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            lanche.setCebola(false);
        }

        return lanche;
    }

    public boolean querAdicionarItem() {
        System.out.print("Deseja adicionar outro lanche? (s/n): ");
        return scanner.nextLine().equalsIgnoreCase("s");
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

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}