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
public class ArtistModel {

	public ArtistModel() {}
	
	public ArtistModel(Integer id, String name, String description) {
		
		this.id = id;
		this.name = name;
		this.description = description;
	}

	@XmlID
	@XmlAttribute
	private Integer id;

	@XmlElement
	private String name;

	@XmlElement
	private String description;
	

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
