package br.com.fiap.fintech.model;

import br.com.fiap.fintech.enums.TipoOperacao;
import java.util.Date;

public class Operacao {
	
	private Integer id;
	private String nome;
	private String descricao;
	private TipoOperacao tipoOperacao;
	private Date dataHora;
	private Double montante;
	private Integer contaId;
	
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
	
	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}
	
	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	
	public Date getDataHora() {
		return dataHora;
	}
	
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	
	public Double getMontante() {
		return montante;
	}
	
	public void setMontante(Double montante) {
		this.montante = montante;
	}
	
	public Integer getContaId() {
		return contaId;
	}
	
	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}
	
	@Override
	public String toString() {
		return "ID: " + id
				+ "\nNome: " + nome
				+ "\nDescrição: " + descricao
				+ "\nTipo Operação: " + tipoOperacao
				+ "\nData e Hora da Operação: " + dataHora
				+ "\nMontante: " + montante
				+ "\nId da Conta: " + contaId;
	}
	
}
