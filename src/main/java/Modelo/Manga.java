package Modelo;

import java.util.List;

public class Manga {
	private int idManga;
	List<Grupo> grupos;
	private int idCompeticion;
	
	public Manga() {
	}

	public Manga(List<Grupo> grupos, int idCompeticion) {
		this.grupos = grupos;
		this.idCompeticion = idCompeticion;
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

	public int getIdCompeticion() {
		return idCompeticion;
	}

	public void setIdCompeticion(int idCompeticion) {
		this.idCompeticion = idCompeticion;
	}
	
}
