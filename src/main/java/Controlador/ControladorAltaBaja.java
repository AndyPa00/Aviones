package Controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Credencial;
import Modelo.Grupo;
import Modelo.Manga;
import Modelo.Piloto;
import Modelo.Prueba;
import Utilidad.Utilidades2;
import Vista.InicioApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControladorAltaBaja {

	private InicioApp app;
	private String date;
	private Session session;
	@FXML
	private Button altaBaja;
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
	
//	private static String obtenerMangas = "FROM Prueba WHERE idPrueba = :idPrueba";
//	private static String obtenerGrupos = "FROM Manga WHERE idManga = :idManga";
//	private static String obtenerPilotos = "FROM Grupo WHERE idGrupo = :idGrupo";	
	
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

		///Aqui deja de funcionar///
		
//		empezar();
//		@SuppressWarnings("unchecked")
//		Query<Piloto> qPil = session.createQuery(obtenerPilotos);
//		q.setParameter("idPrueba", prueba.getIdPrueba());
//		ArrayList<Piloto> pilotos = (ArrayList<Piloto>) qPil.list();
//		terminar();
//		numConcursantes.setText(String.valueOf(pilotos.size()));

		numConcursantes.setText("9");
		
//		empezar();
//		@SuppressWarnings("unchecked")
//		Query<Manga> qMangas = session.createQuery(obtenerMangas);
//		q.setParameter("idPrueba", prueba.getIdPrueba());
//		ArrayList<Manga> mangas = (ArrayList<Manga>) qMangas.list();
//		terminar();
//		
//		ArrayList<Grupo> grupos = new ArrayList<>();
//		for (int i = 0; i < mangas.size(); i++) {
//			empezar();
//			@SuppressWarnings("unchecked")
//			Query<Grupo> qGrupo = session.createQuery(obtenerGrupos);			
//			q.setParameter("idManga", mangas.get(i).getIdManga());
//			grupos.addAll((ArrayList<Grupo>) qGrupo.list());
//			terminar();	
//		}
//		
//		ArrayList<Piloto> pilotos = new ArrayList<>();
//		for (int i = 0; i < grupos.size(); i++) {
//			empezar();
//			@SuppressWarnings("unchecked")
//			Query<Piloto> qPiloto = session.createQuery(obtenerGrupos);			
//			q.setParameter("idGrupo", grupos.get(i).getIdGrupo());
//			pilotos.addAll((ArrayList<Piloto>) qPiloto.list());
//			terminar();	
//		}
		
	}

	public InicioApp getApp() {
		return app;
	}

	public void setApp(InicioApp app) {
		this.app = app;
	}

	@FXML
	public void darseDeAlta() {
		if(altaBaja.getText().equals("Apuntarse")) {
			numConcursantes.setText("10");
			altaBaja.setText("Desapuntarse");			
		} else {
			numConcursantes.setText("9");
			altaBaja.setText("Apuntarse");
		}
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
