package Jardineria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class mainJardineria {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner teclado= new Scanner(System.in);

		int opcion=0;
		
		try {
		
				
		ConexionJardineria cn=new ConexionJardineria();
		String sql;
		
		do {
		System.out.println("MENU");
		System.out.println("1. Consultar los productos que tenemos");
		System.out.println("2. Consultar los pedidos y el detalle de pedido segun el cliente por teclado");
		System.out.println("3. Insertar en clientes");
		System.out.println("4. Modificar en clientes");
		System.out.println("5. Borrar clientes");
		System.out.println("6. Salir");
		System.out.println("Introduzca opcion");
		opcion=teclado.nextInt();
			
				switch(opcion) {
				case 1:
					
					sql="select * from productos";
					//ConexionJardineria.Select(sql);
					ConexionJardineria.mostrar(sql);
					
			       
					break;
				case 2:
					
					sql="select * from clientes";
					ConexionJardineria.Select(sql);
				
					break;
				case 3:
					sql= "INSERT INTO oficinas VALUES('BCN-ES','TARAZONA','ESPAÑA','ARAGON','50500','653412345','AVENIDA DE LA PAZ','TERRESA CAJAL';)";
					ConexionJardineria.InsertarRegistro(sql);
					
					break;
				case 4:
					ConexionJardineria.modificar();
					break;
				case 5:
					
					ConexionJardineria.borrarDatos();
					
					break;
				case 6:
					
					System.out.println("6. SALIR");
					System.out.println("Que tengas buen dia...");
					try {
						cn.conector.close();
					}catch(Exception e) {
						System.out.println("no Conecta");
						e.printStackTrace();
					}
					
					break;
				
				}
				
				
				
			}while(opcion!=6);
		
	
		}catch(Exception e) {
			System.out.println("Error, fallo conexion");
			e.printStackTrace();
		}
	}

}
