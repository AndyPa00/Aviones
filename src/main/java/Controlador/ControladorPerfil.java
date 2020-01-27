package Controlador;

import Modelo.Credencial;
import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControladorPerfil {
	
	private InicioApp app;
	@FXML
	private Label usuario;
	@FXML
	private Label numLicencia;
	@FXML
	private Label nombre;
	@FXML
	private Label apellido1;
	@FXML
	private Label apellido2;
	
	/* @param Usuario */
	public void setInicioApp(InicioApp app, Credencial usuario) {
		this.setApp(app);
		this.usuario.setText(usuario.getUsuario());
		this.numLicencia.setText(String.valueOf(usuario.getNumLicencia()));
		this.nombre.setText(usuario.getNombre());
		this.apellido1.setText(usuario.getApellido1());
		this.apellido2.setText(usuario.getApellido2());
	}

	public InicioApp getApp() {
		return app;
	}
	public void setApp(InicioApp app) {
		this.app = app;
	}

}
