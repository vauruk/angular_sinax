package br.com.sinax.dao;

import br.com.sinax.app.ExceptionApp;

/**
 * 
 * @author vanderson
 *
 */
public class DAOFactoryHibernate implements DAOFactory {

	@Override
	public DAO criarMusicDAO() {
		try {
			
			return new MusicDAO(PersistenceManager.getSession());
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}
		return null;
	}

	/*@Override
	public DAO criarUsuarioDAO() {
		try {
			return new UsuarioDAO(PersistenceManager.getSession());
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DAO criarCargoDAO() {
		try {
			return new CargoDAO(PersistenceManager.getSession());
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DAO criarVeiculoDAO( ) {
		try {
			return new VeiculoDAO(PersistenceManager.getSession());
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DAO criarModeloDAO( ) {
		try {
			return new ModeloDAO(PersistenceManager.getSession());
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DAO criarItemVistoriaDAO( ) {
		try {
			return new ItemVistoriaDAO(PersistenceManager.getSession());
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DAO criarVistoriaDAO( ) {
		try {
			return new VistoriaDAO(PersistenceManager.getSession());
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}
		return null;
	}
*/
}
