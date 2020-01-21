package Controlador;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Credencial;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ControladorVentanaPiloto {

	private Session session;
	private InicioApp app;
	@FXML
	private Text usuario;
	@FXML
	private Text numLicencia;
	private static String obtenerUsuario = "FROM Credencial WHERE usuario = :usuario";
	ObservableList<Credencial> pilotos;

	/* @param Usuario */
	public void setInicioApp(InicioApp app) {
		this.app = app;
	}

	@FXML
	private void recogerUsuarios() {
		
		empezar();
		@SuppressWarnings("unchecked")
		Query<Credencial> qIS = session.createQuery(obtenerUsuario);
		qIS.setParameter("usuario", usuario.getText());
		ArrayList<Credencial> credencial = (ArrayList<Credencial>) qIS.list();
		terminar();
		
		if (credencial.isEmpty()) {
			System.out.println("Usuario no encontrado");
		} else {
//			if(credencial.get(0).getContrasena().equals(contrasena.getText())) {
//				System.out.println("Usuario y contra correctas");
//				app.iniciarSesion();			
//			} else {
//				System.out.println("Contrasena incorrecta");
//			}
		}
		
	}
	
	public void recibeParametros(String usuario, int numLicencia){
		//Aqui estableceremos los parametros en la vista de pilotos
		this.usuario.setText(usuario);
		this.numLicencia.setText(String.valueOf(numLicencia));
	}
	
	@FXML
	private void mostrarClasificacion() {	//Esto hay que pulirlo
		
	}
	
	@FXML
	private void volverAtras() {
		app.iniciarSesion(String.valueOf(usuario), Integer.parseInt(String.valueOf(numLicencia)));
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