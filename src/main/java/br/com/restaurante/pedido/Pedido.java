package br.com.restaurante.pedido;

import br.com.restaurante.cardapio.ItemCardapio;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Pedido {

    private int numero;
    private String nomeCliente;
    private ArrayList<ItemCardapio> itens;
    private boolean pago;

    public Pedido(int numero, String nomeCliente) {

        this.numero = numero;
        this.itens = new ArrayList<>();
        this.pago = false;

        setNomeCliente(nomeCliente);
    }

    public void setNomeCliente(String nomeCliente) {

        if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O nome do cliente não pode ser nulo ou vazio."
            );
        }

        this.nomeCliente = nomeCliente;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public void adicionarItem(ItemCardapio item) {

        if (item == null) {
            throw new IllegalArgumentException(
                    "O item não pode ser nulo."
            );
        }

        if (pago) {
            throw new IllegalStateException(
                    "Não é possível alterar um pedido já pago."
            );
        }

        itens.add(item);
    }

    public ItemCardapio removerItem(int numeroItem) {

        if (pago) {
            throw new IllegalStateException(
                    "Não é possível alterar um pedido já pago."
            );
        }

        if (numeroItem < 1 || numeroItem > itens.size()) {
            throw new IllegalArgumentException(
                    "Número de item inválido."
            );
        }

        return itens.remove(numeroItem - 1);
    }

    public double calcularTotal() {

        double total = 0;

        for (ItemCardapio item : itens) {
            total += item.getPreco();
        }

        return total;
    }
}