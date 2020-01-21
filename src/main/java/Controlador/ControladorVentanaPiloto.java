
package Controlador;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Credencial;
import Modelo.Prueba;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControladorVentanaPiloto {

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
		Query<Credencial> qIS = session.createQuery(obtenerUsuario);
		qIS.setParameter("usuario", usuario.getText());
		ArrayList<Credencial> credencial = (ArrayList<Credencial>) qIS.list();
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
	private void mostrarClasificacion() {	//Esto hay que pulirlo
//		TableView<Credencial> tableView = new TableView<>();
//		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//
//		TableColumn<Credencial, String> tcNombre = new TableColumn<>("Nombre");
//		tcNombre.setCellValueFactory(c -> c.getValue().firstNameProperty());
//
//		TableColumn<Credencial, String> tcApellido = new TableColumn<>("Apellido");
//		tcApellido.setCellValueFactory(c -> c.getValue().lastNameProperty());
//
//		TableColumn<Credencial, Number> tcEdad = new TableColumn<>("Edad");
//		tcEdad.setCellValueFactory(c -> c.getValue().ageProperty());
//
//		tableView.getColumns().add(tcNombre);
//		tableView.getColumns().add(tcApellido);
//		tableView.getColumns().add(tcEdad);
//		tableView.setItems(createPersonList());

//		TextField search = new TextField();
//		search.setPromptText("Buscar...");
//		HBox.setHgrow(search, Priority.ALWAYS);
//
//		HBox searchBar = new HBox();
//		searchBar.getChildren().add(search);
//
//		VBox root = new VBox();
//		root.setSpacing(5.0);
//		root.setPadding(new Insets(10.0));
//		root.getChildren().addAll(searchBar, tableView);
//
//		Scene scene = new Scene(root, 800, 600);
//
//		primaryStage.setTitle("JavaFX Buscar en TableView");
//		primaryStage.setScene(scene);
//		primaryStage.show();
	}
	
	@FXML
	private void volverAtras() {
		app.iniciarSesion();
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