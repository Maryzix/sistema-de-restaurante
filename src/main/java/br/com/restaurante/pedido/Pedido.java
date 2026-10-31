package br.com.restaurante.pedido;

import lombok.Getter;
import br.com.restaurante.cardapio.Lanche;
import java.util.ArrayList;

@Getter
public class Pedido {
    private int numero;
    private String nomeCliente;
    private ArrayList<Lanche> itens = new ArrayList<>();

    public Pedido(int numero, String nomeCliente) {
        this.numero = numero;
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

    public void adicionarItem(Lanche lanche) {
        itens.add(lanche);
    }

    public double calcularTotal() {
        double total = 0;

        for (Lanche lanche : itens) {
            total += lanche.getPreco();
        }

        return total;
    }
}