package br.com.restaurante.app;

import br.com.restaurante.cardapio.ItemCardapio;
import br.com.restaurante.cardapio.Lanche;
import br.com.restaurante.financeiro.Caixa;
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

                        ItemCardapio itemPersonalizado =
                                menu.personalizarLanche(lanche);

                        sistema.adicionarLanche(
                                pedido,
                                itemPersonalizado
                        );

                        adicionar = menu.querAdicionarItem();
                    }

                    menu.exibirPedido(pedido);
                    break;

                case 2:

                    menu.exibirPedidos(sistema.listarPedidos());
                    break;

                case 3:

                    menu.exibirFechamentoDoDia(
                            sistema.getCaixa(),
                            sistema.listarCombinacoesDiferentes(),
                            sistema.listarLanchesBaseDiferentes()
                    );

                    menu.exibirTransacoes(sistema.getCaixa());

                    executando = false;
                    break;

                case 4:

                    int numeroPedido = menu.lerNumeroPedido();

                    Pedido pedidoEncontrado =
                            sistema.buscarPedido(numeroPedido);

                    if (pedidoEncontrado == null) {
                        menu.exibirMensagem("Pedido não encontrado!");
                        break;
                    }

                    menu.exibirPedido(pedidoEncontrado);

                    boolean modificando = true;

                    while (modificando) {

                        menu.exibirMenuModificacao();

                        int opcaoModificacao =
                                menu.lerOpcaoModificacao();

                        switch (opcaoModificacao) {

                            case 1:

                                int escolha = menu.lerOpcaoLanche();

                                Lanche novoLanche =
                                        sistema.criarLanche(escolha);

                                ItemCardapio novoItem =
                                        menu.personalizarLanche(novoLanche);

                                sistema.adicionarLanche(
                                        pedidoEncontrado,
                                        novoItem
                                );

                                menu.exibirMensagem(
                                        "Item adicionado com sucesso!"
                                );

                                menu.exibirPedido(pedidoEncontrado);
                                break;

                            case 2:

                                menu.exibirItensNumerados(pedidoEncontrado);

                                if (pedidoEncontrado.getItens().isEmpty()) {
                                    break;
                                }

                                int numeroItem = menu.lerNumeroItem(
                                        pedidoEncontrado.getItens().size()
                                );

                                sistema.removerItem(
                                        pedidoEncontrado,
                                        numeroItem
                                );

                                menu.exibirMensagem(
                                        "Item removido com sucesso!"
                                );

                                menu.exibirPedido(pedidoEncontrado);
                                break;

                            case 3:

                                boolean cancelado =
                                        sistema.cancelarPedido(numeroPedido);

                                if (cancelado) {
                                    menu.exibirMensagem(
                                            "Pedido cancelado com sucesso!"
                                    );
                                } else {
                                    menu.exibirMensagem(
                                            "Não foi possível cancelar o pedido."
                                    );
                                }

                                modificando = false;
                                break;

                            case 0:

                                modificando = false;
                                break;
                        }
                    }

                    break;

                case 5:

                    int numeroPagamento = menu.lerNumeroPedido();

                    Pedido pedidoPagamento =
                            sistema.buscarPedido(numeroPagamento);

                    if (pedidoPagamento == null) {
                        menu.exibirMensagem("Pedido não encontrado!");
                        break;
                    }

                    if (pedidoPagamento.isPago()) {
                        menu.exibirMensagem(
                                "Este pedido já foi pago!"
                        );
                        break;
                    }

                    if (pedidoPagamento.getItens().isEmpty()) {
                        menu.exibirMensagem(
                                "Não é possível pagar um pedido sem itens."
                        );
                        break;
                    }

                    menu.exibirPedido(pedidoPagamento);

                    double gorjeta = menu.lerValorMonetario(
                            "Informe o valor da gorjeta (R$): "
                    );

                    boolean pagamentoRegistrado =
                            sistema.registrarPagamento(
                                    numeroPagamento,
                                    gorjeta
                            );

                    if (pagamentoRegistrado) {
                        menu.exibirMensagem(
                                "Pagamento registrado com sucesso!"
                        );
                    } else {
                        menu.exibirMensagem(
                                "Não foi possível registrar o pagamento."
                        );
                    }

                    break;

                case 0:

                    menu.exibirMensagem("Saindo do sistema...");
                    executando = false;
                    break;
            }
        }
    }
}