package br.com.fiap.fintech.model;

import java.util.Date;

public class Conta {
	
	private Integer id;
	private String nome;
	private String descricao;
	private Date dataCriacao;
	private Double saldo;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public Double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	@Override
	public String toString() {
		return "ID: " + id
				+ "\nNome: " + nome
				+ "\nDescricao: " + descricao
				+ "\nData Criação: " + dataCriacao
				+ "\nSaldo: " + saldo;
	}
	
}
