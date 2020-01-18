package Vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import Controlador.ControladorIniciarSesion;
import Controlador.ControladorInicio;
import Controlador.ControladorRegistrarse;
import Modelo.Credencial;
import Utilidad.Utilidades2;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InicioApp extends Application{
	private Stage escenario;
	private AnchorPane raiz;
	private BorderPane raiz2;
	private Session session1;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primerEscenario) throws Exception {
		this.escenario = primerEscenario;
		this.escenario.setTitle("Liga F5J");
		iniciarInicio();
	}
	
	public void iniciarInicio() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(InicioApp.class.getResource("../Vista/Inicio.fxml"));
		
		try {
			raiz = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println("Error  " + e.getMessage());
		}
		Scene scene = new Scene(raiz);
//		scene.getStylesheets().add(getClass().getResource("/Estilo.css").toExternalForm());
		escenario.setScene(scene);
		
		ControladorInicio controller = loader.getController();
		controller.setInicioApp(this);
		escenario.show();
	}
	
	public void iniciarIniciarSesion() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(InicioApp.class.getResource("../Vista/IniciarSesion2.fxml"));
		
		try {
			raiz = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println("Error  " + e.getMessage());
		}
		Scene scene = new Scene(raiz);
		scene.getStylesheets().add(getClass().getResource("/Estilo.css").toExternalForm());
		escenario.setScene(scene);
		ControladorIniciarSesion controller = loader.getController();
		controller.setInicioApp(this);
		escenario.show();
	}
	
	public void iniciarRegistrarse() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(InicioApp.class.getResource("../Vista/Registrarse3.fxml"));
		
		try {
			raiz = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println("Error  " + e.getMessage());
		}
		Scene scene = new Scene(raiz);
		scene.getStylesheets().add(getClass().getResource("/Estilo.css").toExternalForm());
		escenario.setScene(scene);
		
		ControladorRegistrarse controller = loader.getController();
		controller.setInicioApp(this);
		escenario.show();
	}
	
	public void iniciarRegistrarseBorder() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(InicioApp.class.getResource("../Vista/Registrarse4.fxml"));
		
		try {
			raiz2 = (BorderPane) loader.load();
		} catch (IOException e) {
			System.out.println("Error  " + e.getMessage());
		}
		Scene scene = new Scene(raiz2);
		scene.getStylesheets().add(getClass().getResource("/Estilo2.css").toExternalForm());
		escenario.setScene(scene);
		escenario.initStyle(StageStyle.TRANSPARENT);
		
		ControladorRegistrarse controller = loader.getController();
		controller.setInicioApp(this);
		escenario.show();
	}
	
	public void iniciarRaiz() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(InicioApp.class.getResource("../Vista/Raiz.fxml"));
		
		try {
			raiz2 = (BorderPane) loader.load();
		} catch (IOException e) {
			System.out.println("Error  " + e.getMessage());
		}
		Scene scene = new Scene(raiz2);
		scene.getStylesheets().add(getClass().getResource("/Estilo2.css").toExternalForm());
		escenario.setScene(scene);
		
		ControladorRegistrarse controller = loader.getController();
		controller.setInicioApp(this);
		escenario.show();
	}
	
	public void mostrarRegistrarse() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InicioApp.class.getResource("Vista/Registrarse5.fxml"));
            AnchorPane registrarse = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            raiz2.setCenter(registrarse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@FXML
	public void registrarse() {
		session1 = Utilidades2.getSessionFactory().openSession();
		List<Credencial> listaUsuarios = new ArrayList<Credencial>();
		TypedQuery<Credencial> query = session1.createQuery("FROM Credencial", Credencial.class);
		listaUsuarios = query.getResultList();
		for (int i = 0; i < listaUsuarios.size(); i++) {
			System.out.println(listaUsuarios.get(i).toString());
		}
	}
	
	public void recuperarUsuario() {
		
	}

	public void recuperarContrasena() {
		
	}

	public void iniciarSesion() {
		System.out.println("Se ha iniciado sesion");
	}

}
