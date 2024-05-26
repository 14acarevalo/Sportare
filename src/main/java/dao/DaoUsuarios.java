package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Contabilidad.ControlEconomico;

//import com.google.gson.Gson;

import Usuarios.Usuario;

public class DaoUsuarios {
	
	private Connection con = null;
	private static DaoUsuarios instance = null;
	
	public DaoUsuarios () throws SQLException {
		
		 System.out.println("Intentando establecer conexión a la base de datos...");
		    this.con = DBConection.getConnection();
		    System.out.println("Conexión establecida correctamente.");
	}

	public static DaoUsuarios getInstance () throws SQLException {
		//se aplica el patron singelton
		if (instance == null) {
			instance = new DaoUsuarios ();
		}
		return instance;
	}
	
	/**Método a través del cual se busca insertar diferentes atributos del usuario, con el fin de tener un control. Este método estará presente para coger sus datos básicos, a la vez que el permio para que pueda realizar uno u otras opciones
	 * @param a
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public void insertar (Usuario a) throws SQLException { 
		
		String sql = "INSERT INTO usuario (nombre, apellidos, correoElectronico, password, fechaNacimiento, dni, nacionalidad, ciudad, calle, direccion, codigoPostal, telefono, genero, permiso) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement ps = con.prepareStatement(sql); 
		
		ps.setString(1, a.getNombre());
		ps.setString(2, a.getApellidos());
		ps.setString(3, a.getCorreoElectronico());
		ps.setString(4, a.getPassword());
		
		ps.setString(5, a.getFechaNacimiento());
		ps.setString(6, a.getDni());
		ps.setString(7, a.getNacionalidad());
		ps.setString(8, a.getCiudad());
		ps.setString(9, a.getCalle());
		ps.setString(10, a.getDireccion());
		ps.setString(11, a.getCodigoPostal());
		ps.setString(12, a.getTelefono());
		ps.setString(13, a.getGenero());
		ps.setInt(14, a.getPermiso());
		
		int filas = ps.executeUpdate ();  
		
		ps.close();
	}
	
	/**Método creado para obtener diferentes datos de los usuarios a nivel individual y con ello, llevar a cabo la posterior modificaion, pero a través de este método veremos los datos individualizados de un único usuario como coordinador
	 * en este se cogen todos los datos a excepcion del idUsuario, saldo y permiso, ya que no se quieren alterar ni modificar en ningún momento.
	 * @param a
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	public void actualizarUsuario (Usuario a) throws SQLException {
		String sql = "UPDATE usuario SET  nombre=?, apellidos=?, correoElectronico=?, fechaNacimiento=?, dni=?, nacionalidad=?, ciudad=?, direccion=?, calle=?, telefono=?, codigoPostal=?, genero=? WHERE idUsuario =?";
		PreparedStatement ps = con.prepareStatement(sql); 
		
		 	ps.setString(1, a.getNombre());
		    ps.setString(2, a.getApellidos());
		    ps.setString(3, a.getCorreoElectronico());
		    ps.setString(4, a.getFechaNacimiento());
		    ps.setString(5, a.getDni());
		    ps.setString(6, a.getNacionalidad());
		    ps.setString(7, a.getCiudad());
		    ps.setString(8, a.getDireccion());
		    ps.setString(9, a.getCalle());
		    ps.setString(10, a.getTelefono());
		    ps.setString(11, a.getCodigoPostal());
		    ps.setString(12, a.getGenero());
		    ps.setInt(13, a.getIdUsuario());
		
		int filas = ps.executeUpdate ();  
		ps.close();
	}
	
	/**Método creado para borrar todos los datos de los usuarios 
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 08/05/2024
	 */
	public void borrarUsuario (int idUsuario) throws SQLException {
		String sql = "DELETE FROM usuario WHERE idUsuario=?";
		PreparedStatement ps = con.prepareStatement(sql);
		  ps.setInt(1, idUsuario);
		  
		int filas = ps.executeUpdate ();  
		ps.close();
	}
	
	/** Método para listar los datos de un usuario especifico, los cuales se asignan al idUsuario, de tal forma que se puedan ver para despues ser posteriormente modificados
	 * @param a
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 08/05/2024
	 */
	
