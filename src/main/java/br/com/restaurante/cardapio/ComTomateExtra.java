package br.com.restaurante.cardapio;

public class ComTomateExtra implements ItemCardapio {

    private ItemCardapio item;

    public ComTomateExtra(ItemCardapio item) {
        this.item = item;
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + tomate extra";
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 1.50;
    }
}