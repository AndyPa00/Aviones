package Vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import Controlador.ControladorRaiz;
import Modelo.Credencial;
import Utilidad.Utilidades2;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InicioApp extends Application{
	private Stage escena;
	private BorderPane raiz;
	private Session session1;
	
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
	
	public void iniciarSesion() {

		Utilidades2.getSessionFactory().close();
	}
	
	public void recuperarUsuario() {
		
	}

	public void recuperarContrasena() {
		
	}

}
