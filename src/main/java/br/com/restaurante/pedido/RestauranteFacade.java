package br.com.restaurante.pedido;

import br.com.restaurante.cardapio.Lanche;
import br.com.restaurante.cardapio.LancheFactory;

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
                return LancheFactory.xBurger();

            case 2:
                return LancheFactory.xSalada();

            case 3:
                return LancheFactory.xBacon();

            default:
                return null;
        }
    }
}

