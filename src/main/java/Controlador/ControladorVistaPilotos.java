package Controlador;

import Modelo.Credencial;
import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorVistaPilotos {

	private InicioApp app;
	@FXML
	private Button perfil;
	@FXML
	private Button clasificacion;
	@FXML
	private Button pruebas;
	@FXML
	private Button cerrarSesion;
	private Credencial usuario;

	/* @param Usuario */
	public void setInicioApp(InicioApp app, Credencial usuario) {
		this.app = app;
		this.usuario = usuario;
		perfil.setText("Piloto " + usuario.getNumLicencia() + "\n@" + usuario.getUsuario());
		perfil.setStyle("-fx-background-color: #2B6FA8;");
	}

	@FXML
	private void abrirPerfil() {
		app.mostrarPerfil(usuario);
		perfil.setStyle("-fx-background-color: #2B6FA8;");
		clasificacion.setStyle("-fx-background-color: #42A6F9;");
		pruebas.setStyle("-fx-background-color: #42A6F9;");
	}
	
	@FXML
	private void abrirClasificacion() {
		app.mostrarClasificacion();
		perfil.setStyle("-fx-background-color: #42A6F9;");
		clasificacion.setStyle("-fx-background-color: #2B6FA8;");
		pruebas.setStyle("-fx-background-color: #42A6F9;");
	}
	
	@FXML
	private void abrirPruebas() {
//		app.mostrarPruebas();
		perfil.setStyle("-fx-background-color: #42A6F9;");
		clasificacion.setStyle("-fx-background-color: #42A6F9;");
		pruebas.setStyle("-fx-background-color: #2B6FA8;");
	}
	
	@FXML
	private void cerrarSesion() {
		app.mostrarInicio();
	}

//	@FXML
//	private void abrirClasificacion() {
//		app.mostrarClasificacion();
//	}
//	
//	@FXML
//	private void abrirPruebas() {
//		app.mostrarPruebas();
//	}

}
