package Modelo;

import java.util.List;

public class Grupo {
	private int idGrupo;
	private List<Piloto> pilotos;
	private Manga manga;
	
	public Grupo(List<Piloto> pilotos, Manga manga) {
		this.pilotos = pilotos;
		this.manga = manga;
	}
	public int getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	public List<Piloto> getPilotos() {
		return pilotos;
	}
	public void setPilotos(List<Piloto> pilotos) {
		this.pilotos = pilotos;
	}
	public Manga getManga() {
		return manga;
	}
	public void setManga(Manga manga) {
		this.manga = manga;
	}
}
