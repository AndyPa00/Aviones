package Modelo;

public class Piloto {
	private int idPiloto;
	private Puntuacion puntuacion;
	
	public Piloto(int idPiloto, Puntuacion puntuacion) {
		this.idPiloto = idPiloto;
		this.puntuacion = puntuacion;
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
	
}
