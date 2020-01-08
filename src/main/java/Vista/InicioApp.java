package Vista;

import java.io.IOException;

import Controlador.ControladorRaiz;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InicioApp extends Application{
	private Stage escena;
	private BorderPane raiz;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage escenaPrimera) throws Exception {
		this.escena = escenaPrimera;
		this.escena.setTitle("Liga F5J");
		iniciarRaiz();
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
		escena.setScene(scene);
		
		ControladorRaiz controller = loader.getController();
		controller.setInicioApp(this);
		escena.show();
	}
	
	public void registrarse() {
		
	}
	
	public void iniciarSesion() {
		
	}
	
	public void recuperarUsuario() {
		
	}

	public void recuperarContrasena() {
		
	}
}
