package com.dextra.lanchonete.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private long id;
	private List<Ingredientes> ingredientes = new ArrayList<>();

	public double getValorTotalPedido() {
		int desconto = 0;
		int muitaCarne = 0;
		int muitoQueijo = 0;
		double valorTotalPedido = 0.0;

		// promoção Light
		if (this.ingredientes.contains(Ingredientes.ALFACE) && !this.ingredientes.contains(Ingredientes.BACON)) {
			desconto = 10;
		}

		// promoção muita carne
		muitaCarne = (int) this.ingredientes.stream().filter(i -> i.equals(Ingredientes.HAMBURGER)).count();
		if (muitaCarne > 2) {
			int quantidadeDescontar = muitaCarne / 3;

			while (quantidadeDescontar > 0) {
				valorTotalPedido -= Ingredientes.HAMBURGER.getValor();
				quantidadeDescontar--;
			}
		}

		// promoção muito queijo
		muitoQueijo = (int) this.ingredientes.stream().filter(i -> i.equals(Ingredientes.QUEIJO)).count();
		if (muitoQueijo > 2) {
			int quantidadeDescontar = muitoQueijo / 3;
			while (quantidadeDescontar > 0) {
				valorTotalPedido -= Ingredientes.QUEIJO.getValor();
				quantidadeDescontar--;
			}
		}

		// soma total dos ingredientes
		for (Ingredientes i : this.ingredientes) {
			valorTotalPedido += i.getValor();
		}

		// verifica o desconto da promoção light
		if (desconto != 0) {
			double v = valorTotalPedido * 0.1;
			valorTotalPedido = valorTotalPedido - v;
		}

		return valorTotalPedido;
	}

	public void adicionarIngredientes(Ingredientes ingrediente) {
		this.ingredientes.add(ingrediente);
	}

	public long getId() {
		return id;
	}

	public List<Ingredientes> getIngredientes() {
		return this.ingredientes;
	}
}
