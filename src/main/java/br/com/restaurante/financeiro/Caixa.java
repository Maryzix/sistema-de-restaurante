package br.com.restaurante.financeiro;

import br.com.restaurante.pedido.Pedido;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Caixa {

    private static final Caixa INSTANCIA = new Caixa();

    private Set<Transacao> transacoes = new HashSet<>();

    private Caixa() {
    }

    public static Caixa getInstance() {
        return INSTANCIA;
    }

    public boolean registrarPagamento(Pedido pedido, double gorjeta) {

        if (pedido == null) {
            throw new IllegalArgumentException(
                    "O pedido não pode ser nulo."
            );
        }

        if (pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException(
                    "Não é possível pagar um pedido sem itens."
            );
        }

        if (gorjeta < 0 || !Double.isFinite(gorjeta)) {
            throw new IllegalArgumentException(
                    "A gorjeta deve ser um valor válido e não negativo."
            );
        }

        if (pedido.isPago()) {
            return false;
        }

        Transacao transacao = new Transacao(
                pedido.getNumero(),
                pedido.calcularTotal(),
                gorjeta
        );

        boolean registrado = transacoes.add(transacao);

        if (registrado) {
            pedido.setPago(true);
        }

        return registrado;
    }

    public Set<Transacao> getTransacoes() {
        return Collections.unmodifiableSet(transacoes);
    }

    public int getQuantidadePedidosPagos() {
        return transacoes.size();
    }

    public double calcularFaturamento() {

        double total = 0;

        for (Transacao transacao : transacoes) {
            total += transacao.getValorPedido();
        }

        return total;
    }

    public double calcularTotalGorjetas() {

        double total = 0;

        for (Transacao transacao : transacoes) {
            total += transacao.getGorjeta();
        }

        return total;
    }

    public double calcularTotalRecebido() {
        return calcularFaturamento() + calcularTotalGorjetas();
    }

    // Usado para isolar os testes automatizados.
    public void limparParaTestes() {
        transacoes.clear();
    }
}