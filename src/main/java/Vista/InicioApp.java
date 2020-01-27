package Vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import Controlador.ControladorIniciarSesion;
import Controlador.ControladorInicio;
import Controlador.ControladorPerfil;
import Controlador.ControladorPruebas;
import Controlador.ControladorRecuperarPassw;
import Controlador.ControladorRecuperarUsser;
import Controlador.ControladorRegistrarse;
import Controlador.ControladorVistaPilotos;
import Controlador.ControladorVistaPilotosAdmin;
import Modelo.Credencial;
import Utilidad.Utilidades2;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InicioApp extends Application {
	private Stage escenario;
	private BorderPane raiz;
	private BorderPane vistaPilotos;
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
			raiz = (BorderPane) loader.load();
		} catch (IOException e) {
			System.out.println("Error  " + e.getMessage());
		}
		Scene scene = new Scene(raiz);
//		scene.getStylesheets().add(getClass().getResource("/Estilo2.css").toExternalForm());
		escenario.setScene(scene);
		escenario.initStyle(StageStyle.TRANSPARENT);

		ControladorRegistrarse controller = loader.getController();
		controller.setInicioApp(this);
		escenario.show();
		
		/* Centrar ventana */
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	    escenario.setX((screenBounds.getWidth() - escenario.getWidth()) / 2); 
	    escenario.setY((screenBounds.getHeight() - escenario.getHeight()) / 2);
	}

	public void mostrarRegistrarse() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(InicioApp.class.getResource("/Vista/Registrarse.fxml"));
			AnchorPane registrarse = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			raiz.setCenter(registrarse);

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
			raiz.setCenter(iniciarSesion);

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
			loader.setLocation(InicioApp.class.getResource("/Vista/Inicio.fxml"));
			AnchorPane inicio = (AnchorPane) loader.load();

			escenario.setWidth(600);
			escenario.setHeight(500);

			// Set person overview into the center of root layout.
			raiz.setCenter(inicio);

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

	public void mostrarRecuperarUsuario() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(InicioApp.class.getResource("/Vista/RecuperaUser.fxml"));
			AnchorPane inicio = (AnchorPane) loader.load();

			escenario.setWidth(600);
			escenario.setHeight(500);

			// Set person overview into the center of root layout.
			raiz.setCenter(inicio);

			ControladorRecuperarUsser controller = loader.getController();
			controller.setInicioApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarRecuperarContrasena() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(InicioApp.class.getResource("/Vista/RecuperaPassw.fxml"));
			AnchorPane inicio = (AnchorPane) loader.load();

			escenario.setWidth(600);
			escenario.setHeight(500);

			// Set person overview into the center of root layout.
			raiz.setCenter(inicio);

			ControladorRecuperarPassw controller = loader.getController();
			controller.setInicioApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void iniciarSesion(Credencial usuario) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(InicioApp.class.getResource("/Vista/VistaPilotos2.fxml"));
			vistaPilotos = (BorderPane) loader.load();

			escenario.setWidth(700);
			escenario.setHeight(500);
			/* Centrar ventana */
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		    escenario.setX((screenBounds.getWidth() - escenario.getWidth()) / 2); 
		    escenario.setY((screenBounds.getHeight() - escenario.getHeight()) / 2);
		    
			// Set person overview into the center of root layout.
			raiz.setCenter(vistaPilotos);

			ControladorVistaPilotos controller = loader.getController();
//            controller.recibeParametros(usuario, numLicencia);
			controller.setInicioApp(this, usuario);
			mostrarPerfil(usuario);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void iniciarSesionAdmin(Credencial usuario) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(InicioApp.class.getResource("/Vista/VistaAdmin.fxml"));
			vistaPilotos = (BorderPane) loader.load();

			escenario.setWidth(700);
			escenario.setHeight(500);
			// Set person overview into the center of root layout.
			raiz.setCenter(vistaPilotos);

			ControladorVistaPilotosAdmin controller = loader.getController();
//            controller.recibeParametros(usuario, numLicencia);
			controller.setInicioApp(this, usuario);
			mostrarPerfil(usuario);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarPerfil(Credencial usuario) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(InicioApp.class.getResource("/Vista/Perfil.fxml"));
			GridPane perfil = (GridPane) loader.load();

			// Set person overview into the center of root layout.
			vistaPilotos.setCenter(perfil);

			ControladorPerfil controller = loader.getController();
			controller.setInicioApp(this, usuario);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarClasificacion() {

		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(InicioApp.class.getResource("/Vista/Clasificacion.fxml"));
			GridPane clasificacion = (GridPane) loader.load();

			// Set person overview into the center of root layout.
			vistaPilotos.setCenter(clasificacion);

//            ControladorInicio controller = loader.getController();
//    		controller.setInicioApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarPruebas() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(InicioApp.class.getResource("/Vista/Pruebas.fxml"));
			GridPane clasificacion = (GridPane) loader.load();

			// Set person overview into the center of root layout.
			vistaPilotos.setCenter(clasificacion);

			ControladorPruebas controller = loader.getController();
			controller.setInicioApp(this);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarEditor() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(InicioApp.class.getResource("/Vista/Editar.fxml"));
			AnchorPane editor = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			vistaPilotos.setCenter(editor);

//            ControladorInicio controller = loader.getController();
//    		controller.setInicioApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
