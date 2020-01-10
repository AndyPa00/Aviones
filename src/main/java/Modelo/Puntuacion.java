package Modelo;

public class Puntuacion {
	private int idPuntuacion;
	private int tiempoVuelo;
	private int distancia;
	private int altura;
	private int total;
	
	public Puntuacion(int idPuntuacion,int tiempoVuelo, int distancia, int altura, int total) {
		this.idPuntuacion = idPuntuacion;
		this.tiempoVuelo = tiempoVuelo;
		this.distancia = distancia;
		this.altura = altura;
		this.total = total;
	}

	public int getTiempoVuelo() {
		return tiempoVuelo;
	}

	public void setTiempoVuelo(int tiempoVuelo) {
		this.tiempoVuelo = tiempoVuelo;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getIdPuntuacion() {
		return idPuntuacion;
	}

	public void setIdPuntuacion(int idPuntuacion) {
		this.idPuntuacion = idPuntuacion;
	}

}
