package Controlador;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Session;

import Modelo.Competicion;
import Modelo.Credencial;
import Modelo.Prueba;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControladorVistaPilotosAdmin {

	private InicioApp app;
	@FXML private Button perfil;
	@FXML private Button clasificacion;
	@FXML private Button pruebas;
	@FXML private Button cerrarSesion;
	@FXML private Label punto;
	
	private Credencial usuario;
	
	private Session session;
	private static String existeCompeticion = "FROM Competicion";

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
	private void editarCompeticion() {
//		app.mostrarPruebas();
		app.mostrarEditor();
		perfil.setStyle("-fx-background-color: #42A6F9;");
		clasificacion.setStyle("-fx-background-color: #42A6F9;");
		pruebas.setStyle("-fx-background-color: #2B6FA8;");
	}
	
	@FXML
	private void cerrarSesion() {
		app.mostrarInicio();
	}
	
	public void empezar() {
		session = Utilidades2.getSessionFactory().openSession();
		session.beginTransaction();
	}

	public void terminar() {
		session.getTransaction().commit();
		session.close();
	}

}
