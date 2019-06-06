package br.fatec.exercicio8.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.fatec.exercicio8.model.Usuario;

@ManagedBean(name = "usuarioBean")
@RequestScoped

public class UsuarioBean {

	
	private Usuario usuario = new Usuario(); // propriedade do tipo Usuario
	private String confirmarSenha;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	// porta de entrada do formulário
	// preparar cadastro novo usuario
	public String novo() {
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "publico/usuario"; // tenta exibir usuario.xhtml
	}


	public String salvar() {
		// facescontext adiciona as mensagens de erro que possam ser criadas
		FacesContext context = FacesContext.getCurrentInstance();
		String senha = this.usuario.getSenha();
		if (!senha.equals(this.confirmarSenha)) { // adicionando mensagem ao
													// context
			FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null; // null nao caira em nenhuma pagina, fica na mesma de
							// origem
		}

		// se deu certo
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		return "usuariosucesso"; // tenta abrir pagina /public/usuariosucesso
									// nao colocou o publico mas
		// o contexto de salvar está em /publico
	}
}
