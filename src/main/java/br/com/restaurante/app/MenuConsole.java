package br.com.restaurante.app;

import org.example.pedido.Pedido;

import java.util.Scanner;

public class MenuConsole {
    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1 - Criar pedido");
        System.out.println("2 - Buscar pedido");
        System.out.println("3 - Fechamento do dia");
        System.out.println("0 - Sair");
    }

    public int lerOpcao() {
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int lerOpcaoLanche() {
        System.out.println("\n=== ESCOLHA O LANCHE ===");
        // Adicione aqui as opções de lanches do item 1.5.
        System.out.print("Escolha o número do lanche: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void exibirPedido(Pedido pedido) {
        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }

        System.out.println("\n=== PEDIDO ===");
        System.out.println("Número: " + pedido.getNumero());
        System.out.println("Total: R$ " + pedido.calcularTotal());
    }

    public void exibirFechamentoDoDia(double total) {
        System.out.println("\n=== FECHAMENTO DO DIA ===");
        System.out.println("Faturamento total: R$ " + total);
    }
}
