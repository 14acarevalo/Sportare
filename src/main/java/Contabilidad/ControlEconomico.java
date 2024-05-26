package Contabilidad;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.google.gson.Gson;

import ServiciosDeportivos.ServicioDeportivo;
import Usuarios.Usuario; //recibe este nombre porque viene del paquete usuario. NOTA MENTAL, RECORDAR ESTO
import Usuarios.Usuario;
import dao.DaoControlEconomico;
import dao.DaoUsuarios;

/** En este cas nos encontramos con los atributos del controlEconomico
 * IdIngreso, será su primary key
 * ingresos, harán referencia al ingreso realizado por parte del usuario el cual introducirá el coordinador
 * fecha, para que quede constancia de cuando se produjo
 * @author: Alberto Campanero Arevalo
 * @version: 14/04/2024
 */
public class ControlEconomico {
	private int idIngreso;
	private String ingresos; 
	private String fechaIngreso;
	private int idUsuario;
	private String nombreUsuario;
	private String apellidosUsuario;
	private String saldo;
	private String descripcion;
	
	/** Constructor vacio que nos ayudará en la inicialización del objeto 
	 * @version: 14/04/2024
	 *  @author: Alberto Campanero Arevalo

	 */
	
	public ControlEconomico () {
		
	}
	
	
	public ControlEconomico(int idIngreso, String ingresos, String fechaIngreso, int idUsuario, String nombreUsuario, String apellidosUsuario, String saldo, String descripcion) {
		super();
		this.idIngreso = idIngreso;
		this.ingresos = ingresos;
		this.fechaIngreso = fechaIngreso;
		this.idUsuario = idUsuario;
		this.nombreUsuario=nombreUsuario;
		this.apellidosUsuario=apellidosUsuario;
		this.saldo = saldo;
		this.descripcion=descripcion;
		}
	
	/**
	 * /** Metodo con todos y cada uno de los atributos el cual nos hará falta para la conexión con la base de datos a la hora de listar.
	 * @param ingresos
	 * @param fechaIngreso
	 * @param idUsuario
	 * @param nombreUsuario
	 * @param apellidosUsuario
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	
	public ControlEconomico(String ingresos, String fechaIngreso, int idUsuario, String nombreUsuario, String apellidosUsuario, String saldo, String descripcion) {
		super();
		this.ingresos = ingresos;
		this.fechaIngreso = fechaIngreso;
		this.idUsuario = idUsuario;
		this.nombreUsuario=nombreUsuario;
		this.apellidosUsuario=apellidosUsuario;
		this.saldo=saldo;
		this.descripcion=descripcion;
	}
	
	/** Metodo con todos y cada uno de los atributos el cual nos hará falta para la conexión con la base de datos a la hora de listar.
	 * @param idIngreso
	 * @param ingresos
	 * @param fechaIngreso
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	
	public ControlEconomico(int idIngreso, String saldo) {
		this.idIngreso=idIngreso;
		this.saldo=saldo;
	}
	
	/**Constructor creado para listar por usuario su saldo y movimientos relacionados con el nivel económico
	 * @param idIngreso
	 * @param ingresos
	 * @param fechaIngreso
	 * @param nombreUsuario
	 * @param apellidosUsuario
	 * @param saldo
	 * @param descripcion
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/05/2024
	 */
	
	public ControlEconomico(int idIngreso, String ingresos, String fechaIngreso, String nombreUsuario,
			String apellidosUsuario, String saldo, String descripcion) {
		super();
		this.idIngreso = idIngreso;
		this.ingresos = ingresos;
		this.fechaIngreso = fechaIngreso;
		this.nombreUsuario = nombreUsuario;
		this.apellidosUsuario = apellidosUsuario;
		this.saldo = saldo;
		this.descripcion = descripcion;
	}
	public ControlEconomico(int idIngreso, String ingresos, String fechaIngreso, String idUsuario, String nombreUsuario, String apellidoUsuario,
			int saldo) {
		// TODO Auto-generated constructor stub
	}
	
	/**Constructor para consultar a la base determinados datos de cara a que el usuario sea capaz de listar su saldo cuando inicia sesión
	 * @param idIngreso
	 * @param ingresos
	 * @param fechaIngreso
	 * @param saldo
	 * @param descripcion
	 */
	
