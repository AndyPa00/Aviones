package Controlador;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Credencial;
import Modelo.PilotosConPuntos;
import Modelo.Puntuacion;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControladorVentanaPiloto {	//Clase en prueba

	private Session session;
	private InicioApp app;
	@FXML private Text usuario;
	@FXML private Text numLicencia;

    // Declaramos la tabla y las columnas
	@FXML private TableView<PilotosConPuntos> tablaPilotos;
    @FXML private TableColumn<PilotosConPuntos, String> usuarioTab;
    @FXML private TableColumn<PilotosConPuntos, String> nombreTab;
    @FXML private TableColumn<PilotosConPuntos, Number> numLicenciaTab;
    @FXML private TableColumn<PilotosConPuntos, Number> puntosTab;
    @FXML private TextField buscar = new TextField();
    
	ObservableList<PilotosConPuntos> pilotos;
	FilteredList<PilotosConPuntos> datosFiltrados;
	
	private static String obtenerUsuario = "FROM Credencial";
	private static String obtenerPuntos = "FROM puntuacion";

	@FXML
	private void initialize() {
		tablaPilotos = new TableView<PilotosConPuntos>();
		
		tablaPilotos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		// Inicializamos la tabla con los valores predefinidos.
		usuarioTab.setCellValueFactory(cellData -> cellData.getValue().propiedadUsuario());
		nombreTab.setCellValueFactory(cellData -> cellData.getValue().propiedadNombre());
		numLicenciaTab.setCellValueFactory(cellData -> cellData.getValue().propiedadnumLicencia());
		puntosTab.setCellValueFactory(cellData -> cellData.getValue().propiedadPuntos());
		
		tablaPilotos.getColumns().add(usuarioTab);
		tablaPilotos.getColumns().add(nombreTab);
		tablaPilotos.getColumns().add(numLicenciaTab);
		tablaPilotos.getColumns().add(puntosTab);
		
		datosFiltrados = new FilteredList<>(listaPilotos(), p -> true);
		tablaPilotos.setItems(datosFiltrados);
		
		buscar.textProperty().addListener((prop, old, text) -> {
			datosFiltrados.setPredicate(pilotos -> {
				if (text == null || text.isEmpty()){
					return true;
				}
				String nombre = pilotos.getNombre().toLowerCase();
				return nombre.contains(text.toLowerCase());
			});
		});
		
		//Por si el field necesita mas espacio
		HBox.setHgrow(buscar, Priority.ALWAYS);
		VBox.setVgrow(tablaPilotos, Priority.ALWAYS);
		
		HBox searchBar = new HBox();
        searchBar.getChildren().add(buscar);
		
	}	
	
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
	
	public void recibeParametros(String usuario, int numLicencia){
		//Aqui estableceremos los parametros en la vista de pilotos
		this.usuario.setText(usuario);
		this.numLicencia.setText(String.valueOf(numLicencia));
	}	
    
//	@FXML
//	private void volverAtras() {
//		app.iniciarSesion(String.valueOf(usuario), Integer.parseInt(String.valueOf(numLicencia)));
//	}
	
	private ObservableList<PilotosConPuntos> listaPilotos() {
		List<PilotosConPuntos> pailots = new ArrayList<>();
		//////AQUI COGEMOS LOS PILOTOS Y LOS ANNADIMOS//////
		
		ArrayList<Credencial> credenciales = recogerUsuarios();
		ArrayList<Puntuacion> puntuaciones = recogerPuntuaciones();
		ArrayList<PilotosConPuntos> pilotosCompe = new ArrayList<PilotosConPuntos>();
		for (int i = 0; i < credenciales.size(); i++) {
			PilotosConPuntos pilotes = new PilotosConPuntos();
			pilotes.setUsuario(credenciales.get(i).getUsuario());
			pilotes.setNombre(credenciales.get(i).getNombre());
			pilotes.setNumLicencia(credenciales.get(i).getNumLicencia());
			pilotes.setPuntos(puntuaciones.get(i).getTotal());
			
			pilotosCompe.add(pilotes);
		}
		for (int i = 0; i < pilotosCompe.size(); i++) {
			System.out.println(pilotosCompe.get(i).toString());
		}
		for (int i = 0; i < pilotosCompe.size(); i++) {
			
		}
		
        pailots.add(new PilotosConPuntos("Andy00", "Andres", 123, 500));
        pailots.add(new PilotosConPuntos("Holits", "HOlo", 174, 500));
        pailots.add(new PilotosConPuntos("Guapits", "Guapo", 253, 500));
        pailots.add(new PilotosConPuntos("Señorito", "Señor", 5465, 500));
        
        return FXCollections.observableArrayList(pilotosCompe);
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