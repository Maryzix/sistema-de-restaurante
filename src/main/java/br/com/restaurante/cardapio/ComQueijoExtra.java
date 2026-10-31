package br.com.restaurante.cardapio;

public class ComQueijoExtra implements ItemCardapio {

    private ItemCardapio item;

    public ComQueijoExtra(ItemCardapio item) {
        this.item = item;
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + queijo extra";
    }

    @Override
    public String getNomeBase() {
        return item.getNomeBase();
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 3.00;
    }
}