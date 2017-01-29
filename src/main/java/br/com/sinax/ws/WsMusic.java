package br.com.sinax.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.sinax.app.ExceptionApp;
import br.com.sinax.app.model.ArtistBO;
import br.com.sinax.app.model.MusicBO;
import br.com.sinax.controller.MusicController;
import br.com.sinax.ws.model.ArtistModel;
import br.com.sinax.ws.model.MusicModel;

/**
 * 
 * @author vanderson
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "ws_music")
@Path("/ws_music")
@Produces(MediaType.APPLICATION_JSON)
public class WsMusic {
	private MusicController musicController = new MusicController();

	@GET
	@Path("/list_music")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MusicModel> getListaMusic() {
		List<MusicBO> listMusic = null;
		try {
			listMusic = musicController.listMusic(null);
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}

		return converteMusic(listMusic);
		// return listMusic;
	}

	@GET
	@Path("/list_music/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MusicModel> getListaMusic(@PathParam("name") String name) {
		List<MusicBO> listMusic = null;
		try {
			listMusic = musicController.listMusic(name);
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}

		return converteMusic(listMusic);
		// return listMusic;
	}

	@GET
	@Path("/list_artist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ArtistModel> getListaArtist() {
		List<ArtistBO> listArtist = null;
		try {
			listArtist = musicController.listArtist();
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}

		return converteArtist(listArtist);
		// return listMusic;
	}

	private List<ArtistModel> converteArtist(List<ArtistBO> listArtist) {
		List<ArtistModel> list = new ArrayList<ArtistModel>();
		for (ArtistBO artistBO : listArtist) {
			list.add(new ArtistModel(artistBO.getId(), artistBO.getName(), artistBO.getDescription()));
		}
		return list;
	}

	private List<MusicModel> converteMusic(List<MusicBO> listMusic) {
		List<MusicModel> list = new ArrayList<MusicModel>();
		for (MusicBO musicBO : listMusic) {
			MusicModel musicModel = new MusicModel(musicBO.getId(), musicBO.getName(), musicBO.getStyle());
			musicModel.setArtist(new ArtistModel(musicBO.getArtist().getId(), musicBO.getArtist().getName(),
					musicBO.getArtist().getDescription()));
			list.add(musicModel);
		}
		return list;
	}

	@PUT
	@Path("/salvar_music/")
	@Produces(MediaType.APPLICATION_JSON)
	public void salvarMusic(MusicModel music) {
		MusicBO musicBO = new MusicBO();
		if (music.getId() != null) {
			musicBO.setId(music.getId());
		}
		musicBO.setName(music.getName());
		musicBO.setStyle(music.getStyle());
		musicBO.setArtist(new ArtistBO(music.getArtist().getId()));
		try {
			musicController.saveMusic(musicBO);
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}

	}

	@PUT
	@Path("/salvar_artist/")
	@Produces(MediaType.APPLICATION_JSON)
	public void salvarMusic(ArtistModel artist) {
		ArtistBO artistBO = new ArtistBO();
		artistBO.setId(artist.getId());
		artistBO.setName(artist.getName());
		artistBO.setDescription(artist.getDescription());
		try {
			musicController.saveArtist(artistBO);
		} catch (ExceptionApp e) {
			e.printStackTrace();
		}

	}

	@DELETE
	@Path("/deletar_music/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deletarMusic(@PathParam("id") Integer id) {
		try {
			musicController.deleteMusic(id);
		} catch (ExceptionApp e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}