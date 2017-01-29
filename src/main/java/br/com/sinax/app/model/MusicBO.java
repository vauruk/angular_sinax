package br.com.sinax.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sinax.app.EntityApp;

/**
 * 
 * @author vanderson
 *
 */
@Entity
@Table(name = "music")
public class MusicBO extends EntityApp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8396562429365548947L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private int id;
	@Column(length = 45)
	private String name;
	@Column(length = 45)
	private String style;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_artist", nullable = false)
	private ArtistBO artist;
	
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

	
	public ArtistBO getArtist() {
		return artist;
	}

	public void setArtist(ArtistBO artist) {
		this.artist = artist;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	
	
}
