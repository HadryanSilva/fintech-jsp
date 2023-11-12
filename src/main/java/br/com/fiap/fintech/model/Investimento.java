package br.com.fiap.fintech.model;

import br.com.fiap.fintech.enums.Categoria;

public class Investimento extends Operacao{
	
	private Integer id;
	private Categoria categoria;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return "ID: " + id
				+ "\nNome: " + super.getNome()
				+ "\nDescricao: " + super.getDescricao()
				+ "\nMontante: " + super.getMontante()
				+ "\nCategoria: " + categoria;
	}
	
}
