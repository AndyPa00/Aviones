package Controlador;

import org.hibernate.Session;

import Modelo.Credencial;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControladorRegistrarse {
	
	private Session session;
	private InicioApp app;
	@FXML
	private TextField nombre;
	@FXML
	private TextField apellido1;
	@FXML
	private TextField apellido2;
	@FXML
	private TextField usuario;
	@FXML
	private TextField contrasena;
	
	/* @param Usuario */
	public void setInicioApp(InicioApp app) {
		this.app = app;
	}
	
	@FXML
	private void registrarse() {
		empezar();
		Credencial cred = new Credencial(usuario.getText(), contrasena.getText(),
				123, nombre.getText(), apellido1.getText(), apellido2.getText());
		session.save(cred);
		System.out.println(cred.toString());
		terminar();
		System.out.println("Usuario creado");
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
