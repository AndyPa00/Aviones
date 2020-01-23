package Controlador;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Credencial;
import Modelo.Puntuacion;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ControladorVentanaPiloto {

	private Session session;
	private InicioApp app;
	@FXML
	private Text usuario;
	@FXML
	private Text numLicencia;
	
	
	private static String obtenerUsuario = "FROM Credencial";
	private static String obtenerPuntos = "FROM puntuacion";

	/* @param Usuario */
	public void setInicioApp(InicioApp app) {
		this.app = app;
	}

	public ArrayList<Credencial> recogerUsuarios() {
		empezar();
		@SuppressWarnings("unchecked")
		Query<Credencial> qIS = session.createQuery(obtenerUsuario);
		ArrayList<Credencial> pilotos = (ArrayList<Credencial>) qIS.list();
		terminar();
		return pilotos;
	}

	public ArrayList<Puntuacion> recogerPuntuaciones() {
		empezar();
		@SuppressWarnings("unchecked")
		Query<Puntuacion> qIS = session.createQuery(obtenerPuntos);
		ArrayList<Puntuacion> pilotos = (ArrayList<Puntuacion>) qIS.list();
		terminar();
		return pilotos;
	}
	
	public void recibeParametros(String usuario, int numLicencia){
		//Aqui estableceremos los parametros en la vista de pilotos
		this.usuario.setText(usuario);
		this.numLicencia.setText(String.valueOf(numLicencia));
	}
	
	@FXML
	private void mostrarClasificacion() {	//Esto hay que pulirlo
		
	//	PilotosConPuntos pilotos = new PilotosConPuntos(usuario, numLicencia, nombre, puntos);
	}
	
//	@FXML
//	private void volverAtras() {
//		app.iniciarSesion(String.valueOf(usuario), Integer.parseInt(String.valueOf(numLicencia)));
//	}

	public void empezar() {
		session = Utilidades2.getSessionFactory().openSession();
		session.beginTransaction();
	}

	private void terminar() {
		session.getTransaction().commit();
		session.close();
	}

}