package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConection {

	//en esta clase habrá metodos estaticos
	
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/sportare";
	public static Connection instance = null;

	private DBConection() {
		
	}
	
	public static Connection getConnection () throws SQLException {		//metodo estatico para llamarlo de cualquier sitio

		if (instance == null) {
			Properties props = new Properties ();
			props.put("user", "root");
			props.put( "password", "1234");
			props.put("charset", "UTF-8");

			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			instance = DriverManager.getConnection(JDBC_URL, props); // saldra error y la propagamos
			
		}
		return instance;
	}
	
	
}



