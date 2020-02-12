package Vista;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Modelo.Competicion;
import Modelo.Credencial;
import Modelo.Manga;
import Modelo.Piloto;
import Modelo.Prueba;
import Modelo.Grupo;
import Modelo.Puntuacion;
import Utilidad.Utilidades2;

public class Pruebas {

	private static String obtenerPruebas = "FROM Prueba WHERE idCompeticion = :idCompe";
	private static String obtenerCompeticion = "FROM Competicion WHERE idCompeticion = :idCompe";
	
	private static String obtenerMangas = "FROM Manga WHERE idPrueba = :idPrueba";
	private static String obtenerPruebaParaMangas = "FROM Prueba WHERE idPrueba = :idPrueba";

	private static String obtenerGrupos = "FROM Grupo WHERE idManga = :idManga";
	
	private static String obtenerPilotos = "FROM Piloto WHERE idGrupo = :idGrupo";	
	
	Session session;

	public static void main(String[] args) {
		Pruebas p = new Pruebas();

		//Funcionan//
		p.crearCompeticion(2020, 10, 5);
//		p.agregarPrueba(2020, 1, 14, 1);//idCompeticion
		
		//No probadas//
//		p.agregarPrueba(2000, 8, 30, 1);//idPrueba
//		p.agregarGrupo(1);//idManga
//		p.crearPiloto(p.crearPuntuacion(), 1, 1);//idGrupo idManga
		
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

	public void agregarPrueba(int ano, int mes, int dia, int idCompeticion) {// Voites que no se nos olvide qeu en agiosto no se puede
		empezar();
		// Crear fecha para la prueba
		Calendar calenPrueba = Calendar.getInstance();
		calenPrueba.set(ano, mes - 1, dia);
		ArrayList<Manga> mangas = new ArrayList<Manga>();
		//Obtener la competicion que nos envian
		@SuppressWarnings("unchecked")
		Query<Competicion> qCompeticion = session.createQuery(obtenerCompeticion);
		qCompeticion.setParameter("idCompe", idCompeticion);
		Competicion competicion = ((ArrayList<Competicion>) qCompeticion.list()).get(0);
		// Crear prueba
		Prueba prr = new Prueba(calenPrueba, mangas, competicion);
		session.save(prr);
		terminar();
	}
	
//	public void agregarManga(int idPrueba) {
//		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
//		Manga manga = new Manga(grupos, 1);
//		empezar();
//		// Obtener las mangas que sean de esa prueba
//		@SuppressWarnings("unchecked")
//		Query<Manga> quMangas = session.createQuery(obtenerMangas);
//		quMangas.setParameter("idPrueba", idPrueba);
//		ArrayList<Manga> mangas = (ArrayList<Manga>) quMangas.list();
//		mangas.add(manga);
//		terminar();		
//		empezar();
//		// Obtengo la prueba
//		@SuppressWarnings("unchecked")
//		Query<Prueba> quPrueba = session.createQuery(obtenerPruebaParaMangas);
//		quPrueba.setParameter("idPrueba", idPrueba);
//		ArrayList<Prueba> prueba = (ArrayList<Prueba>) quPrueba.list();
//		//Cogemos fechaPrueba y idCompeticion para luego pasarlas a la prueba con mangas actualizadas
//		Calendar fechaPrueba = prueba.get(0).getFechaPrueba();
//		// Crear la competicion con la nueva prueba
//		Prueba prr = new Prueba(fechaPrueba, mangas, prueba.get(0).getCompeticion());
//		prr.setIdPrueba(idPrueba);
//		
//		System.out.println(prr.getIdCompeticion() + " idCompe");
//		System.out.println(prr.getIdPrueba() + " idPrueba");
//		System.out.println(prr.getMangas().get(0).getIdManga() + " mangas 1");
//		System.out.println(prr.getMangas().get(0).getGrupos().toString() + " mangas 2");
//		
//		terminar();		
//		empezar();
//		session.saveOrUpdate(prr);
//		terminar();		
//	}
	
//	public void agregarGrupo(int idManga, int idCompeticion) {
//		ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
//		Grupo grup = new Grupo(0, pilotos, idManga);
//		empezar();
//		// Obtener los grupos que sean de esa manga
//		@SuppressWarnings("unchecked")
//		Query<Grupo> quGrupos = session.createQuery(obtenerGrupos);
//		quGrupos.setParameter("idManga", idManga);
//		ArrayList<Grupo> grupos = (ArrayList<Grupo>) quGrupos.list();
//		grupos.add(grup);
//		// Crear la competicion con la nueva prueba
//		Manga mang = new Manga( grupos, idCompeticion);
//		terminar();
//		empezar();
//		session.update(mang);
//		terminar();
//	}
	
	//En este metodo a diferencia de los anteriores, que cogíamos los valores de una query se lo ponemos por parametro (asegurarse poner bien)
	public void crearPiloto(Puntuacion puntuacion, int idGrupo, int idManga) {
		Piloto pilots = new Piloto(0, puntuacion, idGrupo);
		System.out.println("Ostia pilots.  Uohh que son de bones, men canteeen ");
		empezar();
		// Obtener los pilotos que sean de ese grupo
		@SuppressWarnings("unchecked")
		Query<Piloto> quPilotos = session.createQuery(obtenerPilotos);
		quPilotos.setParameter("idGrupo", idGrupo);
		ArrayList<Piloto> pilotos = (ArrayList<Piloto>) quPilotos.list();
		pilotos.add(pilots);
		terminar();
		// Crear la competicion con la nueva prueba
		empezar();
		Grupo grup = new Grupo(idGrupo, pilotos, idManga);
		session.update(grup);
		terminar();
	}

	public Puntuacion crearPuntuacion() {	//Perfeccionar
		Scanner teclado = new Scanner(System.in);
		Puntuacion punt = new Puntuacion();
		String leer;
		System.out.print("Altura: ");
		leer=teclado.nextLine();
		punt.setAltura(Integer.parseInt(leer));
		System.out.print("Distancia: ");
		leer=teclado.nextLine();
		punt.setDistancia(Integer.parseInt(leer));
		System.out.print("Tiempo de vuelo: ");
		leer=teclado.nextLine();
		punt.setTiempoVuelo(Integer.parseInt(leer));
		System.out.print("Total: ");
		leer=teclado.nextLine();
		punt.setTotal(Integer.parseInt(leer));
		
		teclado.close();
		return punt;
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
