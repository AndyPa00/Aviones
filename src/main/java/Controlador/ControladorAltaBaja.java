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
	@FXML
	private Label idPrueba;
	@FXML
	private Label idCompeticion;
	@FXML
	private Label numConcursantes;
	@FXML
	private Label fecha;
	private static String obtenerPrueba = "FROM Prueba WHERE idPrueba = :idPrueba";
	private static String obtenerPilotos = "FROM Piloto, Grupo, Manga, Prueba WHERE Piloto.idGrupo"
			+ " = Grupo.idGrupo AND Grupo.idManga = Manga.idManga AND Manga.idPrueba = :idPrueba";
	private SimpleDateFormat sdf;
	// Competicion>Prueba>Manga>Grupos>Pilotos
	
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
		idCompeticion.setText(String.valueOf(pruebaConCompe.get(0).getCompeticion().getIdCompeticion()));
		empezar();
		@SuppressWarnings("unchecked")
		Query<Piloto> qPil = session.createQuery(obtenerPilotos);
		q.setParameter("idPrueba", prueba.getIdPrueba());
		ArrayList<Piloto> pilotos = (ArrayList<Piloto>) qPil.list();
		terminar();
		numConcursantes.setText(String.valueOf(pilotos.size()));
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
