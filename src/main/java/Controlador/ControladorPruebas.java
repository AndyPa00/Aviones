package Controlador;

import java.util.List;

import org.hibernate.Session;

import Modelo.Prueba;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControladorPruebas {

	private InicioApp app;
	private ObservableList<Prueba> listaPruebas = FXCollections.observableArrayList();
	private Session session;
	private static String obtenerPruebas = "FROM Prueba";
	@FXML
	private TableView<Prueba> vistaPruebas;
	@FXML
    private TableColumn<Prueba, String> pruebaCol;
	@FXML
    private TableColumn<Prueba, String> fechaCol;
	
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
