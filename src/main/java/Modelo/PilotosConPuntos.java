package Modelo;

public class PilotosConPuntos {
	private String usuario;
	private int numLicencia;
	private String nombre;
	private int puntos;
	
	public PilotosConPuntos(String usuario, int numLicencia, String nombre, int puntos) {
		this.usuario = usuario;
		this.numLicencia = numLicencia;
		this.nombre = nombre;
		this.puntos = puntos;
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getNumLicencia() {
		return numLicencia;
	}
	public void setNumLicencia(int numLicencia) {
		this.numLicencia = numLicencia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
}
