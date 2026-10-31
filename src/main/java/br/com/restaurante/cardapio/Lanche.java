package br.com.restaurante.cardapio;

import lombok.Getter;
import lombok.Setter;

public class Lanche implements ItemCardapio {

    private String nome;
    private double preco;

    @Setter
    @Getter
    private boolean queijo = true;
    @Setter
    @Getter
    private boolean alface = true;
    @Setter
    @Getter
    private boolean tomate = true;
    @Setter
    @Getter
    private boolean cebola = true;

    public Lanche(String nome, double preco) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O nome do lanche não pode ser vazio."
            );
        }

        if (preco < 0) {
            throw new IllegalArgumentException(
                    "O preço não pode ser negativo."
            );
        }

        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String getDescricao() {

        String descricao = nome;

        if (!queijo) descricao += " sem queijo";
        if (!alface) descricao += " sem alface";
        if (!tomate) descricao += " sem tomate";
        if (!cebola) descricao += " sem cebola";

        return descricao;
    }

    @Override
    public String getNomeBase() {
        return nome;
    }

    @Override
    public double getPreco() {
        return preco;
    }

}