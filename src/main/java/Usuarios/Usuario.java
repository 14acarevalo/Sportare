package Usuarios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Contabilidad.ControlEconomico;
import ServiciosDeportivos.ServicioDeportivo;
import dao.DaoControlEconomico;
import dao.DaoUsuarios;

/*Esta clase nos define los atributos del usuario
 * @author: Alberto Campanero Arevalo
 * @version: 10/04/2024
 */
public class Usuario  {

	private int idUsuario;
	private String nombre;
	private String apellidos;
	private String correoElectronico;
	private String password;
	private String fechaNacimiento;
	private String dni;
	private String nacionalidad;
	private String ciudad;
	private String direccion;
	private String calle;
	private String codigoPostal;
	private String telefono;
	private String saldo; 
	private String genero;
	
	private int permiso;
	
	/**
	 * Constructor vacio, mas que necesario. Inicializa los miembros de datos de la clase con sus valores predeterminados.
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 * 
	 */
	public Usuario() {
		
	}
	/**Constructor creado para el uso del método insertar en donde aparece por primera vez los permisos
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param correoElectronico
	 * @param password
	 * @param fechaNacimiento
	 * @param dni
	 * @param nacionalidad
	 * @param ciudad
	 * @param direccion
	 * @param calle
	 * @param codigoPostal
	 * @param telefono
	 * @param genero
	 * @param permiso
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public Usuario(String nombre, String apellidos, String correoElectronico, String password,
			String fechaNacimiento, String dni, String nacionalidad, String ciudad,
			String direccion, String calle, String codigoPostal, String telefono, String genero, int permiso) {
		// TODO Auto-generated constructor stub
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correoElectronico = correoElectronico;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.nacionalidad = nacionalidad;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.calle = calle;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.genero = genero;
		this.permiso=permiso;
	}
	
	
	/**
	 * Constructor con todos los atributos, me va a servir para listar los datos de mi base de datos sin el permiso
	 * @param idUsuario: Define el id, primary key de mi usuario.
	 * @param nombre: Datos personales de tipo string para identificar al usuario por su nombre.
	 * @param apellidos: Datos personales de tipo string para identificar al usuario por sus apellidos.
	 * @param correoElectronico: Datos personales de tipo string para identificar al usuario y para acceder.
	 * @param contraseña: Parametro para facilitar el acceso.
	 * @param fechaNacimiento: Dato que nos viene bien para valorar el rango de edad y actividades.
	 * @param dni: Documento identificativo.
	 * @param nacionalidad: Nacionalidad del usuario
	 * @param ciudad: CIudad donde vive
	 * @param direccion: Concreccion de la direcion
	 * @param calle: Calle del usuario
	 * @param codigoPostal: Codigo postal
	 * @param telefono: Parametro para facilitar el contacto en todo momento con el usuario
	 * @param saldo: EL dinero que tiene en su cuenta virtual
	 * @param genero: Datos en relacion al genero
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public Usuario(int idUsuario, String nombre, String apellidos, String correoElectronico, String contraseña,
			String fechaNacimiento, String dni, String nacionalidad, String ciudad, String direccion, String calle,
			String codigoPostal, String telefono, String saldo, String genero) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correoElectronico = correoElectronico;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.nacionalidad = nacionalidad;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.calle = calle;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.saldo = saldo;
		this.genero = genero;
	}
		
	/**
	 * Constructor utilizado para el metodo actualizar usuario, en donde parametros como el id, el saldo y el permiso han sido excluidos ya que no se quieren modificar
	 * @param nombre
	 * @param apellidos
	 * @param correoElectronico
	 * @param contraseña
	 * @param fechaNacimiento
	 * @param dni
	 * @param nacionalidad
	 * @param ciudad
	 * @param direccion
	 * @param calle
	 * @param codigo
	 * @param telefono
	 * @param genero
	 * @param codigoPostal 
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public Usuario(String nombre, String apellidos, String correoElectronico, String password,
			String fechaNacimiento, String dni, String nacionalidad, String ciudad,
			String direccion, String calle, String codigoPostal, String telefono, String genero) {
		// TODO Auto-generated constructor stub
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correoElectronico = correoElectronico;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.nacionalidad = nacionalidad;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.calle = calle;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.genero = genero;
	}
	
	
	/**Constructor asociado al logeo 
	 * @param idUsuario
	 * @param nombre
	 * @param apellidos
	 * @param correoElectronico
	 * @param fechaNacimiento
	 * @param dni
	 * @param nacionalidad
	 * @param ciudad
	 * @param direccion
	 * @param calle
	 * @param codigoPostal
	 * @param telefono
	 * @param saldo
	 * @param genero
	 * @param permiso
	 * @author: Alberto Campanero Arevalo
	 * @version: 06/05/2024
	 */
	
