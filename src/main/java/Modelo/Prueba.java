package Modelo;

import java.util.Calendar;

public class Prueba {
	private int idPrueba;
	private Calendar fechaPrueba;
	
	public Prueba(Calendar fechaPrueba) {
		this.fechaPrueba = fechaPrueba;
	}

	public int getIdPrueba() {
		return idPrueba;
	}

	public void setIdPrueba(int idPrueba) {
		this.idPrueba = idPrueba;
	}

	public Calendar getFechaPrueba() {
		return fechaPrueba;
	}

	public void setFechaPrueba(Calendar fechaPrueba) {
		this.fechaPrueba = fechaPrueba;
	}

}
