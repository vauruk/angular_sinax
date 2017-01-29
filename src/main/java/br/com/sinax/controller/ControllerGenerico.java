package br.com.sinax.controller;

import br.com.sinax.dao.DAOFactory;
import br.com.sinax.dao.DAOFactoryHibernate;

/**
 * 
 * @author vanderson
 *
 */

public class ControllerGenerico {
	
	private static DAOFactory daoFactory = null ;

	protected static DAOFactory getDaoFactory() {
		if(daoFactory == null){
			daoFactory = new DAOFactoryHibernate();
		}
		return daoFactory;
	}

}
