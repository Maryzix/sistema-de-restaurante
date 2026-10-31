package br.com.restaurante.financeiro;

import java.util.Objects;

public class Transacao {

    private int numeroPedido;
    private double valorPedido;
    private double gorjeta;

    public Transacao(int numeroPedido, double valorPedido, double gorjeta) {

        if (numeroPedido <= 0) {
            throw new IllegalArgumentException(
                    "Número do pedido inválido."
            );
        }

        if (valorPedido < 0 || gorjeta < 0) {
            throw new IllegalArgumentException(
                    "Os valores não podem ser negativos."
            );
        }

        this.numeroPedido = numeroPedido;
        this.valorPedido = valorPedido;
        this.gorjeta = gorjeta;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public double getValorPedido() {
        return valorPedido;
    }

    public double getGorjeta() {
        return gorjeta;
    }

    public double getTotalPago() {
        return valorPedido + gorjeta;
    }

    @Override
    public boolean equals(Object objeto) {

        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Transacao)) {
            return false;
        }

        Transacao outra = (Transacao) objeto;

        return numeroPedido == outra.numeroPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroPedido);
    }
}