	public ControlEconomico(int idIngreso, String ingresos, String fechaIngreso, int idUsuario, String saldo, String descripcion) {
		super();
		this.idIngreso = idIngreso;
		this.ingresos = ingresos;
		this.fechaIngreso = fechaIngreso;
		this.idUsuario=idUsuario;
		this.saldo = saldo;
		this.descripcion = descripcion;
		// TODO Auto-generated constructor stub
	}
	
	/** Método a través del cual busco realizar el ingreso por parte del coordinador a la base de datos.
	 * Hay que tener en cuenta que al no disponer de una cuenta para los ingresos, en este caso se opta porque se le de el dinero al coordinador en mano
	 * y se realiza dicho ingreso una vez que el dinero le ha sido entregado para que el usuario pueda reservar y adquirir un bono
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 * @throws SQLException 
	 */
	
	public void insertarIngreso () throws SQLException {
		DaoControlEconomico dao = new DaoControlEconomico ();
		dao.insertar(this);
	}
	
	/**Método a través del cual veremos los datos determinados del usuario, concretamente teniendo en cuenta su id y sobre su ingreso realizado en un momento determinado para la reserva de instalaciones
	 * @param idIngreso
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 * @throws SQLException 
	 */
	
	public void updateControlEconomico(int idIngreso) throws SQLException {
	    DaoControlEconomico dao = new DaoControlEconomico();
	    ControlEconomico aux = dao.updateControlEconomico(idIngreso);
	    
	    // Establece los datos del usuario
	    this.setIdIngreso(aux.getIdIngreso());
	    this.setIngresos(aux.getIngresos());
	    this.setFechaIngreso(aux.getFechaIngreso());
	    this.setIdUsuario(aux.getIdUsuario());	    
	    this.setNombreUsuario(aux.getNombreUsuario());
	    this.setApellidosUsuario(aux.getApellidosUsuario());
	    this.setSaldo(aux.getSaldo());
	    this.setDescripcion(aux.getDescripcion());
	}
	
	/**Este método toma un objeto Java 
	 * y lo convierte en una cadena JSON utilizando la biblioteca Gson insertada en este programa. 
	 * Nos será de gran utilidad para enviar objetos Java y almacenarlos en formato JSON.
	 * @return
	 *  @author: Alberto Campanero Arevalo
	 * @version: 4/05/2024
	 */
	
	public String dameJson() {
		String json = "";
		Gson gson = new Gson ();
		
		json = gson.toJson(this);
		return json;
	}
	
	/**
	 * A través de este método vamos a poder actualizar los datos de los usuarios que deseemos modificar
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 30/04/2024
	 */
	public void actualizarIngreso () throws SQLException {
		
		DaoControlEconomico dao = new DaoControlEconomico();
		dao.actualizarIngreso(this);
	}
	
	/**Método a través del cual busco eliminar el registro economico de un servicio
	 * @param idIngreso
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 05/05/2024
	 */
	
	public void borrarIngreso(int idIngreso) throws SQLException {
		DaoControlEconomico dao = new DaoControlEconomico();
		dao.borrarIngreso(idIngreso);
	}
	
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	
	public int getIdIngreso() {
		return idIngreso;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	
	public void setIdIngreso(int idIngreso) {
		this.idIngreso = idIngreso;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public String getIngresos() {
		return ingresos;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public void setIngresos(String ingresos) {
		this.ingresos = ingresos;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public int getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public String getApellidosUsuario() {
		return apellidosUsuario;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidosUsuario = apellidosUsuario;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public String getSaldo() {
		return saldo;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 14/04/2024
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/** Este método nos ayudará a listar datos por consola, facilitando el conocimiento al programador sobre la llegada de los diferentes datos que desees 
	 * @version: 14/04/2024
	 * @author: Alberto Campanero Arevalo
	 */
	@Override
	public String toString() {
		return "ControlEconomico [idIngreso=" + idIngreso + ", ingresos=" + ingresos + ", fechaIngreso=" + fechaIngreso
				+ ", idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", apellidosUsuario="
				+ apellidosUsuario + ", saldo=" + saldo + ", descripcion=" + descripcion + "]";
	}
	
	
	
	
	
	
}