	public Usuario(int idUsuario, String nombre, String apellidos, String correoElectronico, String fechaNacimiento, String dni, String nacionalidad, String ciudad, String direccion,
			String calle, String codigoPostal, String telefono, String saldo, String genero, int permiso) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correoElectronico = correoElectronico;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.nacionalidad = nacionalidad;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.calle = calle;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.saldo = saldo;
		this.genero = genero;
		this.permiso = permiso;
	}
	
	/**Constructor utilizado para el listar usuario cuando aparece en su perfil. Resaltar que se ha quitado el saldo, ya que el usuario podrá verlo en otro apartado de su perfil y la calle ya que el constructor lo cogio como parecido al anterior por tener el mismo numero de atributos y este es similar a la dirección
	 * @param idUsuario
	 * @param nombre
	 * @param apellidos
	 * @param correoElectronico
	 * @param password
	 * @param fechaNacimiento
	 * @param dni
	 * @param nacionalidad
	 * @param ciudad
	 * @param direccion
	 * @param codigoPostal
	 * @param telefono
	 * @param genero
	 * @param permiso
	 * @author: Alberto Campanero Arevalo
	 * @version: 06/05/2024
	 */
	public Usuario(int idUsuario, String nombre, String apellidos, String correoElectronico, String fechaNacimiento, String dni, String nacionalidad, String ciudad, String direccion,
			 String codigoPostal, String telefono, String genero, int permiso) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correoElectronico = correoElectronico;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.nacionalidad = nacionalidad;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.genero = genero;
		this.permiso = permiso;
	}
	
	/**Constructor a través del cual nos va a servir para coger los datos correspondientes en la base de datos sobre el correo y la password para poder acceder al servicio de la app.
	 * @author: Alberto Campanero Arevalo
	 * @version: 07/05/2024
	 */
	
	public Usuario(int idUsuario, String nombre, String correoElectronico, String password, int permiso) {
		super();
		this.idUsuario = idUsuario;
		this.nombre= nombre;
		this.correoElectronico = correoElectronico;
		this.password = password;
		this.permiso = permiso;
	}
	
	/**Constructor actualizado utilizado para borrar todos los datos del usuario desde el punto de vista del coordinador
	 * @param idUsuario
	 * @param nombre
	 * @param apellidos
	 * @param correoElectronico
	 * @param password
	 * @param fechaNacimiento
	 * @param dni
	 * @param nacionalidad
	 * @param ciudad
	 * @param direccion
	 * @param calle
	 * @param codigoPostal
	 * @param telefono
	 * @param saldo
	 * @param genero
	 * @param permiso
	 * @author: Alberto Campanero Arevalo
	 * @version: 07/05/2024
	 * 
	 */
	public Usuario(int idUsuario, String nombre, String apellidos, String correoElectronico, String password,
			String fechaNacimiento, String dni, String nacionalidad, String ciudad, String direccion, String calle,
			String codigoPostal, String telefono, String saldo, String genero, int permiso) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correoElectronico = correoElectronico;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.nacionalidad = nacionalidad;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.calle = calle;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.saldo = saldo;
		this.genero = genero;
		this.permiso = permiso;
	}
	
	
	
	
	/**
	 * Con este metodo buscamos insertar en la base de datos desde el cuestionario HTML. Existe una conexion a través del paquete
	 * DAO, en este caso DAOUsuario y el servlet, ClaseUsuario que facilita la conexión con la base de datos y el HTML
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public void insertarUsuario () throws SQLException {
		DaoUsuarios dao = new DaoUsuarios ();
		dao.insertar(this);
	}

	
	//ESTO ES NUEVO, CUIDADO!!
	/** A través de este método vamos a buscar que se mofiquen los datos del usuario
	 * @param idUsuario
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 27/04/2024
	 */
	
	public void update(int idUsuario) throws SQLException {
	    DaoUsuarios dao = new DaoUsuarios();
	    Usuario aux = dao.update(idUsuario);
	    
	    // Establece los datos del usuario
	    this.setIdUsuario(aux.getIdUsuario());
	    this.setNombre(aux.getNombre());
	    this.setApellidos(aux.getApellidos());
	    this.setCorreoElectronico(aux.getCorreoElectronico());
	    //this.setPassword(aux.getPassword());
	    this.setFechaNacimiento(aux.getFechaNacimiento());
	    this.setDni(aux.getDni());
	    this.setNacionalidad(aux.getNacionalidad());
	    this.setCiudad(aux.getCiudad());
	    this.setDireccion(aux.getDireccion());
	    this.setCalle(aux.getCalle());
	    this.setTelefono(aux.getTelefono());
	    this.setCodigoPostal(aux.getCodigoPostal());
	    this.setGenero(aux.getGenero());
	}

	
	/**Método para llevar a cabo el "logeo" o mejor dicho el acceso del usuario al servicio, en este se tendrán en cuenta diferentes datos, pero en especial la contraseña.
	 * @param password
	 * @return
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 07/05/2024
	 */
	
	public boolean logeo (int idUsuario, String nombre, String password, int permiso) throws SQLException {
		boolean ok = false;
		
		DaoUsuarios dao = new DaoUsuarios ();
		Usuario aux = dao.logeando(this, idUsuario, nombre, password, permiso);
		
		if(aux != null) {
			
				ok=true;
				
			 	this.setIdUsuario(aux.getIdUsuario());
			    this.setNombre(aux.getNombre());
			    this.setApellidos(aux.getApellidos());
			    this.setCorreoElectronico(aux.getCorreoElectronico());
			    this.setPassword(aux.getPassword());
			    this.setFechaNacimiento(aux.getFechaNacimiento());
			    this.setDni(aux.getDni());
			    this.setNacionalidad(aux.getNacionalidad());
			    this.setCiudad(aux.getCiudad());
			    this.setDireccion(aux.getDireccion());
			    this.setCalle(aux.getCalle());
			    this.setTelefono(aux.getTelefono());
			    this.setCodigoPostal(aux.getCodigoPostal());
			    this.setGenero(aux.getGenero());
			    this.setPermiso(aux.getPermiso());
		}
		
		return ok;
	}
	
	/**Este método toma un objeto Java 
	 * y lo convierte en una cadena JSON utilizando la biblioteca Gson insertada en este programa. 
	 * Nos será de gran utilidad para enviar objetos Java y almacenarlos en formato JSON.
	 * @return
	 *  @author: Alberto Campanero Arevalo
	 * @version: 4/05/2024
	 */
	
	public String listaJson() {
		String json = "";
		Gson gson = new Gson ();
		
		json = gson.toJson(this);
		return json;
	}
	
	/**
	 * Método a través de este método vamos a poder actualizar los datos de los usuarios que deseemos modificar
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 30/04/2024
	 */
	
	public void actualizarUsuario () throws SQLException {
		
		DaoUsuarios dao = new DaoUsuarios();
		dao.actualizarUsuario(this);
	}
	
	/**Método a través del cual lo que se busca es borrar los datos pertenecientes a determinados usuarios dentro de nuestra app, incluida la base de datos
	 * @param idUsuario
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 5/05/2024
	 */

	public void borrarUsuario(int idUsuario) throws SQLException {
		DaoUsuarios dao = new DaoUsuarios();
		dao.borrarUsuario(idUsuario);
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public String getApellidos() {
		return apellidos;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public String getPassword() {
		return password;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public String getDni() {
		return dni;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public String getCiudad() {
		return ciudad;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public String getCalle() {
		return calle;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public void setCalle(String calle) {
		this.calle = calle;
	}

	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public String getSaldo() {
		return saldo;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public String getCodigoPostal() {
		return codigoPostal;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	public String getGenero() {
		return genero;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */

	public int getPermiso() {
		return permiso;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	/** Este método nos ayudará a listar datos por consola, facilitando el conocimiento al programador sobre la llegada de los diferentes datos que desees 
	 * @version: 10/04/2024
	 * @author: Alberto Campanero Arevalo
	 */
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", correoElectronico=" + correoElectronico + ", password=" + password + ", fechaNacimiento="
				+ fechaNacimiento + ", dni=" + dni + ", nacionalidad=" + nacionalidad + ", ciudad=" + ciudad
				+ ", direccion=" + direccion + ", calle=" + calle + ", codigoPostal=" + codigoPostal + ", telefono="
				+ telefono + ", saldo=" + saldo + ", genero=" + genero + ", permiso=" + permiso + "]";
	}
	
	

	
	
	
	


}




