package Modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PilotosConPuntos {
	private StringProperty usuario;
	private StringProperty nombre;
	private IntegerProperty numLicencia;
	private IntegerProperty puntos;
	
	public PilotosConPuntos() {}
	
	public PilotosConPuntos(String usuario, String nombre, Integer numLicencia, Integer puntos) {
		this.usuario = new SimpleStringProperty(usuario);
		this.nombre = new SimpleStringProperty(nombre);
		this.numLicencia = new SimpleIntegerProperty(numLicencia);
		this.puntos = new SimpleIntegerProperty(puntos);
	}
	
	public PilotosConPuntos(StringProperty usuario, StringProperty nombre, IntegerProperty numLicencia, IntegerProperty puntos) {
		this.usuario = usuario;
		this.nombre = nombre;
		this.numLicencia = numLicencia;
		this.puntos = puntos;
	}
	
	public String getUsuario() {
		return usuario.get();
	}
	public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }
    public StringProperty propiedadUsuario() {
        return usuario;
    }
    
	public String getNombre() {
		return nombre.get();
	}
	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	} 
    public StringProperty propiedadNombre() {
        return usuario;
    }

	public int getNumLicencia() {
		return numLicencia.get();
	}
	public void setNumLicencia(int numLicencia) {
		this.numLicencia.set(numLicencia);
	}
	public IntegerProperty propiedadnumLicencia() {
		return numLicencia;
	}

	public int getPuntos() {
		return puntos.get();
	}
	public void setPuntos(int puntos) {
		this.puntos.set(puntos);
	}
	public IntegerProperty propiedadPuntos() {
		return puntos;
	}

	
}
