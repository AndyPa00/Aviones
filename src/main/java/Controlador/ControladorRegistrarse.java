package Controlador;

import org.hibernate.Session;

import Modelo.Credencial;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControladorRegistrarse {
	
	private Session session;
	private InicioApp app;
	@FXML
	private TextField nombre;
	@FXML
	private TextField apellido1;
	@FXML
	private TextField apellido2;
	@FXML
	private TextField usuario;
	@FXML
	private TextField contrasena;
	@FXML
	private TextField numLicencia;
	@FXML
	private AnchorPane marco;
	double x,y;
	
	/* @param Usuario */
	public void setInicioApp(InicioApp app) {
//		marco.getStyleClass().add("marco");
		this.app = app;
	}
	
	@FXML
	private void registrarse() {
		empezar();
		try {
			Credencial cred = new Credencial(usuario.getText(), contrasena.getText(),
					Integer.parseInt(numLicencia.getText()), nombre.getText(),
					apellido1.getText(), apellido2.getText());
			session.save(cred);
			System.out.println(cred.toString());
			terminar();
			System.out.println("Usuario creado");
			app.iniciarInicio();			
		} catch (Exception e) {
		}
	}
	
	@FXML
	private void volverAtras() {
		app.iniciarInicio();
	}
	
	@FXML
	private void iniciarIniciarSesion() {
		app.iniciarIniciarSesion();
	}
	
	@FXML
	void presionado(MouseEvent event) {
		x = event.getSceneX();
		y = event.getSceneY();
	}
	
	@FXML
    void arrastrado(MouseEvent event) {
		Stage escenario = (Stage)((Node)event.getSource()).getScene().getWindow();
		escenario.setX(event.getScreenX() - x);
		escenario.setY(event.getScreenY() - y);
    }
	
	@FXML
    void minimizar(MouseEvent event) {
		Stage escenario = (Stage)((Node)event.getSource()).getScene().getWindow();
		escenario.setIconified(true);
    }
	
    @FXML
    void maximizar(MouseEvent event) {
    	Stage escenario = (Stage)((Node)event.getSource()).getScene().getWindow();
		if(escenario.getFullScreenExitHint()==null) {
			escenario.setFullScreenExitHint("");
			escenario.setFullScreen(true);
		} else {
			escenario.setFullScreen(false);
			escenario.setFullScreenExitHint(null);
		}
    }

    @FXML
    void cerrar(MouseEvent event) {
    	Stage escenario = (Stage)((Node)event.getSource()).getScene().getWindow();
		escenario.close();
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
