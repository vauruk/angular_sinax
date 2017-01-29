package br.com.sinax.app.model;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.sinax.app.EntityApp;
/**
 * 
 * @author vanderson
 *
 */
@Entity
@Table(name = "artist")
public class ArtistBO extends EntityApp {

	public ArtistBO(){}
	
	public ArtistBO(int id, String name, String description, Set<MusicBO> listMusic) {
	
		this.id = id;
		this.name = name;
		this.description = description;
		this.listMusic = listMusic;
	}
	public ArtistBO(int id) {
	
		this.id = id;
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 8396842429365548947L;
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private int id;
	@Column(length = 45)

	private String name;
	@Column(length = 45)

	private String description;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "artist")
	private Set<MusicBO> listMusic = new HashSet<MusicBO>(0);
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<MusicBO> getListMusic() {
		return listMusic;
	}
	public void setListMusic(Set<MusicBO> listMusic) {
		this.listMusic = listMusic;
	}
	
}
