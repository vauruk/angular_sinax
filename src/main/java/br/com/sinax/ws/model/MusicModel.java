package br.com.sinax.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author vanderson
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class MusicModel {

	public MusicModel() {
		super();
	}

	public MusicModel(Integer id, String name, String style) {
		this.id = id;
		this.name = name;
		this.style = style;
	}

	@XmlID
	@XmlAttribute
	private Integer id;

	@XmlElement
	private String name;

	@XmlElement
	private String style;

	@XmlElement
	private ArtistModel artist;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public ArtistModel getArtist() {
		return artist;
	}

	public void setArtist(ArtistModel artist) {
		this.artist = artist;
	}

	
}
