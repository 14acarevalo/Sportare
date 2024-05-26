package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Contabilidad.ControlEconomico;
import Usuarios.Usuario;


public class DaoControlEconomico {
	private Connection con = null;
	private static DaoControlEconomico instance = null;
	
	public DaoControlEconomico () throws SQLException {
		
		 System.out.println("Intentando establecer conexión a la base de datos...");
		    this.con = DBConection.getConnection();
		    System.out.println("Conexión establecida correctamente.");
	}

	public static DaoControlEconomico getInstance () throws SQLException {
		//se aplica el patron singelton
		if (instance == null) {
			instance = new DaoControlEconomico ();
		}
		return instance;
	}
	/**Método a través del cual se busca insertar el pago por parte del usuario, con el fin de tener un control. Este método estará presente para coger sus datos básicos, y asi tener un control economico con diferentes datos de gran relevancia
	 * @param a
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public void insertar (ControlEconomico a) throws SQLException { 
		
		/** Nombre de la base de datos
		 * Las comoas se ponen por ser un prepareStatement
		 * @return
		 * @throws SQLException
		 */
		
		String sql = "INSERT INTO controleconomico (ingresos, fechaIngreso, idUsuario, nombreUsuario, apellidosUsuario,  saldo, descripcion) VALUES (?,?,?,?,?,?,?) ";
		PreparedStatement ps = con.prepareStatement(sql); 
		
		ps.setString(1, a.getIngresos());
		ps.setString(2, a.getFechaIngreso());
		ps.setInt(3, a.getIdUsuario());
		ps.setString(4, a.getNombreUsuario());
		ps.setString(5, a.getApellidosUsuario());
		ps.setString(6, a.getSaldo());
		ps.setString(7, a.getDescripcion());
		
		int filas = ps.executeUpdate ();  
		//este es para los envios, el query para devolverme datos
		ps.close();
	}
	
	/**Método creado para obtener diferentes datos del control economico a nivel individual y con ello, llevar a cabo la posterior modificaion, pero a través de este método veremos los datos individualizados de un único pago como coordinador
	 * en este se cogen todos los datos a excepcion del idUsuario, saldo y permiso, ya que no se quieren alterar ni modificar en ningún momento.
	 * @param a
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/05/2024
	 */	
	
	public void actualizarIngreso (ControlEconomico a) throws SQLException {
		String sql = "UPDATE controleconomico SET  ingresos=?, fechaIngreso=?, idUsuario=?, nombreUsuario=?, apellidosUsuario=?,  saldo=?, descripcion=? WHERE idIngreso =?";
		PreparedStatement ps = con.prepareStatement(sql); 
		
			ps.setString(1, a.getIngresos());
		 	ps.setString(2, a.getFechaIngreso());
		    ps.setInt(3, a.getIdUsuario());
		    ps.setString(4, a.getNombreUsuario());
		    ps.setString(5, a.getApellidosUsuario());
		    ps.setString(6, a.getSaldo());
		    ps.setString(7, a.getDescripcion());
		    ps.setInt(8, a.getIdIngreso());;
		   
		
		int filas = ps.executeUpdate ();  
		ps.close();
	}
	
	/**Método creado para borrar todos los datos del pago
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 08/05/2024
	 */
	
	public void borrarIngreso (int idIngreso) throws SQLException {
		String sql = "DELETE FROM controleconomico WHERE idIngreso=?";
		PreparedStatement ps = con.prepareStatement(sql);
		  ps.setInt(1, idIngreso);
		  
		int filas = ps.executeUpdate ();  
		ps.close();
	}
	
	/** Método para listar los datos de un control economico, los cuales se asignan al id, de tal forma que se puedan ver para despues ser posteriormente modificados
	 * @param a
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 08/05/2024
	 */
	
	public ControlEconomico updateControlEconomico (int idIngreso) throws SQLException {
		String sql = "SELECT idIngreso, ingresos, fechaIngreso, idUsuario, nombreUsuario, apellidosUsuario, saldo, descripcion FROM controleconomico WHERE idIngreso=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idIngreso);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
	   	ControlEconomico a = new ControlEconomico (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));  
	   	return a;
	}
	
	/** Método para listar los datos de todos los saldos del usuario, de tal forma que se pueda tener un control y un registro de estos. 
	 * @param a
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 08/05/2024
	 */
	
	public ArrayList <ControlEconomico> listarSaldoUsuario (int idUsuario) throws SQLException {
		String sql = "SELECT idIngreso, ingresos, fechaIngreso, idUsuario, saldo, descripcion FROM controleconomico WHERE idUsuario=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idUsuario);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList <ControlEconomico> result = null;

		 while (rs.next()) {
				if (result == null) {
					result = new ArrayList<>();
				}
		
				result.add(new ControlEconomico(rs.getInt("idIngreso"), rs.getString("ingresos"), rs.getString("fechaIngreso"), rs.getInt("idUsuario"), rs.getString("saldo"), rs.getString("descripcion")));
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
	
	public String dameJsonSaldo(int idUsuario) throws SQLException {
			
			String json = "";
			Gson gson = new Gson();
			
			json = gson.toJson(this.listarSaldoUsuario(idUsuario));	
			
		return json;
				}
	
	/** Método para listar los datos de todos los saldos de todos los usuarios, de tal forma que se pueda tener un control y un registro de estos. 
	 * @param a
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 08/05/2024
	 */
	
	public ArrayList<ControlEconomico> listar () throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT  * FROM controleconomico");
		ResultSet rs = ps.executeQuery();
		
		ArrayList<ControlEconomico> result = null;
		
		/**
		 *  Con el while vamos a ir movimiento datos para saber si las casillas en el ArrayLIst son null o no
		 */
		
		 while (rs.next()) {
			if (result == null) {
				result = new ArrayList<>();
			}
			
			result.add(new ControlEconomico(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8)));

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
	
	public String dameJson() throws SQLException {
		
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.listar());	
		
	return json;
			}
	
	/**Método a través del cual selecciono el saldo del usuario según su idUsuario para que pueda o no pueda reservar la instalación deportiva
	 * si este es > 0 podrá reservala, tras revisar esto, el coordinador eliminará/modificará sus creditos para añadir en la descripción en que lo ha gastado
	 * @param idUsuario
	 * @return
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 16/05/2024
	 */
	
	public String obtenerSaldoUsuario(int idUsuario) throws SQLException {
	    String saldo = null;

	    String sql = "SELECT saldo FROM controleconomico WHERE idUsuario = ?";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, idUsuario);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                saldo = rs.getString("saldo");
	            }
	        }
	    }
	    return saldo;
	}

}
