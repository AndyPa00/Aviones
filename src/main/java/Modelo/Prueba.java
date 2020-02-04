package Modelo;

import java.util.Calendar;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prueba {
	private int idPrueba;
	private Calendar fechaPrueba;
	private List<Manga> mangas;
	private Competicion competicion;
		
	public Prueba() {
	}

	public Prueba(Calendar fechaPrueba, List<Manga> mangas, Competicion competicion) {
		this.fechaPrueba = fechaPrueba;
		this.mangas = mangas;
		this.competicion = competicion;
	}

	public List<Manga> getMangas() {
		return mangas;
	}

	public void setMangas(List<Manga> mangas) {
		this.mangas = mangas;
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
	
	public Competicion getCompeticion() {
		return competicion;
	}

	public void setCompeticion(Competicion competicion) {
		this.competicion = competicion;
	}

	public StringProperty idProperty() {
        return new SimpleStringProperty(String.valueOf(idPrueba));
    }

	public StringProperty fechaProperty() {
		return new SimpleStringProperty(fechaPrueba.getTime().toString());
	}

}
