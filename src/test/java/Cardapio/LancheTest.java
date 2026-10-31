package Cardapio;

import br.com.restaurante.cardapio.ComCheddar;
import br.com.restaurante.cardapio.ItemCardapio;
import br.com.restaurante.cardapio.Lanche;
import br.com.restaurante.cardapio.LancheFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LancheTest {

    @Test
    void deveRecusarPrecoNegativo() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Lanche("X-Burger", -10.00);
        });
    }

    @Test
    void xBaconComCheddarCusta26() {

        ItemCardapio lanche = new ComCheddar(
                LancheFactory.xBacon()
        );

        assertEquals(26.0, lanche.getPreco(), 0.001);
    }
}