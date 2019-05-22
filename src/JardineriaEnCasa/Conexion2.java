package JardineriaEnCasa;

import java.io.*;
import java.sql.*;
import java.util.*;

import JardineriaCorregido.Conexion;

public class Conexion2 {
	 String servidor;
	 String usuario;
	 String password;
	static Connection conector;
		
	

	public Conexion2() {
	
		try {
			try {
				/*Cargamos el archivo desde la ruta especificada*/
				Properties propiedades = new Properties ();
				propiedades.load(new FileInputStream("C:/Users/Esther/git/BBDD_JARDINERIA/src/configuration/properties"));
			
				//propiedades.load(new FileInputStream
				//		("C:/Users/IFC/git/BBDD_JARDINERIA/src/configuration/properties"));
				this.servidor = propiedades.getProperty("dataBaseServer");
				this.usuario = propiedades.getProperty("dataBaseUser");
				this.password = propiedades.getProperty("dataBasePassword");
				/*Imprimimos los valores*/
						 					
			}catch (FileNotFoundException e) {
				 System.out.println("Error, El archivo no exiSte");
			} catch (IOException e) {
				 System.out.println("Error, No se puede leer el archivo");
			}
			
			
			conector= DriverManager.getConnection(servidor, usuario, password);
			
			
			
		}catch (Exception e) {
			System.out.println("noConecta");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public static void Select (String sql) {
		try {
			Statement miStatement = conector.createStatement();
			ResultSet rs = miStatement.executeQuery(sql);
			
			//consulta de campos sin conocer el numero de columnas
			while (rs.next()) {
				for (int i=1;i<=rs.getMetaData().getColumnCount();i++) {
					System.out.print(rs.getString(i)+"\t");
				}
				
				System.out.println("");
			
			}
			miStatement.close();
		}catch (Exception e) {
			System.out.println("noConecta");
			e.printStackTrace();
		}
	}
	
	
	public static void Insert (String sql) {
		try {
			
			Statement miStatement = conector.createStatement();
			if(miStatement.executeUpdate(sql)==1) {
				System.out.println("la consulta se ha realizado con éxito");
			}
			miStatement.close();
		}catch (Exception e) {
			System.out.println("noConecta");
			e.printStackTrace();
		}
	}
	
	
	public static void InsertarTeclado (String sql) {
		Scanner teclado= new Scanner(System.in);
		try {
			PreparedStatement sentencia=conector.prepareStatement(sql);
			System.out.println("\n Inserte codigoCliente:");
			
			int codigoCliente=teclado.nextInt();
			sentencia.setInt(1, codigoCliente);
			teclado.nextLine();
			System.out.println("\n Inserte nombreCliente:");
			String nombreCliente=teclado.nextLine();
			sentencia.setString(2, nombreCliente);
			
			System.out.println("\n Inserte nombreContacto:");
			String nombreCon=teclado.nextLine();
			sentencia.setString(3, nombreCon);
			
			System.out.println("\n Inserte apellido:");
			String apellido=teclado.nextLine();
			sentencia.setString(4, apellido);
			
			System.out.println("\n Inserte telefono:");
			String telefono=teclado.nextLine();
			sentencia.setString(5, telefono);
			
			System.out.println("\n Inserte fax:");
			String fax=teclado.nextLine();
			sentencia.setString(6, fax);
			
			System.out.println("\n Inserte direccion 1:");
			String dir1=teclado.nextLine();
			sentencia.setString(7, dir1);
			
			System.out.println("\n Inserte direccion 2:");
			String dir2=teclado.nextLine();
			sentencia.setString(8, dir2);
			
			System.out.println("\n Inserte ciudad:");
			String ciudad=teclado.nextLine();
			sentencia.setString(9, ciudad);
			
			System.out.println("\n Inserte region:");
			String region=teclado.nextLine();
			sentencia.setString(10, region);
			
			System.out.println("\n Inserte pais:");
			String pais=teclado.nextLine();
			sentencia.setString(11, pais);
			
			System.out.println("\n Inserte codigo Postal:");
			String cp=teclado.nextLine();
			sentencia.setString(12, cp);
			
			System.out.println("\n Inserte codigoResponsable:");
			int resVentas=teclado.nextInt();
			sentencia.setInt(13, resVentas);
			
			System.out.println("\n Inserte limite:");
			double limite=teclado.nextDouble();
			sentencia.setDouble(14, limite);
			
			
			int rsp=sentencia.executeUpdate();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	
	
	public static void consultasPreparadas (PreparedStatement s) {
		try {
			ResultSet rsp=s.executeQuery();
			while (rsp.next()) {
				for (int i=1;i<=rsp.getMetaData().getColumnCount();i++) {
					System.out.print(rsp.getString(i)+"\t");
				}
			
			System.out.println(" ");
		
			}
		}catch(Exception e) {
			System.out.println("noConecta");
			e.printStackTrace();
			
		}
		
	}


	
	

	

}