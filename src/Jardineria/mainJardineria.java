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
		
		try {
		
				
		ConexionJardineria cn=new ConexionJardineria();
		String sql;
		
		
		System.out.println("MENU");
		System.out.println("1. Consultar los productos que tenemos");
		System.out.println("2. Consultar los pedidos y el detalle de pedido segun el cliente por teclado");
		System.out.println("3. Insertar en clientes");
		System.out.println("4. Modificar en clientes");
		System.out.println("5. Borrar clientes");
		System.out.println("6. Salir");
		System.out.println("Introduzca opcion");
		
		int opcion=teclado.nextInt();
			do {
				switch(opcion) {
				case 1:
					
					sql="select * from gamasproductos";
					//ConexionJardineria.Select(sql);
					ConexionJardineria.mostrar(sql);
					
			       
					break;
				case 2:
					
					sql="select * from clientes";
					ConexionJardineria.Select(sql);
				
					break;
				case 3:
					
					ConexionJardineria.Insertar();
					
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
