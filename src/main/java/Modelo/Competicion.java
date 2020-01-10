package Modelo;

import java.util.Calendar;
import java.util.List;

public class Competicion {
	private int idCompeticion;
	private Calendar fechaInscripcion;
	private List<Prueba> pruebas;
	
	public Competicion(Calendar fechaInscripcion, List<Prueba> pruebas) {
		this.fechaInscripcion = fechaInscripcion;
		this.pruebas = pruebas;
	}

	public int getIdCompeticion() {
		return idCompeticion;
	}
	public void setIdCompeticion(int idCompeticion) {
		this.idCompeticion = idCompeticion;
	}
	public Calendar getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Calendar fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public List<Prueba> getPruebas() {
		return pruebas;
	}
	public void setPruebas(List<Prueba> pruebas) {
		this.pruebas = pruebas;
	}
	
}
