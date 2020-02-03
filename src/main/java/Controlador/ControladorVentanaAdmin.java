package Controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Competicion;
import Modelo.Credencial;
import Modelo.Manga;
import Modelo.Prueba;
import Modelo.Puntuacion;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ControladorVentanaAdmin {	//Clase en prueba

	private Session session;
	private InicioApp app;
	@FXML private Text usuario;
	@FXML private Text numLicencia;
	@FXML private DatePicker fechaCompe;
	@FXML private Text correcionClasificacion;
	
	@FXML private TextField diaC;
	@FXML private TextField mesC;
	@FXML private TextField annoC;
	@FXML private TextField diaP;
	@FXML private TextField mesP;
	@FXML private TextField annoP;
	@FXML private TextField compeID;
	@FXML private TextField pruebaID;
	@FXML private TextField mangaID;
	
	@FXML private Button competicio;
	@FXML private Button prueb;
	@FXML private Button mang;
	@FXML private Button grup;

	private static String obtenerUsuario = "FROM Credencial";
	private static String obtenerPuntos = "FROM puntuacion";
	private static String obtenerCompe = "FROM competicion";
	private static String obtenerPruebas = "FROM Prueba WHERE idCompeticion = :idCompe";
	private static String obtenerFechaCompe = "FROM Competicion WHERE idCompeticion = :idCompe";
	

	/* @param Usuario */
	public void setInicioApp(InicioApp app) {
		this.app = app;
		this.app.getClass();//Pa rellenar
	}

	public void recibeParametros(String usuario, int numLicencia){
		//Aqui estableceremos los parametros en la vista de pilotos
		this.usuario.setText(usuario);
		this.numLicencia.setText(String.valueOf(numLicencia));
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
	
	@FXML
	private void empezarCompe() {
		LocalDate fechaDeLaCompe = null;
		boolean existeCompe = true, bienFormado = true;;
		
		empezar();
		@SuppressWarnings("unchecked")
		Query<Competicion> qCom = session.createQuery(obtenerCompe);
		ArrayList<Competicion> competisiones = (ArrayList<Competicion>) qCom.list();
		terminar();
		if (competisiones.isEmpty()) {
			existeCompe = false;
		}else {
			try {
				fechaDeLaCompe = fechaCompe.getValue();
			} catch (NumberFormatException e) {
				bienFormado = false;
			} catch (Exception e) {
				correcionClasificacion.setText("Introduce bien los Datos");
			}
		}				
		
		if (!existeCompe || fechaDeLaCompe.getYear()!=Calendar.YEAR) {
			int dia = 0, mes = 0, ano = 0;
			try {
				dia = fechaDeLaCompe.getDayOfMonth();
				mes = fechaDeLaCompe.getMonthValue();
				ano = fechaDeLaCompe.getYear();
			} catch (NumberFormatException e) {
				bienFormado = false;
			}catch (Exception e) {
				System.out.println("Error "+e.getMessage());
			}
			if(bienCreado(bienFormado, ano, mes, dia, 1)) {
				crearCompeticion(ano, mes, dia);
			}
		}else {
			//Poner boton disable y alert
			competicio.setDisable(true);
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ya existe una competición");
			alert.setHeaderText("Cuidaoo!!");
			alert.setContentText("Para iniciar otra competición seleccione el año posterior a " + Calendar.YEAR);

			alert.showAndWait();
			System.exit(0);
		}
		
	}
	
//	public boolean hayCompe() {
//		empezar();
//		@SuppressWarnings("unchecked")
//		Query<Competicion> qIS = session.createQuery(obtenerCompe);
//		ArrayList<Competicion> compes = (ArrayList<Competicion>) qIS.list();
//		terminar();
//		if (compes.isEmpty()) {
//			return false;
//		}else {
//			return true;
//		}		
//	}
	
	public void crearCompeticion(int ano, int mes, int dia) {
		empezar();
		Calendar c = Calendar.getInstance();
		c.set(ano, mes - 1, dia);
		ArrayList<Prueba> pruebas = new ArrayList<Prueba>();
		Competicion com = new Competicion(c, pruebas);
		session.save(com);
		terminar();
	}
    
	@FXML
	private void agregarPrueba() {
		int dia = 0, mes = 0, ano = 0, idCompet = 0;
		boolean bienFormado = true;
		//Aqui le paso si los parametros son buenos
		try {
			dia = Integer.parseInt(diaC.getText());
			mes = Integer.parseInt(mesC.getText());
			ano = Integer.parseInt(annoC.getText());
			idCompet = Integer.parseInt(compeID.getText());
		} catch (NumberFormatException e) {
			bienFormado = false;
		}catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		if(bienCreado(bienFormado, ano, mes, dia, idCompet)) {
			crearPrueba(ano, mes, dia, idCompet);
		}
	}
	
	public void crearPrueba(int ano, int mes, int dia, int idCompeticion) {
		empezar();
		// Crear fecha para la prueba
		Calendar calenPrueba = Calendar.getInstance();
		calenPrueba.set(ano, mes - 1, dia);
		System.out.println(calenPrueba.getTime());
		ArrayList<Manga> mangas = new ArrayList<Manga>();
		// Crear prueba
		Prueba prr = new Prueba(calenPrueba, mangas, idCompeticion);
		// Obtener las pruebas que sean de esa competicion
		@SuppressWarnings("unchecked")
		Query<Prueba> quPruebas = session.createQuery(obtenerPruebas);
		quPruebas.setParameter("idCompe", idCompeticion);
		ArrayList<Prueba> pruebas = (ArrayList<Prueba>) quPruebas.list();
		// Agregar la prueba nueva al arraylist
		pruebas.add(prr);
		terminar();
		empezar();
		// Obtengo la competicion
		@SuppressWarnings("unchecked")
		Query<Competicion> quCompe = session.createQuery(obtenerFechaCompe);
		quCompe.setParameter("idCompe", idCompeticion);
		ArrayList<Competicion> competicion = (ArrayList<Competicion>) quCompe.list();
		Calendar fechaCompe = competicion.get(0).getFechaInscripcion();
		// Crear la competicion con la nueva prueba
		Competicion compe = new Competicion(idCompeticion, fechaCompe, pruebas);
		terminar();
		empezar();
		session.update(compe);
		terminar();
	}
	
	public boolean bienCreado(boolean bienFormado, int ano, int mes, int dia, int idCompet) {
		if (bienFormado && mes!=8 && idCompet>0 && idCompet<9999 && ano>2000 && ano<3000) {
			if (mes==1 || mes==3 || mes==5 || mes==7 || mes==10 || mes==12) {
				if (dia<=31 && dia>=1) {
					return true;
				}
			}else if (mes==2 || mes==4 || mes==6 || mes==9 || mes==11) {
				if (dia<=30 && dia>=1) {
					return true;
				}
			}			
		}else {
			competicio.setDisable(true);
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Variables Incorrectas");
			alert.setHeaderText("Por favor declara bien las variables");
			alert.setContentText("Comprueba que no este mal formado o que el mes no sea agosto.");

			alert.showAndWait();
			System.exit(0);
		}
		return false;
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