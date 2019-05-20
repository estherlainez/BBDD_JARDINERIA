package JardineriaCorregido;


import java.sql.*;
import java.util.*;

public class pruebaJardineriaPaqui {

	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente
				
				Conexion cn = new Conexion();
				String sql;
					
					Scanner teclado = new Scanner (System.in);
					int opcion;
					do {
						System.out.println("Menu: ");
						System.out.println("1. Consultar los productos que tenemos");
						System.out.println("2. Consultar los clientes que tenemos");
						System.out.println("3. Consultar los pedidos y el detalle de pedido segun el cliente leido por teclado");
						System.out.println("4. Insertar en clientes");
						System.out.println("5. Modificar en clientes");
						System.out.println("6. Borrar clientes");
						System.out.println("7. Salir");
						System.out.print("Introduzca la opcion elegida: ");
						opcion=teclado.nextInt();
						switch (opcion) {
						
						case 1:{
							sql = "select * from productos";
							Conexion.Select(sql);
							
							
							break;
						}
						case 2:{
							sql = "select * from clientes";
							Conexion.Select(sql);
							
							
							break;
						}
						case 3:{
							
							
							sql="select pedidos.codigopedido,pedidos.fechapedido,pedidos.comentarios,pedidos.codigocliente,	clientes.nombrecliente,	detallepedidos.codigoproducto ,	productos.nombre,	detallepedidos.cantidad,detallepedidos.preciounidad	from pedidos, detallepedidos, productos, clientes where pedidos.codigopedido=detallepedidos.codigopedido AND detallepedidos.codigoproducto=productos.codigoproducto AND pedidos.codigocliente=?";
							try {
								PreparedStatement sentencia= cn.conector.prepareStatement (sql);
								System.out.println("introduce el codigo del cliente que queremos mostrar");
								int codigo=teclado.nextInt();
								sentencia.setInt (1, codigo);
							
								Conexion.consultasPreparadas(sentencia);
								
							}catch (Exception e) {
								System.out.println("error");
								e.printStackTrace();
							}
							
							break;
						}
						case 4:{
							sql="INSERT INTO clientes (CodigoCliente, NombreCliente,NombreContacto, ApellidoContacto, Telefono, Fax, LineaDireccion1, LineaDireccion2, Ciudad, Region, Pais, CodigoPostal, CodigoEmpleadoRepVentas,LimiteCredito) VALUES (81,'Jardines Lainez', 'Esther', 'Lainez', '666558844', '976554455', 'MI CASA, 12', NULL, 'TARAZONA', 'ARAGON', 'ESPAÑA', '50500', '1', '10');";
							Conexion.Insert(sql);
							Conexion.Select("select * from clientes");
							
							break;
						}
							
						case 5:{
							System.out.println("introduce el codigo del cliente que queremos modificar");
							int codigo=teclado.nextInt();
							sql="UPDATE clientes SET NombreCliente = 'pruebamodificada' WHERE clientes.CodigoCliente = "+codigo+";"; 
							Conexion.Insert(sql);
							Conexion.Select("select * from clientes");	
							break;
						}
						
						case 6:{
							System.out.println("introduce el codigo del cliente que queremos eliminar");
							int codigo=teclado.nextInt();
							sql="DELETE FROM clientes WHERE clientes.CodigoCliente = "+codigo+";"; 
							Conexion.Insert(sql);
							Conexion.Select("select * from clientes");	
							break;
						}
						
						case 7:{
							try {
								cn.conector.close();
							}catch (Exception e) {
								e.printStackTrace();
							}
						break;
						}
						
						default: {
							System.out.println("Opcion no valida. Vuelva a leer");
						}
						}
					}while(opcion!=7);
					
				
			}

	}
