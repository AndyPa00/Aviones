package Controlador;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Credencial;
import Modelo.Prueba;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControladorIniciarSesion {

	private Session session;
	private InicioApp app;
	@FXML
	private TextField usuario;
	@FXML
	private TextField contrasena;
	private static String obtenerUsuario = "FROM Credencial WHERE usuario = :usuario";

	/* @param Usuario */
	public void setInicioApp(InicioApp app) {
		this.app = app;
	}

	@FXML
	private void iniciarSesion() {
		
		empezar();
		@SuppressWarnings("unchecked")
		Query<Credencial> q = session.createQuery(obtenerUsuario);
		q.setParameter("usuario", usuario.getText());
		ArrayList<Credencial> credencial = (ArrayList<Credencial>) q.list();
		terminar();
		
		if (credencial.isEmpty()) {
			System.out.println("Usuario no encontrado");
		} else {
			if(credencial.get(0).getContrasena().equals(contrasena.getText())) {
				System.out.println("Usuario y contra correctas");
				app.iniciarSesion();			
			} else {
				System.out.println("Contrasena incorrecta");
			}
		}
		
	}
	
	@FXML
	private void volverAtras() {
		app.iniciarInicio();
	}

	public void empezar() {
		session = Utilidades2.getSessionFactory().openSession();
		session.beginTransaction();
	}

	private void terminar() {
		session.getTransaction().commit();
		session.close();
	}

}
