package JardineriaEnCasa;

import java.sql.*;
import java.util.*;

public class pruebaJardineriaEnCasa {

	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente
				
				Conexion2 cn = new Conexion2();
				String sql;
					
					Scanner teclado = new Scanner (System.in);
					int opcion;
					do {
						System.out.println("Menu: ");
						System.out.println("1. Consultar las oficinas que tenemos");
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
							sql = "select * from oficinas";
							Conexion2.Select(sql);
							
							
							break;
						}
						case 2:{
							sql = "select * from clientes";
							Conexion2.Select(sql);
							
							
							break;
						}
						case 3:{
							
							
							sql="select pedidos.codigopedido,pedidos.fechapedido,pedidos.comentarios,pedidos.codigocliente,	clientes.nombrecliente,	detallepedidos.codigoproducto ,	productos.nombre,	detallepedidos.cantidad,detallepedidos.preciounidad	from pedidos, detallepedidos, productos, clientes where pedidos.codigopedido=detallepedidos.codigopedido AND detallepedidos.codigoproducto=productos.codigoproducto AND pedidos.codigocliente=?";
							try {
								PreparedStatement sentencia= cn.conector.prepareStatement (sql);
								System.out.println("introduce el codigo del cliente que queremos mostrar");
								int codigo=teclado.nextInt();
								sentencia.setInt (1, codigo);
							
								Conexion2.consultasPreparadas(sentencia);
								
							}catch (Exception e) {
								System.out.println("error");
								e.printStackTrace();
							}
							
							break;
						}
						case 4:{
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
							
							
							sql = "INSERT INTO clientes (CodigoCliente, NombreCliente,NombreContacto, ApellidoContacto, Telefono, Fax, LineaDireccion1, LineaDireccion2, Ciudad, Region, Pais, CodigoPostal, CodigoEmpleadoRepVentas,LimiteCredito) values (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
						
							//sql="INSERT INTO clientes (CodigoCliente, NombreCliente,NombreContacto, ApellidoContacto, Telefono, Fax, LineaDireccion1, LineaDireccion2, Ciudad, Region, Pais, CodigoPostal, CodigoEmpleadoRepVentas,LimiteCredito) VALUES (82,'Viveros Lapuente', 'Carlos', 'Lapuente', '676643567', '976867654', 'Reino de Aragon 12', 'Teresa Cajal', 'TARAZONA', 'ARAGON', 'ESPAÑA', '50500', '12', '1200');";
							Conexion2.Insert(sql);
							Conexion2.Select("select * from clientes");
							
							break;
						}
							
						case 5:{
							System.out.println("introduce el codigo del cliente que queremos modificar");
							int codigo=teclado.nextInt();
							sql="UPDATE clientes SET NombreCliente = 'pruebamodificada' WHERE clientes.CodigoCliente = "+codigo+";"; 
							Conexion2.Insert(sql);
							Conexion2.Select("select * from clientes");	
							break;
						}
						
						case 6:{
							System.out.println("introduce el codigo del cliente que queremos borrar");
							int codigo=teclado.nextInt();
							sql="DELETE FROM clientes WHERE clientes.CodigoCliente = "+codigo+";"; 
							Conexion2.Insert(sql);
							Conexion2.Select("select * from clientes");	
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
