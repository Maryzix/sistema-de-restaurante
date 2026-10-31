package br.com.restaurante.cardapio;

public class ComAlfaceExtra implements ItemCardapio {

    private ItemCardapio item;

    public ComAlfaceExtra(ItemCardapio item) {
        this.item = item;
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + alface extra";
    }

    @Override
    public String getNomeBase() {
        return item.getNomeBase();
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 1.50;
    }
}