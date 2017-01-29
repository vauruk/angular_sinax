package br.com.sinax.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.sinax.dao.PersistenceManager;

/**
 * 
 * @author vanderson
 *
 */
public class ContextLoaderListener implements ServletContextListener {

	
	public static ServletContext webContext;

	public void contextDestroyed(ServletContextEvent arg0) {	
		System.out.println("destroyed");
		PersistenceManager.destroySession();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("initialized");
		PersistenceManager.initializeSession(); 
	}
	
}
