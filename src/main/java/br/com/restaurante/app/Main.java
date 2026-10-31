package br.com.restaurante.app;

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

                        lanche = menu.personalizarLanche(lanche);

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

                case 4:

                    int numeroPedido = menu.lerNumeroPedido();

                    Pedido pedidoEncontrado = sistema.buscarPedido(numeroPedido);

                    if (pedidoEncontrado == null) {
                        menu.exibirMensagem("Pedido não encontrado!");
                        break;
                    }

                    menu.exibirPedido(pedidoEncontrado);

                    boolean modificando = true;

                    while (modificando) {

                        menu.exibirMenuModificacao();

                        int opcaoModificacao = menu.lerOpcaoModificacao();

                        switch (opcaoModificacao) {

                            case 1:

                                int escolha = menu.lerOpcaoLanche();

                                Lanche novoLanche = sistema.criarLanche(escolha);

                                if (novoLanche == null) {
                                    menu.exibirMensagem("Opção de lanche inválida!");
                                    break;
                                }

                                novoLanche = menu.personalizarLanche(novoLanche);

                                sistema.adicionarLanche(
                                        pedidoEncontrado,
                                        novoLanche
                                );

                                menu.exibirMensagem("Item adicionado com sucesso!");
                                menu.exibirPedido(pedidoEncontrado);

                                break;

                            case 2:

                                menu.exibirItensNumerados(pedidoEncontrado);

                                if (pedidoEncontrado.getItens().isEmpty()) {
                                    break;
                                }

                                int numeroItem = menu.lerNumeroItem();

                                try {
                                    sistema.removerItem(
                                            pedidoEncontrado,
                                            numeroItem
                                    );

                                    menu.exibirMensagem("Item removido com sucesso!");

                                    menu.exibirPedido(pedidoEncontrado);

                                } catch (IllegalArgumentException e) {
                                    menu.exibirMensagem("Número de item inválido!");
                                }

                                break;

                            case 3:

                                boolean cancelado = sistema.cancelarPedido(numeroPedido);

                                if (cancelado) {
                                    menu.exibirMensagem("Pedido cancelado com sucesso!");
                                } else {
                                    menu.exibirMensagem("Não foi possível cancelar o pedido.");
                                }

                                modificando = false;
                                break;

                            case 0:
                                modificando = false;
                                break;

                            default:
                                menu.exibirMensagem("Opção inválida!");
                                break;
                        }
                    }

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