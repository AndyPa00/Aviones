package Controlador;

import Vista.InicioApp;
import javafx.fxml.FXML;

public class ControladorInicio {
	private InicioApp app;

/*	@param Usuario	*/
	public void setInicioApp(InicioApp app) {
		this.app = app;
	}
	
	@FXML
    private void registrarse() {
		app.registrarse();
    }
	
	@FXML
    private void iniciarSesion() {
        app.iniciarIniciarSesion();
    }
	
	@FXML
    private void olvideUsuario() {
        app.recuperarUsuario();
    }
	
	@FXML
    private void olvideContrasena() {
        app.recuperarContrasena();
    }	
	
	@FXML
    private void handleExit() {
        System.exit(0);
    }

}