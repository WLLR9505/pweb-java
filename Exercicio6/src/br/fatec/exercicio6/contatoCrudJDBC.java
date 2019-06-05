package br.fatec.exercicio6;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class contatoCrudJDBC {

		public void salvar(contato contato) {
			Connection conexao = this.geraConexao();
			PreparedStatement insereSt = null;
			// o codigo é auto incremente
			String sql = "insert into contato (nome, telefone, email, dt_cad, obs) values (?, ?, ?, ?, ?)";

			try {
				insereSt = conexao.prepareStatement(sql);
				insereSt.setString(1, contato.getNome());
				insereSt.setString(2, contato.getTelefone());
				insereSt.setString(3, contato.getEmail());
				insereSt.setDate(4, contato.getDataCadastro());
				insereSt.setString(5, contato.getObservacao());
				insereSt.executeUpdate();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao incluir contato" + e.getMessage());
			}
		}

		public void atualizar(contato contato) {
			Connection conexao = this.geraConexao();
			PreparedStatement insereSt = null;
			// o codigo nao pode ser alterado
			String sql = "update from contato (nome, telefone, email, dt_cad, obs) values (?, ?, ?, ?, ?) where codigo=?";

			try {
				insereSt = conexao.prepareStatement(sql);
				insereSt.setString(1, contato.getNome());
				insereSt.setString(2, contato.getTelefone());
				insereSt.setString(3, contato.getEmail());
				insereSt.setDate(4, contato.getDataCadastro());
				insereSt.setString(5, contato.getObservacao());
				insereSt.setInt(6, contato.getCodigo());
				insereSt.executeUpdate();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao alterar contato" + e.getMessage());
			}
		}

		public void excluir(contato contato) {
			Connection conexao = this.geraConexao();
			PreparedStatement insereSt = null;
			// o codigo nao pode ser alterado
			String sql = "delete from contato where codigo=?";

			try {
				insereSt = conexao.prepareStatement(sql);
				insereSt.setInt(1, contato.getCodigo());
				insereSt.executeUpdate();
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao excluir contato" + e.getMessage());
			}

		}

		public List<contato> listar() {
			Connection conexao = this.geraConexao();
			List<contato> contatos = new ArrayList<contato>();
			Statement consulta = null;
			ResultSet resultado = null;
			contato contato = null;
			String sql = "select * from contato";
			try {
				consulta = conexao.createStatement(); // nao é preparada (não
														// precisa de parametros)
				resultado = consulta.executeQuery(sql); // utilizado em consultas
				while (resultado.next()) {
					contato = new contato();
					contato.setCodigo(resultado.getInt("codigo"));
					contato.setNome(resultado.getString("nome"));
					contato.setTelefone(resultado.getString("telefone"));
					contato.setEmail(resultado.getString("email"));
					contato.setDataCadastro(resultado.getDate("dt_cad"));
					contato.setObservacao(resultado.getString("obs"));
					contatos.add(contato);
				}

				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao listar contato " + e.getMessage());

			} finally {
				try {
					consulta.close();
					resultado.close();
					conexao.close();
				} catch (Throwable e) {
					System.out.println("Erro ao fechar operação consulta " + e.getMessage());

				}
			}
			return contatos; // retorna contatos
		}

		public contato buscacontato(int valor) {
			Connection conexao = this.geraConexao();
			PreparedStatement consulta = null;
			ResultSet resultado = null;
			contato contato = null;
			String sql = "select * from contato  where codigo=?";
			try {
				consulta = conexao.prepareStatement(sql); // é preparada (precisa de
															// parametros)
				consulta.setInt(1, valor);
				resultado = consulta.executeQuery(sql); // utilizado em consultas
				while (resultado.next()) {
					contato = new contato();
					contato.setCodigo(resultado.getInt("codigo"));
					contato.setNome(resultado.getString("nome"));
					contato.setTelefone(resultado.getString("telefone"));
					contato.setEmail(resultado.getString("email"));
					contato.setDataCadastro(resultado.getDate("codigo"));
					contato.setObservacao(resultado.getString("codigo"));
				}
				conexao.close();

			} catch (SQLException e) {
				System.out.println("Erro ao listar contato " + e.getMessage());

			} finally {
				try {
					consulta.close();
					resultado.close();
					conexao.close();
				} catch (Throwable e) {
					System.out.println("Erro ao fechar operação consulta " + e.getMessage());
				}
			}
			return contato;
		}

		public Connection geraConexao() {
			Connection conexao = null;
			try {
				// registrando a classe JDBC e os prametros de conexao em tempo de
				// execucao
				Class.forName("org.apache.derby.jdbc.ClientDriver");

				String url = "jdbc:derby://localhost:1527/agenda;create=true;update=true';";

				String usuario = "app";
				String senha = "app";
				conexao = DriverManager.getConnection(url, usuario, senha);
				System.out.println("Conectou");
			} catch (SQLException e) {
				System.out.println("Ocorreu um erro de conexao " + e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println("Ocorreu um erro de noa encontrou classe " + e.getMessage());
			}
			return conexao;
		}

		public static void main(String[] args) {
			contatoCrudJDBC contatoCRUDJDBC = new contatoCrudJDBC();

			// criando um contato
			contato beltrano = new contato();
			beltrano.setNome("Beltrano da Silva");
			beltrano.setTelefone("1234");
			beltrano.setEmail("beltrano@gmail.com");
			beltrano.setDataCadastro(new Date(System.currentTimeMillis()));
			beltrano.setObservacao("novo contato");
			contatoCRUDJDBC.salvar(beltrano);

			// criando outro contato
			contato fulano = new contato();
			fulano.setNome("Fulano de Oliveira");
			fulano.setTelefone("9999");
			fulano.setEmail("fulano@gmail.com");
			fulano.setDataCadastro(new Date(System.currentTimeMillis()));
			fulano.setObservacao("outro novo contato");
			contatoCRUDJDBC.salvar(fulano);

			System.out.println("contatos Cadastrados " + contatoCRUDJDBC.listar().size());

		}
}