	public Usuario update (int idUsuario) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE idUsuario=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idUsuario);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
		
	   	Usuario aux = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15));

	
		    return aux;
		} else {
		    return null; 
		}
		
	}
	
	/** Método a través del cual se busca iniciar el acceso al perfil teniendo en cuenta el id del usuario, el nombre, contraseña y su permiso
	 * Se introduce el id con el objetivo de aumentar la seguridad a la hora de entrar dentro del servicio deportivo, de tal forma, que cada usuario se consciente de esto.
	 * @param a
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 08/05/2024
	 */
	
	public Usuario logeando (Usuario a, int idUsuario, String nombre, String password, int permiso) throws SQLException {
		
		String sql = "SELECT * FROM usuario WHERE idUsuario=? AND nombre=? AND correoElectronico=? AND password=? AND permiso=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, a.getIdUsuario());
		ps.setString(2, a.getNombre());
		
		
		ps.setString(3, a.getCorreoElectronico());
		ps.setString(4, a.getPassword());
		ps.setInt(5, a.getPermiso());
		System.out.println("1");

		ResultSet rs = ps.executeQuery();
		
		Usuario aux = null;
		
		System.out.println("2");
		if(rs.next()) {
		System.out.println("3");
		System.out.println(rs.getInt(1)+rs.getString(2)+ rs.getString(3) +  rs.getString(4) +  rs.getString(5) + rs.getString(6) + rs.getString(7) + rs.getString(8) + rs.getString(9) + rs.getString(10) + rs.getString(11) + rs.getString(12) + rs.getString(13)  );
		System.out.println(rs.getString(14));
		
		System.out.println(rs.getInt(1)+rs.getString(2)+ rs.getString(3) +  rs.getString(4) +  rs.getString(5) + rs.getString(6) + rs.getString(7) + rs.getString(8) + rs.getString(9) + rs.getString(10) + rs.getString(11) + rs.getString(12) + rs.getString(13)  + rs.getString(15) + rs.getInt(16) );
	     aux = new Usuario (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(15), rs.getInt(16));
	   
	    return aux;
		
		} else {
	        
	        return null;
	    }
		
	}
	
	/** Método a través del cual se busca que nos aparezca desde el panel del coordinador todos y cada uno de los datos de los usuarios, de tal forma, que el coordinador pueda identificarlos según sus características
	 * @param a
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 08/05/2024
	 */
	
	public ArrayList<Usuario> listar () throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT idUsuario, nombre, apellidos, correoElectronico, password, fechaNacimiento, dni, nacionalidad, ciudad, direccion, calle, telefono, codigoPostal, genero, saldo, permiso FROM usuario");
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Usuario> result = null;
		
		 while (rs.next()) {
			if (result == null) {
				result = new ArrayList<>();
			}
			
			result.add(new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getInt(16)));
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
	
	/** Método similar al anterior, pero en este caso lo que se busca es que el usuario pueda listar sus datos según su id, en este caso sólo se pretende sacar los datos de un usuario.
	 * @param a
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 08/05/2024
	 */
	
	public Usuario obtenerUsuarioPorId(int idUsuario) throws SQLException {
	    PreparedStatement ps = con.prepareStatement("SELECT idUsuario, nombre, apellidos, correoElectronico, fechaNacimiento, dni, nacionalidad, ciudad, direccion, telefono, codigoPostal, genero, permiso FROM usuario WHERE idUsuario = ?");
	    ps.setInt(1, idUsuario);
	    ResultSet rs = ps.executeQuery();

	    Usuario usuario = null;
	    if (rs.next()) {
	        // Crear un objeto Usuario con los datos recuperados de la base de datos
	        usuario = new Usuario(rs.getInt("idUsuario"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correoElectronico"), rs.getString("fechaNacimiento"), rs.getString("dni"), rs.getString("nacionalidad"), rs.getString("ciudad"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("codigoPostal"), rs.getString("genero"), rs.getInt("permiso"));
	    }

	    return usuario;
	}

	/**Este método toma un objeto Java individual
	 * y lo convierte en una cadena JSON utilizando la biblioteca Gson insertada en este programa. 
	 * Nos será de gran utilidad para enviar objetos Java y almacenarlos en formato JSON.
	 * @return
	 *  @author: Alberto Campanero Arevalo
	 * @version: 4/05/2024
	 */
	
	public String dameJsonIndividual(int idUsuario) throws SQLException {
		
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.obtenerUsuarioPorId(idUsuario));	
		
	return json;
			}
	
	

}
	

	

