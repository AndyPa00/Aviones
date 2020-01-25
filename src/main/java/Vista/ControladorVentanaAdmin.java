package Vista;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Competicion;
import Modelo.Credencial;
import Modelo.Prueba;
import Modelo.Puntuacion;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ControladorVentanaAdmin {	//Clase en prueba

	private Session session;
	private InicioApp app;
	@FXML private Text usuario;
	@FXML private Text numLicencia;
	@FXML private TextField diaC;
	@FXML private TextField mesC;
	@FXML private TextField annoC;
	@FXML private TextField diaP;
	@FXML private TextField mesP;
	@FXML private TextField annoP;
	@FXML private TextField pruebaID;

	private static String obtenerUsuario = "FROM Credencial";
	private static String obtenerPuntos = "FROM puntuacion";
	private static String obtenerCompe = "FROM competicion";

	/* @param Usuario */
	public void setInicioApp(InicioApp app) {
		this.app = app;
		this.app.getClass();//Pa rellenar
	}

	public ArrayList<Credencial> recogerUsuarios() {
		empezar();
		@SuppressWarnings("unchecked")
		Query<Credencial> qIS = session.createQuery(obtenerUsuario);
		ArrayList<Credencial> pilotosU = (ArrayList<Credencial>) qIS.list();
		terminar();
		return pilotosU;
	}

	public ArrayList<Puntuacion> recogerPuntuaciones() {
		empezar();
		@SuppressWarnings("unchecked")
		Query<Puntuacion> qIS = session.createQuery(obtenerPuntos);
		ArrayList<Puntuacion> pilotosP = (ArrayList<Puntuacion>) qIS.list();
		terminar();
		return pilotosP;
	}
	
	public boolean hayCompe() {
		empezar();
		@SuppressWarnings("unchecked")
		Query<Competicion> qIS = session.createQuery(obtenerCompe);
		ArrayList<Competicion> compes = (ArrayList<Competicion>) qIS.list();
		terminar();
		if (compes.isEmpty()) {
			return false;
		}else {
			return true;
		}		
	}
	
	public void recibeParametros(String usuario, int numLicencia){
		//Aqui estableceremos los parametros en la vista de pilotos
		this.usuario.setText(usuario);
		this.numLicencia.setText(String.valueOf(numLicencia));
	}
	
	@FXML
	private void empezarCompe() {
		//Aqui le paso si los parametros son buenos
		
	}
	
	public void crearCompeticion(int ano, int mes, int dia) { // Funciona
		if (hayCompe()) {
			empezar();
			Calendar c = Calendar.getInstance();
			c.set(ano, mes-1, dia);
			ArrayList<Prueba> pruebas = new ArrayList<Prueba>();
			Competicion com = new Competicion(c, pruebas);
			session.save(com);
			terminar();
		}else {
			//Poner boton disable y alert
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ya existe una competición");
			alert.setHeaderText("Cuidaoo!!");
			alert.setContentText("Para iniciar otra competición será mejor que termine esta.");

			alert.showAndWait();
			System.exit(0);
		}		
	}
    
	@FXML
	private void volver() {
		Credencial c = new Credencial();
		c.setUsuario(String.valueOf(usuario));
		c.setContrasena(String.valueOf(usuario));
		app.iniciarSesion(c);
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