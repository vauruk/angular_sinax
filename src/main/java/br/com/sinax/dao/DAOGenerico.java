package br.com.sinax.dao;

import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.cfg.Configuration;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.com.sinax.app.EntityApp;
import br.com.sinax.app.ExceptionApp;

/**
 * 
 * @author vanderson
 *
 */

public class DAOGenerico implements DAO {
	protected static Session hibernateSession;

	@SuppressWarnings ( "static-access" )
	public DAOGenerico(Session hibernateSession) {
		if (hibernateSession == null) {
			System.out.println("NUll Session");
		}
		this.hibernateSession = hibernateSession;
	}

	public Session getSession( ) {
		return hibernateSession;
	}

	/**
	 * Método responsavel por realizar o "load" de um objeto
	 * 
	 * @param obj
	 *        Tipo do objeto a ser lido
	 * @param arg
	 *        Chave(PK) do objeto a ser lido
	 * @return objeto carregado
	 * @throws Exception
	 */
	public EntityApp load(Class <? extends EntityApp> obj, Serializable arg) throws ExceptionApp {
		try {
			return (EntityApp) hibernateSession.get(obj, arg);
		} finally {
			// hibernateSession.close();
		}
	}

	public EntityApp load(Criteria criteria) throws ExceptionApp {
		try {
			return (EntityApp) criteria.uniqueResult();
		} finally {
			// closeSession();
		}
	}

	/**
	 * Metodo responsavel por recuperar uma lista de objetos do tipo
	 * EntidadeApp
	 * 
	 * @param Criteria
	 *        query com o filtro a ser recuperado
	 * @return lista de objetos
	 * @throws Exception
	 *         
	 */
	@Override
	public List <?> listar(CriteriaQuery<? extends EntityApp> criteria) throws ExceptionApp {
		try {
			return hibernateSession.createQuery( criteria ).getResultList();
		} finally {
			// closeSession();
		}
	}

	/**
	 * Metodo responsavel por recuperar uma lista de objetos do tipo
	 * EntidadeApp
	 * 
	 * @param hqlQuery
	 *        query com o filtro a ser recuperado
	 * @return lista de objetos
	 * @throws Exception
	 */
	public List <?> listar(TypedQuery<EntityApp> hqlQuery) throws ExceptionApp {
		try {
			return hqlQuery.getResultList();
		} finally {
			// closeSession();
		}
	}

	/**
	 * Metodo responsavel por gravar ou atualizar um objeto.
	 * 
	 * @param obj
	 *        Objeto a ser persistido
	 * @throws ExceptionApp
	 */
	@Override
	public void gravar(EntityApp obj) throws ExceptionApp {
		try {
			beginTransaction();
			hibernateSession.saveOrUpdate(obj);
			commitTransaction();
		} catch (Exception e) {
			if (e.getCause() instanceof StaleObjectStateException) {
				e = new ExceptionApp("erro ao atualizar");
			} else if (e.getCause() instanceof MySQLIntegrityConstraintViolationException) {
				throw new ExceptionApp("mensagem.erro.unique", e.getCause());
			} else {
				this.lancarExcecao(e);
			}
			rollbackTransaction();
			e.printStackTrace();
		} finally {
			closeSession();
		}
	}

	private void lancarExcecao(Exception e) throws ExceptionApp {
		e.printStackTrace();
		throw new ExceptionApp("", e);
	}

	/**
	 * Metodo responsavel em atualizar o objeto
	 * 
	 * @param obj
	 *        Objeto a ser atualizado
	 * @throws ExceptionApp
	 */
	public void alterar(EntityApp obj) throws ExceptionApp {
		try {
			beginTransaction();
			hibernateSession.update(obj);
			commitTransaction();
		} catch (ExceptionApp e) {
			rollbackTransaction();
			// this.lancarExcecao(e, LOGGER);
			e.printStackTrace();
		} finally {
			// closeSession();
		}
	}

