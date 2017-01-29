package br.com.sinax.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;

import br.com.sinax.app.EntityApp;
import br.com.sinax.app.ExceptionApp;

/**
 * 
 * @author vanderson
 *
 */
public interface DAO {
	
	public EntityApp load(Class<? extends EntityApp> obj, Serializable arg) throws ExceptionApp;
	
	public void gravar(EntityApp obj) throws ExceptionApp;
	
	public void excluir(EntityApp obj) throws ExceptionApp ;
	
	public void alterar(EntityApp obj) throws ExceptionApp ;
	
	public void merge(EntityApp obj) throws ExceptionApp;
	
	public EntityApp load(Criteria criteria) throws ExceptionApp;

	public List<?> listar(CriteriaQuery<? extends EntityApp> criteria) throws ExceptionApp;
	
}
