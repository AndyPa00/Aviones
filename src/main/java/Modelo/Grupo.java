package Modelo;

import java.util.List;

public class Grupo {
	private int idGrupo;
	private List<Piloto> pilotos;
	private int idManga;
	
	public Grupo(int idGrupo, List<Piloto> pilotos, int idManga) {
		this.idGrupo = idGrupo;
		this.pilotos = pilotos;
		this.idManga = idManga;
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
	public int getIdManga() {
		return idManga;
	}
	public void setIdManga(int idManga) {
		this.idManga = idManga;
	}
}
