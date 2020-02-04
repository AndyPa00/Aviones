package Controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Credencial;
import Modelo.Piloto;
import Modelo.Prueba;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControladorAltaBaja {

	private InicioApp app;
	private String date;
	private Session session;
	@FXML private Label idPrueba;
	@FXML private Label idCompeticion;
	@FXML private Label numConcursantes;
	@FXML private Label fecha;
	private static String obtenerPrueba = "FROM Prueba WHERE idPrueba = :idPrueba";
	private SimpleDateFormat sdf;
	
	/* @param Usuario */
	public void setInicioApp(InicioApp app, Prueba prueba) {
		sdf = new SimpleDateFormat("dd/M/yyyy");
		date = sdf.format(prueba.getFechaPrueba().getTime());
		idPrueba.setText(String.valueOf(prueba.getIdPrueba()));
		fecha.setText(date);
		System.out.println(date);
		this.setApp(app);
		empezar();
		@SuppressWarnings("unchecked")
		Query<Prueba> q = session.createQuery(obtenerPrueba);
		q.setParameter("idPrueba", prueba.getIdPrueba());
		ArrayList<Prueba> pruebaConCompe = (ArrayList<Prueba>) q.list();
		terminar();
		System.out.println(pruebaConCompe.get(0).getCompeticion().getIdCompeticion());
		idCompeticion.setText(String.valueOf(pruebaConCompe.get(0).getCompeticion().getIdCompeticion()));
		
	}

	public InicioApp getApp() {
		return app;
	}

	public void setApp(InicioApp app) {
		this.app = app;
	}
	
	public void darseDeAlta() {
		
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
