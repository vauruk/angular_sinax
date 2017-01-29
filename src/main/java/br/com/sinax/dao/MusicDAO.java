package br.com.sinax.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.sinax.app.ExceptionApp;
import br.com.sinax.app.model.ArtistBO;
import br.com.sinax.app.model.MusicBO;

/**
 * 
 * @author vanderson
 *
 */
public class MusicDAO extends DAOGenerico {

	public MusicDAO(Session hibernateSession) {
		super(hibernateSession);
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings ( "unchecked" )
	public List <MusicBO> listMusic(final String name) throws ExceptionApp{	
		CriteriaBuilder builder = hibernateSession.getCriteriaBuilder();

		CriteriaQuery<MusicBO> criteria = builder.createQuery( MusicBO.class );
		Root<MusicBO> root = criteria.from( MusicBO.class );
		root.fetch("artist", JoinType.INNER);
		criteria.select( root );
		
		
		if(name != null && name.length() > 0 ){
			criteria.where( builder.like( root.get( "name" ), "%"+name+"%" ) );
		}

		
		return (List <MusicBO>) listar(criteria);
		
	}
	
	@SuppressWarnings ( "unchecked" )
	public List <ArtistBO> listArtist() throws ExceptionApp{	
		CriteriaBuilder builder = hibernateSession.getCriteriaBuilder();

		CriteriaQuery<ArtistBO> criteria = builder.createQuery( ArtistBO.class );
		Root<ArtistBO> root = criteria.from( ArtistBO.class );
		criteria.select( root );
		
		return (List <ArtistBO>) listar(criteria);
		
	}
	public MusicBO salvar(MusicBO music){
		try {
			gravar(music);
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArtistBO salvar(ArtistBO artist){
		try {
			gravar(artist);
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}
		return null;
	}
	public void delete(Integer id){
		try {
			excluir(load(MusicBO.class, id));
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}
	}
}
