package Controlador;

import java.awt.TextField;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Credencial;
import Modelo.PilotosConPuntos;
import Modelo.Puntuacion;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class ControladorVentanaPiloto {

	private Session session;
	private InicioApp app;
	@FXML
	private Text usuario;
	@FXML
	private Text numLicencia; 

    // Declaramos la tabla y las columnas
    @FXML private TableView<PilotosConPuntos> tablaPilotos;
    @FXML private TableColumn usuarioTab;
    @FXML private TableColumn nombreTab;
    @FXML private TableColumn numLicenciaTab;
    @FXML private TableColumn puntosTab;
	ObservableList<PilotosConPuntos> pilotos;

    private int posicionPilotoEnTabla;
	
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
	private void mostrarClasificacion() {	//Esto hay que probarlo
		ArrayList<Credencial> credenciales = recogerUsuarios();
		ArrayList<Puntuacion> puntuaciones = recogerPuntuaciones();
		ArrayList<PilotosConPuntos> pilotosCompe = new ArrayList<PilotosConPuntos>();
		for (int i = 0; i < credenciales.size(); i++) {
			PilotosConPuntos pilotes = new PilotosConPuntos(credenciales.get(0).getUsuario(), credenciales.get(i).getNumLicencia(), credenciales.get(i).getNombre(), puntuaciones.get(i).getTotal());
			pilotosCompe.add(pilotes);
		}
		for (int i = 0; i < pilotosCompe.size(); i++) {
			System.out.println(pilotosCompe.get(i).toString());
		}
		for (int i = 0; i < pilotosCompe.size(); i++) {
			
		}
		//Mostrar en la clasificacion
		 
		
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