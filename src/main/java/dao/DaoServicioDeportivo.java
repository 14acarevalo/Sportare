package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Contabilidad.ControlEconomico;
import ServiciosDeportivos.ServicioDeportivo;
import Usuarios.Usuario;


public class DaoServicioDeportivo {
		
		private Connection con = null;
		private static DaoServicioDeportivo instance = null;
		
		public DaoServicioDeportivo () throws SQLException {
			
			 System.out.println("Intentando establecer conexión a la base de datos...");
			    this.con = DBConection.getConnection();
			    System.out.println("Conexión establecida correctamente.");
		}

		public static DaoServicioDeportivo getInstance () throws SQLException {
			//se aplica el patron singelton
			if (instance == null) {
				instance = new DaoServicioDeportivo ();
			}
			return instance;
			
		}
		/**Método a través del cual se busca insertar diferentes servicios deportivos para la entidad con el fin de tener un control. Este método estará presente para coger los datos básicos del servicio y asi tener un control
		 * @param a
		 * @throws SQLException
		 * @author: Alberto Campanero Arevalo
		 * @version: 10/04/2024
		 */
		
		public void insertar (ServicioDeportivo a) throws SQLException { //el nombre de Usuario a es con el que jugamos y cambiamos
			
			String sql = "INSERT INTO servicio (nombre, horaIniciacion, minInicio, horaFinalizacion, minFinal, dia, mes, idUsuario) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql); 
			
			
			ps.setString(1, a.getNombre());
			ps.setString(2, a.getHoraIniciacion());
			ps.setString(3, a.getMinInicio());
			ps.setString(4, a.getHoraFinalizacion());
			ps.setString(5, a.getMinFinal());
			ps.setString(6, a.getDia());
			ps.setString(7, a.getMes());
			ps.setInt(8, a.getIdUsuario());
			
			int filas = ps.executeUpdate ();  
			ps.close();
			
		}
		
		/** Esto metodo me ayudará a verificar si existe disponibilidad respecto al dia, hora y mes escogido por el usuario
		 * @return
		 * @throws SQLException
		 * @author: Alberto Campanero Arevalo
		 * @version: 10/05/2024
		 */
		
		public boolean verificarDisponibilidad (String nombre, String horaIniciacion, String minInicio, String horaFinalizacion, String minFinal, String dia, String mes, int idUsuario) throws SQLException {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM servicio WHERE nombre = ? AND horaIniciacion = ? AND minInicio = ? AND horaFinalizacion = ? AND minFinal = ? AND dia = ? AND mes = ?");
		    ps.setString(1, nombre);
		    ps.setString(2, horaIniciacion);
		    ps.setString(3, minInicio);
		    ps.setString(4, horaFinalizacion);
		    ps.setString(5, minFinal);
		    ps.setString(6, dia);
		    ps.setString(7, mes);
		 
		    ResultSet rs = ps.executeQuery();
		    boolean disponible = !rs.next();
		    rs.close();
		    ps.close();

		    return disponible;
			}
		
		/** Esto metodo me ayudará a verificar si existe disponibilidad respecto al dia, hora y mes escogido por el usuario, teniendo en cuenta el aforo
		 * @return
		 * @throws SQLException
		 * @author: Alberto Campanero Arevalo
		 * @version: 10/05/2024
		 */
	
		public boolean verificarDisponibilidadAforo(String nombre, String horaIniciacion, String minInicio, String horaFinalizacion, String minFinal, String dia, String mes, int idUsuario) throws SQLException {
		    int aforoActual = 0;
		    
		    PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS aforo_actual FROM servicio WHERE nombre = ? AND horaIniciacion = ? AND minInicio = ? AND horaFinalizacion = ? AND minFinal = ? AND dia = ? AND mes = ?");
		    ps.setString(1, nombre);
		    ps.setString(2, horaIniciacion);
		    ps.setString(3, minInicio);
		    ps.setString(4, horaFinalizacion);
		    ps.setString(5, minFinal);
		    ps.setString(6, dia);
		    ps.setString(7, mes);
		    ResultSet rs = ps.executeQuery();

		    if (rs.next()) {
		        aforoActual = rs.getInt("aforo_actual");
		    }

		    // Con esto busco verificar si el aforo actual supera el límite
		    final int TOPE_AFORO = 2; // Definir el tope máximo de aforo
		    return aforoActual < TOPE_AFORO;
		}

