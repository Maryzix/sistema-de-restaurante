package br.com.restaurante.pedido;

import br.com.restaurante.cardapio.Lanche;

import java.util.Collection;

public class RestauranteFacade {

    private GerenciadorDePedidos gerenciador = new GerenciadorDePedidos();

    public Pedido criarPedido(String nomeCliente) {
        return gerenciador.criarPedido(nomeCliente);
    }

    public void adicionarLanche(Pedido pedido, Lanche lanche) {
        pedido.adicionarItem(lanche);
    }

    public Collection<Pedido> listarPedidos() {
        return gerenciador.getPedidos();
    }

    public double calcularTotalDoDia() {
        return gerenciador.calcularTotalDoDia();
    }

    public Lanche criarLanche(int opcao) {
        switch (opcao) {
            case 1:
                return new Lanche("X-Burger", 16.00);

            case 2:
                return new Lanche("X-Salada", 18.00);

            case 3:
                return new Lanche("X-Bacon", 22.00);

            default:
                return null;
        }
    }
}

