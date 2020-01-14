package Vista;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Competicion;
import Modelo.Credencial;
import Modelo.Manga;
import Modelo.Prueba;
import Utilidad.Utilidades2;

public class Pruebas {

	private static String obtenerPruebas = "FROM Prueba WHERE idCompeticion = :idCompe";
	private static String obtenerFechaCompe = "FROM Competicion WHERE idCompeticion = :idCompe";

	Session session;

	public static void main(String[] args) {
		Pruebas p = new Pruebas();

		p.agregarPrueba(2020, 1, 14, 1);
//		p.crearCompeticion(2020, 1, 1);

	}

	public void crearCompeticion(int ano, int mes, int dia) { // Funciona
		empezar();
		Calendar c = Calendar.getInstance();
		c.set(ano, mes-1, dia);
		ArrayList<Prueba> pruebas = new ArrayList<Prueba>();
		Competicion com = new Competicion(c, pruebas);
		session.save(com);
		terminar();
	}

//	public void agregarPrueba(int ano, int mes, int dia, int idCompeticion) {
//		empezar();
//		Calendar calen = Calendar.getInstance();
//		calen.set(ano, mes, dia);
//		ArrayList<Manga> mangas = new ArrayList<Manga>();
//		Prueba prr = new Prueba(calen, mangas, idCompeticion);
//		session.save(prr);
//		terminar();
//	}

	public void agregarPrueba(int ano, int mes, int dia, int idCompeticion) {// Que no se nos olvide qeu en agiosto no
																				// se puede
		empezar();
		// Crear fecha para la prueba
		Calendar calenPrueba = Calendar.getInstance();
		calenPrueba.set(ano, mes - 1, dia);
		System.out.println(calenPrueba.getTime());
		// Crear array de mangas vacio
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
		ArrayList<Competicion> competiciones = (ArrayList<Competicion>) quCompe.list();
		Calendar calenCompe = competiciones.get(0).getFechaInscripcion();
		// Crear la competicion con la nueva prueba
		Competicion com = new Competicion(idCompeticion, calenCompe, pruebas);
		terminar();
		empezar();
		session.update(com);
		terminar();
	}

	public void crearUsuario() { // Funciona
		empezar();
		Credencial cred = new Credencial("LauraAAA", "aaa", 1, "Laura", "Cinturita", "Avispa");
		session.save(cred);
		System.out.println(cred.toString());
		terminar();
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
