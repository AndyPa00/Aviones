package Vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import Controlador.ControladorIniciarSesion;
import Controlador.ControladorInicio;
import Controlador.ControladorRegistrarse;
import Controlador.ControladorVentanaPiloto;
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
	private AnchorPane raizAnchor;
	private BorderPane raizBorder;
	private Session session1;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primerEscenario) throws Exception {
		this.escenario = primerEscenario;
		this.escenario.setTitle("Liga F5J");
		iniciarRaiz();
		mostrarInicio();
	}
	
	public void iniciarRaiz() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(InicioApp.class.getResource("../Vista/Raiz.fxml"));
		
		try {
			raizBorder = (BorderPane) loader.load();
		} catch (IOException e) {
			System.out.println("Error  " + e.getMessage());
		}
		Scene scene = new Scene(raizBorder);
//		scene.getStylesheets().add(getClass().getResource("/Estilo2.css").toExternalForm());
		escenario.setScene(scene);
		escenario.initStyle(StageStyle.TRANSPARENT);
		
		ControladorRegistrarse controller = loader.getController();
		controller.setInicioApp(this);
		escenario.show();
	}
	
	public void mostrarRegistrarse() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InicioApp.class.getResource("/Vista/Registrarse.fxml"));
            AnchorPane registrarse = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            raizBorder.setCenter(registrarse);
            
            ControladorRegistrarse controller = loader.getController();
    		controller.setInicioApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void mostrarIniciarSesion() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InicioApp.class.getResource("/Vista/IniciarSesion.fxml"));
            AnchorPane iniciarSesion = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            raizBorder.setCenter(iniciarSesion);
            
            ControladorIniciarSesion controller = loader.getController();
    		controller.setInicioApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void mostrarInicio() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InicioApp.class.getResource("/Vista/Inicio2.fxml"));
            AnchorPane inicio = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            raizBorder.setCenter(inicio);
            
            ControladorInicio controller = loader.getController();
    		controller.setInicioApp(this);
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
		try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InicioApp.class.getResource("/Vista/VistaPilotos.fxml"));
            AnchorPane inicio = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            raizBorder.setCenter(inicio);
            
            ControladorVentanaPiloto controller = loader.getController();
    		controller.setInicioApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
