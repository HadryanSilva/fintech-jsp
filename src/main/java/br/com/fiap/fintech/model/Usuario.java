package br.com.fiap.fintech.model;

import br.com.fiap.fintech.enums.RoleUsuario;
import java.util.Date;

public class Usuario {
	
	private Integer id;
	private String name;
	private String email;
	private String password;
	private RoleUsuario role;
	private Integer contaId;
	private Date dataCriacao;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public RoleUsuario getRole() {
		return role;
	}
	
	public void setRole(RoleUsuario role) {
		this.role = role;
	}
	
	public Integer getContaId() {
		return contaId;
	}
	
	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@Override
	public String toString() {
		return "ID: " + id
				+ "\nNome: " + name
				+ "\nEmail: " + email
				+ "\nPassword" + password
				+ "\nRole: " + role
				+ "\nId da Conta: " + contaId
				+ "\nData Criação: " + dataCriacao;
	}
	
}
