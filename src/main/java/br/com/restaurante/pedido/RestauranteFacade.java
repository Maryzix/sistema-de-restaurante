package br.com.restaurante.pedido;

import br.com.restaurante.cardapio.ItemCardapio;
import br.com.restaurante.cardapio.Lanche;
import br.com.restaurante.cardapio.LancheFactory;
import br.com.restaurante.financeiro.Caixa;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RestauranteFacade {

    private GerenciadorDePedidos gerenciador =
            new GerenciadorDePedidos();

    private Caixa caixa = Caixa.getInstance();

    public Pedido criarPedido(String nomeCliente) {
        return gerenciador.criarPedido(nomeCliente);
    }

    public Pedido buscarPedido(int numero) {
        return gerenciador.buscarPedido(numero);
    }

    public void adicionarLanche(Pedido pedido, ItemCardapio lanche) {
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

        Pedido pedido = buscarPedido(numero);

        if (pedido == null || pedido.isPago()) {
            return false;
        }

        return gerenciador.cancelarPedido(numero);
    }

    public Collection<Pedido> listarPedidos() {
        return gerenciador.getPedidos();
    }

    public double calcularTotalDoDia() {
        return gerenciador.calcularTotalDoDia();
    }

    public boolean registrarPagamento(int numeroPedido, double gorjeta) {

        Pedido pedido = buscarPedido(numeroPedido);

        if (pedido == null) {
            return false;
        }

        return caixa.registrarPagamento(pedido, gorjeta);
    }

    public Caixa getCaixa() {
        return caixa;
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

    public Set<String> listarLanchesBaseDiferentes() {

        Set<String> lanchesBase = new HashSet<>();

        for (Pedido pedido : gerenciador.getPedidos()) {

            for (ItemCardapio item : pedido.getItens()) {
                lanchesBase.add(item.getNomeBase());
            }
        }

        return lanchesBase;
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