	/**
	 * Metodo responsavel por gravar ou atualizar um objeto. Utilize este metodo
	 * quando precisar persistir e reutilizar o objeto na sequencia.
	 * 
	 * @param obj
	 *        Objeto a ser persistido
	 * @throws Exception
	 */
	public void merge(EntityApp obj) throws ExceptionApp {
		try {
			beginTransaction();
			hibernateSession.merge(obj);
			commitTransaction();
		} catch (ExceptionApp e) {
			rollbackTransaction();
			e.printStackTrace();
			// this.lancarExcecao(e, LOGGER);
		} finally {
			// closeSession();
		}
	}

	/**
	 * Metodo responsavel por recuperar uma lista de objetos do tipo
	 * EntidadeApp.
	 * 
	 * @param hqlQuery
	 *        query com o filtro a ser recuperado
	 * @return lista de objetos
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List <?> listar(String query) throws ExceptionApp {
		try {
			return this.listar(hibernateSession.createQuery(query));
		} catch (ExceptionApp e) {
			e.printStackTrace();
		} finally {
			// closeSession();
		}
		return null;
	}

	/**
	 * Metodo responsavel em eliminar um objeto persistente
	 * 
	 * @param obj
	 *        Objeto a ser excluido
	 * @throws ExceptionApp
	 *         Erro de exclusao do objeto
	 */
	public void excluir(EntityApp obj) throws ExceptionApp {
		try {
			beginTransaction();
			hibernateSession.delete(obj);
			hibernateSession.flush();
			commitTransaction();
		} catch (Exception e) {
			rollbackTransaction();
			// Caso ocorra erro de constraint
			if (e.getCause() instanceof MySQLIntegrityConstraintViolationException) {
				// if (e.getCause() instanceof ConstraintViolationException) {
				throw new ExceptionApp("mensagem.dependente", e.getCause());
			}
			e.printStackTrace();
			// this.lancarExcecao(new ExceptionApp(e.getMessage()), LOGGER);
		} finally {
			// closeSession();
		}
	}

	/**
	 * Metodo que "expulsa" o objeto da sessao do hibernate.
	 * evict = Expulsar
	 * 
	 * @param obj
	 */
	@SuppressWarnings ( "static-access" )
	public void limparCache(EntityApp obj) {
		this.hibernateSession.flush();
		this.hibernateSession.evict(obj);
	}

	@SuppressWarnings ( "static-access" )
	public void evict(EntityApp obj) {
		this.hibernateSession.evict(obj);
	}

	//private static final ThreadLocal <Session> threadSession = new ThreadLocal <Session>();
	//private static final ThreadLocal <Transaction> threadTransaction = new ThreadLocal <Transaction>();

	public static void beginTransaction( ) throws ExceptionApp {
		
		hibernateSession.getTransaction().begin();
		/*Transaction tx = (Transaction) threadTransaction.get();
		try {
			if (tx == null) {
				System.out.println("--->> Iniciando uma nova transacao com o banco de dados na ThreadLocal <<--");
				tx = hibernateSession.beginTransaction();
				threadTransaction.set(tx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	public static void commitTransaction( ) throws ExceptionApp {
		hibernateSession.getTransaction().commit();
	}

	public static void rollbackTransaction( ) throws ExceptionApp {
		hibernateSession.getTransaction().rollback();
	}

	/**
	 * @author vanderson
	 *         Closes the Session local to the thread.
	 */
	public static void closeSession( ) throws ExceptionApp {
		hibernateSession.close();
	}

	public Connection getConnection( ) {
		Configuration configuration = new Configuration().configure();
		String driver = configuration.getProperty("hibernate.dialect");
		// driver = "org.hibernate.dialect.MySQLDialect";
		String url = configuration.getProperty("hibernate.connection.url");
		// url = "jdbc:mysql://127.0.0.1:3306/gerauto?autoReconnect=true";
		String username = configuration.getProperty("hibernate.connection.username");
		// username = "gerauto";
		String password = configuration.getProperty("hibernate.connection.password");
		// password = "useradm";
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
