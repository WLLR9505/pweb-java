package br.fatec.exercicio8.controller;

import java.util.List;

import br.fatec.exercicio8.dao.DaoFactory;
import br.fatec.exercicio8.dao.UsuarioDAO;
import br.fatec.exercicio8.model.Usuario;

// unica camada que podera ser comunicar com a camada de dados
// entretanto nao existe qqq referencia ao hibernate mostrando o alto 
// nivel de dedesacoplamento entre camada de acesso a dados e regra de negocio
// por enquanto so usuario so usuario tera  regra de negocio
public class UsuarioRN {
	// padrão formal criar essa propriedade e a instanciacao usando DaoFactory
	private UsuarioDAO usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = DaoFactory.criaUsuarioDAO();
	}

	// carrega uma instancia
	public Usuario carregar(Integer codigo) {
		return this.usuarioDAO.carregar(codigo);
	}

	  // faz repasse metodo na classe DAO
	public Usuario buscarPorLogin(String login) {
		return this.usuarioDAO.buscarPorLogin(login);
	}

	// se nao existe salva, caso contrario atualiza
	public void salvar(Usuario usuario) {
		Integer codigo = usuario.getCodigo();
		if (codigo == null || codigo == 0) {
			this.usuarioDAO.salvar(usuario);
		} else {
			this.usuarioDAO.atualizar(usuario);
		}
	}

	public void excluir(Usuario usuario) {
		this.usuarioDAO.excluir(usuario);
	}	

	public List<Usuario> listar() {
		return this.usuarioDAO.listar();
	}
}
