package Controlador;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Credencial;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ControladorRecuperarUsser {

	private Session session;
	private InicioApp app;
	@FXML private TextField nombre;
	@FXML private TextField numLicencia;
	@FXML private TextField contrasena;
	@FXML private Text resultado;
	private static String obtenerUsuario = "FROM Credencial WHERE contrasena = :contrasena AND nombre = :nombre AND numLicencia = :numLicencia";

	/* @param Usuario */
	public void setInicioApp(InicioApp app) {
		this.app = app;
	}

	@FXML
	private void recuperarUsserr() {
		if (contrasena.getText().isEmpty() || nombre.getText().isEmpty() || numLicencia.getText().isEmpty()) {
			resultado.setText("Credenciales Incorrectas");
		} else {
			try {
				Integer.parseInt(numLicencia.getText());
				empezar();
				@SuppressWarnings("unchecked")
				Query<Credencial> q = session.createQuery(obtenerUsuario);
				q.setParameter("contrasena", contrasena.getText());
				q.setParameter("nombre", nombre.getText());
				q.setParameter("numLicencia", Integer.parseInt(numLicencia.getText()));
				ArrayList<Credencial> credencial = (ArrayList<Credencial>) q.list();
				terminar();

				if (credencial.isEmpty()) {
					resultado.setText("Credenciales Incorrectas");
				} else {
					if (credencial.get(0).getContrasena().equals(contrasena.getText())
							&& credencial.get(0).getNumLicencia() == Integer.parseInt(numLicencia.getText())) {
						resultado.setText("Usuario: " + credencial.get(0).getUsuario());
					} else {
						resultado.setText("Credenciales Incorrectas");
					}
				}
			} catch (NumberFormatException e) {
				resultado.setText("numLicencia Incorrecto");
			} catch (Exception e) {
				resultado.setText("Credenciales Incorrectas");
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
	
	@FXML
	private void volverAtras() {
		app.mostrarIniciarSesion();
	}
	
	@FXML
	private void recuperaContra() {
		app.mostrarRecuperarContrasena();
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
