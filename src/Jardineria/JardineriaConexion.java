package Jardineria;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.sql.*;

public class JardineriaConexion {
	
	
	static String servidor;
	static String bbdd;
	static String usuario;
	static String password;
	
	
	static Connection conector;
	
	public JardineriaConexion() {
		try {
		this.leerArchivo();
		conector=DriverManager.getConnection
				(servidor+bbdd, usuario, password);
		}catch(Exception e) {
			System.out.println("No es posible conectar");
		}
	}
	
	
	
	public static void leerArchivo() {
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream
					("C:/Users/IFC/eclipse-workspace/UD12_BasesDatos/src/jardineriaProperties/jardineria.properties"));
			
			servidor = propiedades.getProperty("dataBaseServer");
			bbdd = propiedades.getProperty("dataBaseCatalog");
			usuario = propiedades.getProperty("dataBaseUser");
			password = propiedades.getProperty("dataBasePassword");
			
			/*imprimimos los valores*/
			System.out.println("server: "+servidor+"\nBBDD: "+bbdd+"\nUsuario: "+usuario+"\nPassword: "+password);
		}catch(FileNotFoundException e) {
			System.out.println("El archivo no existe");
		}catch(IOException e) {
			System.out.println("Error, no se puede leer el archivo");
		}
	}
	
	
	
	
	/*Metodos operaciones*/
	
	
	public static void Select (String sql) {
		try {
			Statement miStatement = conector.createStatement();
			ResultSet rs = miStatement.executeQuery(sql);
		
			System.out.println("\n\n Select");
			while(rs.next()) {
				for(int i=1; i<=rs.getMetaData().getColumnCount();i++) {
					System.out.print(rs.getString(i)+"\t");
				}
				System.out.println("\n\n");
			}
		}catch(Exception e) {
			System.out.println("No es posible conectar");
		}
	}
	
	
	
	
	public static void IUD (String sql) {
		try {
			
			Statement miStatement = conector.createStatement();
			miStatement.executeUpdate(sql);
			
		}catch(Exception e) {
			System.out.println("No es posible conectar");
		}
	}

}
