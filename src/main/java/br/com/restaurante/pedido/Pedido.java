package br.com.restaurante.pedido;

import br.com.restaurante.cardapio.ItemCardapio;
import lombok.Getter;
import java.util.ArrayList;

@Getter
public class Pedido {

    private int numero;
    private String nomeCliente;
    private ArrayList<ItemCardapio> itens;

    public Pedido(int numero, String nomeCliente) {
        this.numero = numero;
        this.itens = new ArrayList<>();
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

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;

        for (ItemCardapio item : itens) {
            total += item.getPreco();
        }

        return total;
    }
}