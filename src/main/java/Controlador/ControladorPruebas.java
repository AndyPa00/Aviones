package Controlador;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import Modelo.Prueba;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorPruebas {

	private InicioApp app;
	private ObservableList<Prueba> listaPruebas = FXCollections.observableArrayList();
	private Session session;
	private BorderPane raiz;
	private Stage escenario;
	private static String obtenerPruebas = "FROM Prueba";
	@FXML private TableView<Prueba> vistaPruebas;
	@FXML private TableColumn<Prueba, String> pruebaCol;
	@FXML private TableColumn<Prueba, String> fechaCol;
	
	public ControladorPruebas() {
	}

	/* @param Usuario */
	public void setInicioApp(InicioApp app) {
		this.setApp(app);
		empezar();
		List<Prueba> datosPruebas = session.createQuery(obtenerPruebas, Prueba.class).getResultList();
		terminar();
		for (int i = 0; i < datosPruebas.size(); i++) {
			listaPruebas.add(datosPruebas.get(i));
		}
        pruebaCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        fechaCol.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        vistaPruebas.setItems(listaPruebas);
		
        vistaPruebas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarAltaBaja(newValue));
	}

	public void mostrarAltaBaja(Prueba pruebaSeleccionada) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(InicioApp.class.getResource("../Vista/RaizPeque.fxml"));
		
		try {
			raiz = (BorderPane) loader.load();
		} catch (IOException e) {
			System.out.println("Error  " + e.getMessage());
		}
		Scene scene = new Scene(raiz);
		escenario = new Stage();
		
//		scene.getStylesheets().add(getClass().getResource("/Estilo2.css").toExternalForm());
		escenario.setScene(scene);
		escenario.initStyle(StageStyle.TRANSPARENT);

		ControladorRegistrarse controller = loader.getController();
		controller.setInicioApp(app);
		
		escenario.show();
		/* Centrar ventana */
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	    escenario.setX((screenBounds.getWidth() - escenario.getWidth()) / 2); 
	    escenario.setY((screenBounds.getHeight() - escenario.getHeight()) / 2);
	    
	    try {
			// Load person overview.
			loader = new FXMLLoader();
			loader.setLocation(InicioApp.class.getResource("/Vista/AltaBaja.fxml"));
			GridPane altaBaja = (GridPane) loader.load();

			// Set person overview into the center of root layout.
			raiz.setCenter(altaBaja);

			ControladorAltaBaja controllerAB = loader.getController();
			controllerAB.setInicioApp(app, pruebaSeleccionada);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setListaPruebas(ObservableList<Prueba> listaPruebas) {
		this.listaPruebas = listaPruebas;
	}

	public void empezar() {
		session = Utilidades2.getSessionFactory().openSession();
		session.beginTransaction();
	}

	private void terminar() {
		session.getTransaction().commit();
		session.close();
	}

	public InicioApp getApp() {
		return app;
	}

	public void setApp(InicioApp app) {
		this.app = app;
	}

}
