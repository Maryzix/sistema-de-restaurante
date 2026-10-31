package br.com.restaurante.pedido;

import br.com.restaurante.cardapio.ItemCardapio;
import br.com.restaurante.cardapio.Lanche;
import br.com.restaurante.cardapio.LancheFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RestauranteFacade {

    private GerenciadorDePedidos gerenciador = new GerenciadorDePedidos();

    public Pedido criarPedido(String nomeCliente) {
        return gerenciador.criarPedido(nomeCliente);
    }

    public Pedido buscarPedido(int numero) {
        return gerenciador.buscarPedido(numero);
    }

    public void adicionarLanche(Pedido pedido, Lanche lanche) {
        pedido.adicionarItem(lanche);
    }

    public boolean removerItem(Pedido pedido, int numeroItem) {

        if (pedido == null) {
            return false;
        }

        pedido.removerItem(numeroItem);
        return true;
    }

    public boolean cancelarPedido(int numero) {
        return gerenciador.cancelarPedido(numero);
    }

    public Collection<Pedido> listarPedidos() {
        return gerenciador.getPedidos();
    }

    public double calcularTotalDoDia() {
        return gerenciador.calcularTotalDoDia();
    }

    public Set<String> listarCombinacoesDiferentes() {

        Set<String> combinacoes = new HashSet<>();

        for (Pedido pedido : gerenciador.getPedidos()) {

            for (ItemCardapio item : pedido.getItens()) {

                combinacoes.add(item.getDescricao());
            }
        }

        return combinacoes;
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