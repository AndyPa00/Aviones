package Utilidad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Utilidades {

	public String dbms;
	public String dbName;
	public String testDbName;
	public String test_mode;
	public String userName;
	public String password;
	public String urlString;

	private String driver;
	private String serverName;
	private int portNumber;
	private Properties prop;

	private static final String PROPERTIES_FILE = "Planea.xml";

	public Utilidades() throws FileNotFoundException, IOException, InvalidPropertiesFormatException {
		this.setProperties(PROPERTIES_FILE);
	}

	private void setProperties(String fileName) throws IOException, InvalidPropertiesFormatException {
		this.prop = new Properties();
		prop.loadFromXML(Files.newInputStream(Paths.get(fileName)));

		this.dbms = this.prop.getProperty("dbms");
		this.driver = this.prop.getProperty("driver");
		this.dbName = this.prop.getProperty("database_name");
		this.testDbName = this.prop.getProperty("test_database_name");
		this.test_mode = this.prop.getProperty("test_mode");
		this.userName = this.prop.getProperty("user_name");
		this.password = this.prop.getProperty("password");
		this.serverName = this.prop.getProperty("server_name");
		this.portNumber = Integer.parseInt(this.prop.getProperty("port_number"));
	}

	public Connection getConnection() throws SQLException {

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		if (this.dbms.equals("mysql")) {
			
			if (this.test_mode.equals("yes")) {
				conn = DriverManager.getConnection("jdbc:" + this.dbms + "://" + this.serverName + ":" + this.portNumber
						+ "/" + this.testDbName + "?serverTimezone=UTC", connectionProps);	
				
			}else {
				conn = DriverManager.getConnection("jdbc:" + this.dbms + "://" + this.serverName + ":" + this.portNumber
						+ "/" + this.dbName + "?serverTimezone=UTC", connectionProps);
			}
			
			
	
		} else if (this.dbms.equals("derby")) {
			conn = DriverManager.getConnection("jdbc:" + this.dbms + ":" + this.dbName + ";create=true",
					connectionProps);
		}
		System.out.println("Connectado a BD");
		return conn;
	}

	public static void closeConnection(Connection connArg) {
		System.out.println("Releasing all open resources ...");
		try {
			if (connArg != null) {
				connArg.close();
				connArg = null;
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
	}

	public static void printSQLException(SQLException e) {

		while (e != null) {
			if (e instanceof SQLException) {
				// Estado ANSI
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				// C�dio de error propio de cada gestor de BD
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				// Mensaje textual
				System.err.println("Message: " + e.getMessage());

				// Objetos desencadenantes de la excepci�n
				Throwable t = e.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
				// Cualquier otra excepci�n encadenada
				e = e.getNextException();

			}
		}
	}

}
