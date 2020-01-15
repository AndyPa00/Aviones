package Controlador;

import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControladorIniciarSesion {
	
	private InicioApp app;
	@FXML
	private TextField usuario;
	@FXML
	private TextField contrasena;
	
	/*	@param Usuario	*/
	public void setInicioApp(InicioApp app) {
		this.app = app;
	}

	@FXML
    private void iniciarSesion() {
		app.iniciarSesion();
    }
	
}
