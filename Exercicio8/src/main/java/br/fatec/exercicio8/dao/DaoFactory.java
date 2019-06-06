package br.fatec.exercicio8.dao;

import br.fatec.exercicio8.util.HibernateUtil;

public class DaoFactory {

	
	public static UsuarioDAO criaUsuarioDAO() {
	UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		      usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}



}
