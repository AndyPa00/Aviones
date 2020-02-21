package Modelo;

import java.util.List;

public class Manga {
	private int idManga;
	private List<Grupo> grupos;
	private Prueba prueba;
	
	public Manga() {
	}

	public Manga(List<Grupo> grupos, Prueba prueba) {
		this.grupos = grupos;
		this.prueba = prueba;
	}

	public Prueba getPrueba() {
		return prueba;
	}

	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
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
