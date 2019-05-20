package JardineriaEnCasa;

import java.io.*;
import java.sql.*;
import java.util.*;

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
				//propiedades.load(new FileInputStream("C:/Users/Esther/git/BBDD_JARDINERIA/src/configuration/properties"));
			
				propiedades.load(new FileInputStream
						("C:/Users/IFC/git/BBDD_JARDINERIA/src/configuration/properties"));
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
	
	
	
public static void InsertarTeclado ()  {
		
		Scanner teclado=new Scanner(System.in);
		
	 	System.out.println("Introduzca CodigoCliente" );
		int codigo=teclado.nextInt();
		teclado.nextLine();
		System.out.println("Introduzca NombreCliente" );
		String nombre=teclado.nextLine();
		System.out.println("Introduzca NombreContacto" );
		String nombreCon=teclado.nextLine();
		System.out.println("Introduzca ApellidoContacto" );
		String apellido=teclado.nextLine();
		System.out.println("Introduzca Telefono" );
		String telefono=teclado.nextLine();
		System.out.println("Introduzca Fax" );
		String fax=teclado.nextLine();
		System.out.println("Introduzca direccion1" );
		String direccion=teclado.nextLine();
		System.out.println("Introduzca direccion2" );
		String direccion2=teclado.nextLine();
		System.out.println("Introduzca ciudad" );
		String ciudad=teclado.nextLine();
		System.out.println("Introduzca region" );
		String region=teclado.nextLine();
		System.out.println("Introduzca pais" );
		String pais=teclado.nextLine();
		System.out.println("Introduzca codigo postal" );
		String cp=teclado.nextLine();
		System.out.println("Introduzca codigo empleado de ventas" );
		int empVentas=teclado.nextInt();
		System.out.println("Introduzca limite credito" );
		double limiteCredito=teclado.nextDouble();
		
	
		String sql="INSERT INTO clientes (CodigoCliente, NombreCliente,NombreContacto, ApellidoContacto, Telefono, Fax, LineaDireccion1, LineaDireccion2, Ciudad, Region, Pais, CodigoPostal, CodigoEmpleadoRepVentas,LimiteCredito) VALUES (codigo,'"+nombre+"', '"+nombreCon+"', '"+apellido+"', '"+telefono+"', '"+fax+"', '"+direccion+"', '"+direccion2+"', '"+ciudad+"', '"+region+"', '"+pais+"', '"+cp+"', '"+empVentas+"', '"+limiteCredito+")";
		                                                                                                                                                                                                                                                                                                                                                                                      
	
		
		System.out.println(sql);
		try {
			Statement miStatement = conector.createStatement();
			if(miStatement.executeUpdate(sql)==1) {
				System.out.println("la consulta se ha realizado con éxito");
			}
			miStatement.close();
		}catch(SQLException e) {
			System.out.println("no Conecta");
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