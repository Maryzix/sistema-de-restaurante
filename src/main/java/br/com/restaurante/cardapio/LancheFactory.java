package br.com.restaurante.cardapio;

public class LancheFactory {

    public static Lanche xBurger() {
        return new Lanche(
                "X-Burger (pão, hambúrguer, queijo)",
                16.00
        );
    }

    public static Lanche xSalada() {
        return new Lanche(
                "X-Salada (pão, hambúrguer, queijo, alface, tomate, cebola)",
                18.00
        );
    }

    public static Lanche xBacon() {
        return new Lanche(
                "X-Bacon (pão, hambúrguer, queijo, bacon, alface, tomate, cebola)",
                22.00
        );
    }
}