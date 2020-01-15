package Modelo;

public class Credencial {
	private String usuario;
	private String contrasena;
	private int numLicencia;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	public Credencial() {
	}

	public Credencial(String usuario, String contrasena, int numLicencia, String nombre, String apellido1, String apellido2) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.numLicencia = numLicencia;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	
}
