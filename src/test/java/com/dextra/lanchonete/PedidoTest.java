package com.dextra.lanchonete;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.dextra.lanchonete.model.Ingredientes;
import com.dextra.lanchonete.model.Pedido;

public class PedidoTest {

	private Pedido pedido;

	@Before
	public void setup() {
		pedido = new Pedido();
	}

	@Test
	public void deveVerificarPromocaoLight() throws Exception {
		pedido.getIngredientes().add(Ingredientes.ALFACE);
		double v = Ingredientes.ALFACE.getValor() * 0.1;
		assertEquals(Ingredientes.ALFACE.getValor() - v, pedido.getValorTotalPedido(), 0.00);
	}

	@Test
	public void deveVerificarPromocaoMuitaCarne() throws Exception {
		pedido.getIngredientes().add(Ingredientes.HAMBURGER);
		pedido.getIngredientes().add(Ingredientes.HAMBURGER);
		pedido.getIngredientes().add(Ingredientes.HAMBURGER);
		int i = pedido.getIngredientes().size() / 3;
		assertEquals(Ingredientes.HAMBURGER.getValor() * (pedido.getIngredientes().size() - i),
				pedido.getValorTotalPedido(), 0.00);
	}

	@Test
	public void deveVerificarPromocaoMuitoQueijo() throws Exception {
		pedido.getIngredientes().add(Ingredientes.QUEIJO);
		pedido.getIngredientes().add(Ingredientes.QUEIJO);
		pedido.getIngredientes().add(Ingredientes.QUEIJO);
		int i = pedido.getIngredientes().size() / 3;
		assertEquals(Ingredientes.QUEIJO.getValor() * (pedido.getIngredientes().size() - i),
				pedido.getValorTotalPedido(), 0.00);
	}

}
