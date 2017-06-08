package com.dextra.lanchonete.model;

public enum Ingredientes {
	
	ALFACE("Alface", 0.4), 
	BACON("Bacon", 2.0),
	HAMBURGER("Hambúrguer de carne", 3.0),
	OVO("Ovo", 0.8),
	QUEIJO("Queijo", 1.5);
	
	private String descricao;
	private double valor;
	
	private Ingredientes(String descricao, double valor) {
		this.descricao = descricao;
		this.valor = valor;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public double getValor() {
		return valor;
	}

}
