package br.fatec.exercicio8.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.fatec.exercicio8.model.Usuario;

public class UsuarioDAOHibernate implements UsuarioDAO {

	// obrigatório para classe hibernate
	// por meio da Session as operacoes do Hibernate chegam ao Banco de Dados

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Usuario usuario) {
		try {
			this.session.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.save(usuario);
			this.session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir o contato. Erro: " + e.getMessage());
		}
	}

	public void atualizar(Usuario usuario) {
		try {
			this.session.getSessionFactory().openSession();
			session.beginTransaction();
			this.session.update(usuario);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar o contato. Erro: " + e.getMessage());
		}
	}

	public void excluir(Usuario usuario) {
		try {
			this.session.getSessionFactory().openSession();
			session.beginTransaction();
			this.session.delete(usuario);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir o contato. Erro: " + e.getMessage());
		}
	}

	// como se fosse o select, busca pela chave, se nao existir retorna null
	// se usar load ao inves de get será gerada uma excecao

	public Usuario carregar(Integer codigo) {
		this.session.getSessionFactory().openSession();
		session.beginTransaction();
		return (Usuario) this.session.get(Usuario.class, codigo);
	}

	// select sem criterio

	@SuppressWarnings("unchecked")
	public List<Usuario> listar() {
		this.session.getSessionFactory().openSession();
		session.beginTransaction();
		return this.session.createCriteria(Usuario.class).list();
	}

	// usando hibernate query sql (parecida com sql)
	// busca outros campos

	public Usuario buscarPorLogin(String login) {
		this.session.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "select u from Usuario u where u.login=:login";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("login", login); // parametro
		return (Usuario) consulta.uniqueResult(); // como sabe-se que login é
													// chave naturam, entao
													// chama-se uniqueresult
													// senao usava
													// consulta.list()
	}

}
