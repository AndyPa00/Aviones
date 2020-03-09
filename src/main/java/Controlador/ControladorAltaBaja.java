package Controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Competicion;
import Modelo.Grupo;
import Modelo.Manga;
import Modelo.Piloto;
import Modelo.Prueba;
import Modelo.Puntuacion;
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
	private static String obtenerPilotos = "FROM Piloto p, Grupo g, Manga m, Prueba WHERE p.Grupo.idGrupo=g.idGrupo AND g.idManga=m.idManga AND m.idPrueba = :idPrueba";

	private static String obtenerCompeticion = "FROM Competicion WHERE idCompeticion = :idCompe";
	
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
		idCompeticion.setText(String.valueOf(pruebaConCompe.get(0).getCompeticion().getIdCompeticion()));
		terminar();

		///Aqui deja de funcionar///
		
//		empezar();
//		System.out.println(session);
//		Query<Object> qPil = session.createQuery(obtenerPilotos, Object.class);
//		qPil.setParameter("idPrueba", prueba.getIdPrueba());
//		ArrayList<Object> pilotos = (ArrayList<Object>) qPil.list();
//		terminar();
//		numConcursantes.setText(String.valueOf(pilotos.size()));

		numConcursantes.setText("9");
		
//		Session session2 = Utilidades2.getSessionFactory().openSession();
//		session2.beginTransaction();
//		@SuppressWarnings("unchecked")
//		Query<Manga> qMangas = session2.createQuery(obtenerMangas);
//		q.setParameter("idPrueba", prueba.getIdPrueba());
//		ArrayList<Manga> mangas = (ArrayList<Manga>) qMangas.list();
////		terminar();
//		session2.getTransaction().commit();
//		session2.close();
//		
//		
//		ArrayList<Grupo> grupos = new ArrayList<>();
//		for (int i = 0; i < mangas.size(); i++) {
//			Session session3 = Utilidades2.getSessionFactory().openSession();
//			session3.beginTransaction();
//			@SuppressWarnings("unchecked")
//			Query<Grupo> qGrupo = session3.createQuery(obtenerGrupos);			
//			q.setParameter("idManga", mangas.get(i).getIdManga());
//			grupos.addAll((ArrayList<Grupo>) qGrupo.list());
//			session3.getTransaction().commit();
//			session3.close();
//		}
//		
//		ArrayList<Piloto> pilotos = new ArrayList<>();
//		for (int i = 0; i < grupos.size(); i++) {
//			Session session4 = Utilidades2.getSessionFactory().openSession();
//			session4.beginTransaction();
//			@SuppressWarnings("unchecked")
//			Query<Piloto> qPiloto = session4.createQuery(obtenerGrupos);			
//			q.setParameter("idGrupo", grupos.get(i).getIdGrupo());
//			pilotos.addAll((ArrayList<Piloto>) qPiloto.list());
//			session4.getTransaction().commit();
//			session4.close();	
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
	
	@FXML
	public void darseDeAlta2() {	//Deberiamos añadir el piloto con los valores cogidos anteriormente, pero el paso anterior no funciona
		if(altaBaja.getText().equals("Apuntarse")) {
			empezar();
			Piloto piloto = new Piloto(new Puntuacion(0, 0, 0, 0), new Grupo(new ArrayList<>(), new Manga()));
			session.save(piloto);
			terminar();
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
