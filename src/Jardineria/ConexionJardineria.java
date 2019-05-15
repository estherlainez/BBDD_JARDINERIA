package Jardineria;
import java.io.*;
import java.sql.*;
import java.util.*;
/*
 * MUY IMPORTANTEEEEEEE
 * VERIFICAR LA RUTA DONDE SE ENCUENTRA EL ARCHIVO	
 * Y SI NO NO CONECTA......	
 */
public class ConexionJardineria {
	static String servidor;
	static String bbdd;
	static String usuario;
	static String password;
	
	static Connection conector;
	
	public ConexionJardineria (){
		try {
			this.leerArchivo();
			conector= DriverManager.getConnection(servidor+bbdd,usuario,password);
		
		}catch(Exception e) {
			System.out.println("no Conecta");
			e.printStackTrace();
		}
	}	
	
	
	
	
	public static void leerArchivo(){
		try {
			/*
			 * MUY IMPORTANTEEEEEEE
			 * VERIFICAR LA RUTA DONDE SE ENCUENTRA EL ARCHIVO	
			 * Y SI NO NO CONECTA......	
			 */
			Properties propiedades = new Properties ();
			propiedades.load(new FileInputStream
			
			("C:/Users/Esther/eclipse-workspace/UD12PR_BBDD_JARDINERIA_MYSQL/src/propiedadesJardineria/configuration.properties"));
			servidor = propiedades.getProperty("dataBaseServer");
			bbdd = propiedades.getProperty("dataBaseCatalog");
			usuario = propiedades.getProperty("dataBaseUser");
			password=propiedades.getProperty("dataBasePassword");
			
			/*Imprimir los valores*/
			System.out.println("server: "+servidor+ "\n"+"bbdd: " +bbdd+ "\n"+"Usuario: "+usuario+ "\n"+"Password: "+password);
			
		}catch(FileNotFoundException e) {
			System.out.println("Error, el archivo no existe");
		}catch(IOException e) {
			System.out.println("Error, no se puede leer el archivo");
		}
		
	}	
	
	
	
	public static void Select (String sql) {
		try {
		PreparedStatement ms = conector.prepareStatement(sql);
		ResultSet rs = ms.executeQuery(sql);
		
		
		while(rs.next()) {
			for(int i=1; i<=rs.getMetaData().getColumnCount();i++) {
				System.out.print(rs.getString(i)+"\t");
			}     
			System.out.println(" ");
			
		}
		ms.close();
		}catch(Exception e) {
			System.out.println("no Conecta");
			e.printStackTrace();
		}
	}
	
	
	public static void mostrar(String sql) throws SQLException {
		conector= DriverManager.getConnection(servidor+bbdd,usuario,password);
		Statement st;
		ResultSet rs;
		try {
			st= conector.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				for(int i=1; i<=rs.getMetaData().getColumnCount();i++) {
					System.out.print(rs.getString(i)+"\t");
				}     
				System.out.println(" ");
				
			}
			
			rs.close();
			st.close();
			conector.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	


	public static void Insertar () throws SQLException {
		conector= DriverManager.getConnection(servidor+bbdd,usuario,password);
		Scanner teclado=new Scanner(System.in);
		
		
		System.out.println("Introduzca CodigoCliente" );
		int codCli=teclado.nextInt();
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
		System.out.println("Introduzca direccion" );
		String direccion=teclado.nextLine();
		
		Statement ms;
		
		String sql = "INSERT INTO clientes('CodigoCliente','NombreCliente',,'NombreContacto','ApellidoContacto','Telefono','Fax','LineaDireccion1') "
				+ "VALUES ( codCli, '"+nombre+"','"+nombreCon+"', '"+apellido+"','"+telefono+"','"+fax+"', '"+direccion+"')";
		try {
			 ms= conector.createStatement();
			
			 ms.executeUpdate(sql);
			 System.out.println(sql+ "\n"); 
			 ms.close();
			 conector.close();
		}catch(SQLException e) {
			System.out.println("no Conecta");
			e.printStackTrace();
		}
	}
	
	
	public static void modificar() throws SQLException {
		conector= DriverManager.getConnection(servidor+bbdd,usuario,password);
		Scanner teclado= new Scanner(System.in);
		Statement st;
		System.out.println("Introduzca el NombreCliente de la fila a modificar");
		String nombreModificar= teclado.nextLine();
		System.out.println("Introduzca CodigoCliente" );
		int codCli=teclado.nextInt();
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
		System.out.println("Introduzca direccion" );
		String direccion=teclado.nextLine();
		
		String sql="update clientes set CodigoCliente=codCli, '"+nombre+"','"+nombreCon+"', '"+apellido+"','"+telefono+"','"+fax+"', '"+direccion+"' where NombreCliente="+nombreModificar;
		try {
			st=conector.createStatement();
			int confirmar=st.executeUpdate(sql);
			if(confirmar==1) {
				System.out.println("Modificado con exito");
			}else {
				System.out.println("No Modificado....");
			}
			st.close();
			conector.close();
		}catch(SQLException e) {
			e.getMessage();
		}catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}
		
		
	}
	public static void  borrarDatos() throws SQLException {
		Scanner teclado=new Scanner(System.in);
		conector= DriverManager.getConnection(servidor+bbdd,usuario,password);
		
		System.out.println("Inserte el nombre del cliente a borrar");
		String nombreBorrar=teclado.nextLine();
		
		Statement st;
		String sql= "DELETE FROM CLIENTES WHERE NombreContacto= "+nombreBorrar+"";
		try {
			st= conector.createStatement();
			
			st.executeUpdate(sql);
			int confirmar = st.executeUpdate(sql);
			if(confirmar==1) {
				System.out.println("Sus datos se actualizaron en la base de datos con exito");
			}else {
				System.out.println("El registro no se pudo eliminar");
			}
			
			st.close();
			conector.close();
		}catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}
		
		
	}
	
}
