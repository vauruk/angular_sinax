package br.com.sinax.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import br.com.sinax.app.ExceptionApp;


/**
 * 
 * @author vanderson
 *
 */
public class PersistenceManager {
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry serviceRegistry;

	public PersistenceManager() {
		initializeSession();
	
	}

	public static void initializeSession( ) {	
		serviceRegistry = new StandardServiceRegistryBuilder()
			
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources( serviceRegistry ).buildMetadata().buildSessionFactory();
			
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy( serviceRegistry );
		}		
	}

	public static void destroySession( ) {
		StandardServiceRegistryBuilder.destroy( serviceRegistry );
	}

	public static SessionFactory getSessionFactory( ) {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		PersistenceManager.sessionFactory = sessionFactory;
	}

	public static Session getSession( ) throws ExceptionApp {
		Session session = sessionFactory.openSession();
		return session;
	}
	
}
