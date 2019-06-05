package br.fatec.exercicio4;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="user")
@RequestScoped

public class UsuarioBean {
	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirmasenha() {
		return confirmasenha;
	}
	public void setConfirmasenha(String confirmasenha) {
		this.confirmasenha = confirmasenha;
	}
	private String email;
	private String senha;
	private String confirmasenha;
	
	public String operacao() {
		return "resultado";
	}
	public String novo() {
		return "usuario";
	}
	public String Salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (!this.senha.equalsIgnoreCase(this.confirmasenha)) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "senhas diferentes", ""));
			return "usuario";
		}
		return "mostrausuario";
	}
	
	
}
