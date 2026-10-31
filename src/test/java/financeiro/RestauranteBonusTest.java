package financeiro;

import br.com.restaurante.cardapio.Lanche;
import br.com.restaurante.cardapio.LancheFactory;
import br.com.restaurante.cardapio.ComCheddar;
import br.com.restaurante.financeiro.Caixa;
import br.com.restaurante.pedido.Pedido;
import br.com.restaurante.pedido.RestauranteFacade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteBonusTest {

    private Caixa caixa;

    @BeforeEach
    void configurar() {
        caixa = Caixa.getInstance();
        caixa.limparParaTestes();
    }

    @Test
    void deveIgnorarCombinacoesComDescricaoRepetida() {

        RestauranteFacade sistema = new RestauranteFacade();

        Pedido pedido = sistema.criarPedido("Maria");

        Lanche primeiroLanche = LancheFactory.xBurger();
        Lanche segundoLanche = LancheFactory.xBurger();

        sistema.adicionarLanche(pedido, primeiroLanche);
        sistema.adicionarLanche(pedido, segundoLanche);

        assertEquals(
                1,
                sistema.listarCombinacoesDiferentes().size()
        );
    }

    @Test
    void deveCalcularFechamentoComPedidosEGorjetas() {

        Pedido pedido1 = new Pedido(101, "Maria");
        pedido1.adicionarItem(LancheFactory.xBurger());

        Pedido pedido2 = new Pedido(102, "João");
        pedido2.adicionarItem(LancheFactory.xBacon());

        assertTrue(caixa.registrarPagamento(pedido1, 2.00));
        assertTrue(caixa.registrarPagamento(pedido2, 5.00));

        assertEquals(
                38.00,
                caixa.calcularFaturamento(),
                0.001
        );

        assertEquals(
                7.00,
                caixa.calcularTotalGorjetas(),
                0.001
        );

        assertEquals(
                45.00,
                caixa.calcularTotalRecebido(),
                0.001
        );

        assertEquals(2, caixa.getQuantidadePedidosPagos());

        // O mesmo pedido não pode ser pago duas vezes.
        assertFalse(caixa.registrarPagamento(pedido1, 2.00));

        assertEquals(2, caixa.getQuantidadePedidosPagos());
    }

    @Test
    void deveCancelarPedidoNaoPago() {

        RestauranteFacade sistema = new RestauranteFacade();

        Pedido pedido = sistema.criarPedido("Ana");

        sistema.adicionarLanche(
                pedido,
                LancheFactory.xSalada()
        );

        int numeroPedido = pedido.getNumero();

        assertNotNull(sistema.buscarPedido(numeroPedido));

        assertTrue(sistema.cancelarPedido(numeroPedido));

        assertNull(sistema.buscarPedido(numeroPedido));

        assertFalse(sistema.cancelarPedido(numeroPedido));
    }
}