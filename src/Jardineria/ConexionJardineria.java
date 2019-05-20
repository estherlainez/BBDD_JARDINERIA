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
			//ruta en el pc de clase
			("C:/Users/IFC/git/BBDD_JARDINERIA/src/propiedadesJardineria/configuration.properties"));
			//Ruta en el pc de casa
			//("C:/Users/Esther/eclipse-workspace/UD12PR_BBDD_JARDINERIA_MYSQL/src/propiedadesJardineria/configuration.properties"));
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
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void InsertarRegistro (String sql) {
		try {
			
			Statement miStatement = conector.createStatement();
			miStatement.executeUpdate(sql);
			
		}catch(Exception e) {
			System.out.println("No es posible conectar");
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
		
	
		String sql="INSERT INTO clientes (CodigoCliente, NombreCliente,NombreContacto, ApellidoContacto, Telefono, Fax, LineaDireccion1, LineaDireccion2, Ciudad, Region, Pais, CodigoPostal, CodigoEmpleadoRepVentas,LimiteCredito) VALUES (codigo,'nombre', 'nombreCon', 'apellido', 'telefono', 'fax', 'direccion', direccion2, 'ciudad', 'region', 'pais', 'cp', 'empVentas', 'limiteCredito');";
		
		
		Statement ms;
		
		System.out.println(sql);
		
		try {
			 ms= conector.createStatement();
			
			 ms.executeUpdate(sql);
			 System.out.println(sql+ "\n"); 
			
		}catch(SQLException e) {
			System.out.println("no Conecta");
			e.printStackTrace();
		}
	}
	
	
	public static void modificar()  {
		
		Scanner teclado= new Scanner(System.in);
		Statement st;
		System.out.println("Introduzca el CodigoCliente de la fila a modificar");
		int codigo= teclado.nextInt();
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
		
		String sql="update clientes Set NombreCliente= '"+nombre+"',NombreContacto= '"+nombreCon+"',ApellidoContacto= '"+apellido+"',Telefono='"+telefono+"',Fax='"+fax+"', Direccion'"+direccion+" where CodigoCliente='"+codigo+"'";
		
		System.out.println(sql+ "\n"); 
		try {
			st=conector.createStatement();
			int confirmar=st.executeUpdate(sql);
			if(confirmar==1) {
				System.out.println("Modificado con exito");
			}else {
				System.out.println("No Modificado....");
			}
			
		}catch(SQLException e) {
			e.getMessage();
		}catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}
		
		
	}
	public static void  borrarDatos() {
		Scanner teclado=new Scanner(System.in);
		
		
		System.out.println("Inserte el codigo del cliente a borrar");
		int codigoBorrar = teclado.nextInt();
		
		Statement st;
		String sql= "DELETE FROM CLIENTES WHERE CodigoCliente= "+codigoBorrar+"";
		try {
			st= conector.createStatement();
			
			st.executeUpdate(sql);
			int confirmar = st.executeUpdate(sql);
			if(confirmar==1) {
				System.out.println("Sus datos se actualizaron en la base de datos con exito");
			}else {
				System.out.println("El registro no se pudo eliminar");
			}
			
			
		}catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}
		
		
	}
	
}
