package br.com.restaurante;

import br.com.restaurante.app.MenuConsole;
import br.com.restaurante.cardapio.Lanche;
import br.com.restaurante.pedido.Pedido;
import br.com.restaurante.pedido.RestauranteFacade;

public class Main {

    public static void main(String[] args) {

        MenuConsole menu = new MenuConsole();
        RestauranteFacade sistema = new RestauranteFacade();

        boolean executando = true;

        while (executando) {

            menu.exibirMenu();
            int opcao = menu.lerOpcao();

            switch (opcao) {

                case 1:
                    String nome = menu.lerNomeCliente();

                    Pedido pedido = sistema.criarPedido(nome);

                    boolean adicionar = true;

                    while (adicionar) {

                        int escolha = menu.lerOpcaoLanche();

                        Lanche lanche = sistema.criarLanche(escolha);

                        if (lanche == null) {
                            menu.exibirMensagem("Opção de lanche inválida!");
                            continue;
                        }

                        sistema.adicionarLanche(pedido, lanche);

                        adicionar = menu.querAdicionarItem();
                    }

                    menu.exibirPedido(pedido);
                    break;

                case 2:
                    menu.exibirPedidos(sistema.listarPedidos());
                    break;

                case 3:
                    double total = sistema.calcularTotalDoDia();

                    menu.exibirFechamentoDoDia(total);

                    executando = false;
                    break;

                case 0:
                    menu.exibirMensagem("Saindo do sistema...");
                    executando = false;
                    break;

                default:
                    menu.exibirMensagem("Opção inválida!");
                    break;
            }
        }
    }
}
