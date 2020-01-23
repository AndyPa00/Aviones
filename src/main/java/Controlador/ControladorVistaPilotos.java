package Controlador;

import Vista.InicioApp;
import javafx.fxml.FXML;

public class ControladorVistaPilotos {
	
	private InicioApp app;

	/* @param Usuario */
	public void setInicioApp(InicioApp app) {
		this.app = app;
	}

	@FXML
	private void abrirPerfil() {
		app.mostrarPerfil();
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
