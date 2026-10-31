package br.com.restaurante.cardapio;

public class Lanche implements ItemCardapio {

    private String nome;
    private double preco;

    private boolean queijo = true;
    private boolean alface = true;
    private boolean tomate = true;
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
    public double getPreco() {
        return preco;
    }

    public boolean isQueijo() {
        return queijo;
    }

    public void setQueijo(boolean queijo) {
        this.queijo = queijo;
    }

    public boolean isAlface() {
        return alface;
    }

    public void setAlface(boolean alface) {
        this.alface = alface;
    }

    public boolean isTomate() {
        return tomate;
    }

    public void setTomate(boolean tomate) {
        this.tomate = tomate;
    }

    public boolean isCebola() {
        return cebola;
    }

    public void setCebola(boolean cebola) {
        this.cebola = cebola;
    }
}