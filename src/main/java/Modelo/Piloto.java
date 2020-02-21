package Modelo;

public class Piloto {
	private int idPiloto;
	private Puntuacion puntuacion;
	private Grupo grupo;
	
	public Piloto(Puntuacion puntuacion, Grupo grupo) {
		this.puntuacion = puntuacion;
		this.grupo = grupo;
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

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
}
