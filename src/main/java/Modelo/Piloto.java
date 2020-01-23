package Modelo;

public class Piloto {
	private int idPiloto;
	private Puntuacion puntuacion;
	private int idGrupo;
	
	public Piloto(int idPiloto, Puntuacion puntuacion, int idGrupo) {
		this.idPiloto = idPiloto;
		this.puntuacion = puntuacion;
		this.idGrupo = idGrupo;
	}
	
	public Piloto(Puntuacion puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getIdPiloto() {
		return idPiloto;
	}

	public void setIdPiloto(int idPiloto) {
		this.idPiloto = idPiloto;
	}

	public Puntuacion getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Puntuacion puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	
}
