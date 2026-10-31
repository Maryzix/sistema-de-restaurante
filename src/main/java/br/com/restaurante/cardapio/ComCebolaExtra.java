package br.com.restaurante.cardapio;

public class ComCebolaExtra implements ItemCardapio {

    private ItemCardapio item;

    public ComCebolaExtra(ItemCardapio item) {
        this.item = item;
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + cebola extra";
    }

    @Override
    public String getNomeBase() {
        return item.getNomeBase();
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 1.00;
    }
}