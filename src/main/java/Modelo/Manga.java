package Modelo;

import java.util.List;

public class Manga {
	private int idManga;
	List<Grupo> grupos;
	
	public Manga(int idManga, List<Grupo> grupos) {
		this.idManga = idManga;
		this.grupos = grupos;
	}

	public int getIdManga() {
		return idManga;
	}

	public void setIdManga(int idManga) {
		this.idManga = idManga;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
}
