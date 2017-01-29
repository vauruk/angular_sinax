package br.com.sinax.controller;

import java.util.List;

import br.com.sinax.app.ExceptionApp;
import br.com.sinax.app.model.ArtistBO;
import br.com.sinax.app.model.MusicBO;
import br.com.sinax.dao.MusicDAO;
/**
 * 
 * @author vanderson
 *
 */
public class MusicController extends ControllerGenerico {
	private MusicDAO musicDAO = null;

	public MusicController() {
		musicDAO = (MusicDAO) super.getDaoFactory().criarMusicDAO();
	}

	public List<MusicBO> listMusic(final String name) throws ExceptionApp {
		return musicDAO.listMusic(name);
	}

	public List<ArtistBO> listArtist() throws ExceptionApp {
		return musicDAO.listArtist();
	}
	
	public void deleteMusic(final Integer id) throws ExceptionApp {
		musicDAO.delete(id);
	}
	public void saveMusic(final MusicBO musicBO) throws ExceptionApp {
		musicDAO.salvar(musicBO);
	}
	
	public void saveArtist(final ArtistBO artistBO) throws ExceptionApp {
		musicDAO.salvar(artistBO);
	}
}
