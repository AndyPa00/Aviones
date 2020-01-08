package Modelo;

import java.util.List;

public class Grupo {
	private int idGrupo;
	List<Piloto> pilotos;
	
	public Grupo(int idGrupo, List<Piloto> pilotos) {
		this.idGrupo = idGrupo;
		this.pilotos = pilotos;
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
}