		/**Método creado para obtener diferentes datos de los servicios a nivel individual y con ello, llevar a cabo la posterior modificaion, pero a través de este método veremos los datos individualizados de un único servicio como coordinador
		 * en este se cogen todos los datos a excepcion del idUsuario, saldo y permiso, ya que no se quieren alterar ni modificar en ningún momento.
		 * @param a
		 * @throws SQLException
		 * @author: Alberto Campanero Arevalo
		 * @version: 10/05/2024
		 */	
		public void actualizarServicio (ServicioDeportivo a) throws SQLException {
			String sql = "UPDATE servicio SET  nombre=?, horaIniciacion=?, minInicio=?, horaFinalizacion=?, minFinal=?, dia=?, mes=? WHERE idServicio =?";
			PreparedStatement ps = con.prepareStatement(sql); 
			
			 	ps.setString(1, a.getNombre());
			    ps.setString(2, a.getHoraIniciacion());
			    ps.setString(3, a.getMinInicio());
			    ps.setString(4, a.getHoraFinalizacion());
			    ps.setString(5, a.getMinFinal());
			    ps.setString(6, a.getDia());
			    ps.setString(7, a.getMes());
			    ps.setInt(8, a.getIdServicio());		  
			    
			
			int filas = ps.executeUpdate ();  
			ps.close();
		
		}
		
		/**Método creado para borrar todos los datos del servicio 
		 * @throws SQLException
		 * @author: Alberto Campanero Arevalo
		 * @version: 08/05/2024
		 */
		
		public void borrarServicio (int idServicio) throws SQLException {
			String sql = "DELETE FROM servicio WHERE idServicio=?";
			PreparedStatement ps = con.prepareStatement(sql);
			  ps.setInt(1, idServicio);
			  
			  int filas = ps.executeUpdate ();  
				//este es para los envios, el query para devolverme datos
				ps.close();
				con.close();
		}
		
		/** Método para listar los datos de un servicio especifico, los cuales se asignan al idServicio, de tal forma que se puedan ver para despues ser posteriormente modificados
		 * @param a
		 * @throws SQLException
		 * @author: Alberto Campanero Arevalo
		 * @version: 08/05/2024
		 */
		
		public ServicioDeportivo updateServicio (int idServicio) throws SQLException {
			String sql = "SELECT idServicio, nombre, horaIniciacion, minInicio, horaFinalizacion, minFinal, dia, mes FROM servicio WHERE idServicio=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idServicio);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
		   	ServicioDeportivo a = new ServicioDeportivo (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));

		   	return a;
		}
		
		/** Método para listar los datos de todos los servicios deportivos del usuario, de tal forma que se pueda tener un control y un registro de estos. 
		 * @param a
		 * @throws SQLException
		 * @author: Alberto Campanero Arevalo
		 * @version: 08/05/2024
		 */
		
		public ArrayList <ServicioDeportivo> listarServicioUsuario (int idUsuario) throws SQLException {
			String sql = "SELECT idServicio, nombre, horaIniciacion, minInicio, horaFinalizacion, minFinal, dia, mes, idUsuario FROM servicio WHERE idUsuario=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			
			ResultSet rs = ps.executeQuery();
			
			ArrayList <ServicioDeportivo> result = null;

			 while (rs.next()) {
					if (result == null) {
						result = new ArrayList<>();
					}
			
					result.add(new ServicioDeportivo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9)));
			 }
			 return result;
		}
		
		/**Este método toma un objeto Java 
		 * y lo convierte en una cadena JSON utilizando la biblioteca Gson insertada en este programa. 
		 * Nos será de gran utilidad para enviar objetos Java y almacenarlos en formato JSON.
		 * @return
		 *  @author: Alberto Campanero Arevalo
		 * @version: 4/05/2024
		 */
		
		public String dameJsonServicioUsuario(int idUsuario) throws SQLException {
				
				String json = "";
				Gson gson = new Gson();
				
				json = gson.toJson(this.listarServicioUsuario(idUsuario));	
				
			return json;
					}
		
		/** Método para listar los datos de todos los servicios deportivos de la entidad, de tal forma que se pueda tener un control y un registro de estos. 
		 * @param a
		 * @throws SQLException
		 * @author: Alberto Campanero Arevalo
		 * @version: 08/05/2024
		 */
			
		public ArrayList<ServicioDeportivo> listarServicio () throws SQLException {
			
			PreparedStatement ps = con.prepareStatement("SELECT idServicio, nombre, horaIniciacion, minInicio, horaFinalizacion, minFinal, dia, mes, idUsuario FROM servicio");
			ResultSet rs = ps.executeQuery();
			
			ArrayList<ServicioDeportivo> result = null;
			
			 while (rs.next()) {
				if (result == null) {
					result = new ArrayList<>();
				}
				
				result.add(new ServicioDeportivo (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9)));
			}
			
			return result;
		}
		
		/**Este método toma un objeto Java 
		 * y lo convierte en una cadena JSON utilizando la biblioteca Gson insertada en este programa. 
		 * Nos será de gran utilidad para enviar objetos Java y almacenarlos en formato JSON.
		 * @return
		 *  @author: Alberto Campanero Arevalo
		 * @version: 04/05/2024
		 */
		
		public String dameJsonServicio() throws SQLException {
			
			String json = "";
			Gson gson = new Gson();
			
			json = gson.toJson(this.listarServicio());	
			
		return json;
				}
		
		}
	
	
	
	

