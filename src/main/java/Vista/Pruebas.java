package Vista;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Session;

import Modelo.Competicion;
import Modelo.Credencial;
import Modelo.Prueba;
import Utilidad.Utilidades2;

public class Pruebas {
	
	Session session;
	public static void main(String[] args) {
		Pruebas p=new Pruebas();
		//Crear usuario
		p.crearPrueba();
		
	}
	
	public void crearCompeticion() {
		empezar();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2000);
		c.set(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		ArrayList<Prueba> pruebas = new ArrayList<Prueba>();
		Competicion com = new Competicion(c, pruebas);
		Prueba pr = new Prueba(c);
		session.save(pr);
		System.out.println(pr.toString());
		terminar();
	}
	
	public void crearUsuario() {
		empezar();
		Credencial cred = new Credencial("LauraAAA", "aaa", 1, "Laura", "Cinturita", "Avispa");
		session.save(cred);
		System.out.println(cred.toString());
		terminar();
	}
	
	public void crearPrueba() {
		empezar();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2000);
		c.set(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Prueba pr = new Prueba(c);
		session.save(pr);
		System.out.println(pr.toString());
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
