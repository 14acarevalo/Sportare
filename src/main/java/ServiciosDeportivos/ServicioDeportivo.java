package ServiciosDeportivos;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Usuarios.Usuario;
import dao.DaoControlEconomico;
import dao.DaoServicioDeportivo;
import dao.DaoUsuarios;

public class ServicioDeportivo {

	/*Esta clase nos define los atributos del servicio deportivo
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/04/2024
	 */
	
	 private int idServicio;
	 private String nombre; //último atributo añadido, el nombre del servicio
	 private String descripcion; // hace referencia a si es una carrera, una clase, parecido a la descripcion
	 private String horaIniciacion;
	 private String horaFinalizacion;
	 private String dia;
	 private String mes;
	 private String precio;
	 private String disponibilidad;
	 private String minInicio;
	 private String minFinal;
	 private int aforo; //QUizas la tenga que suprimir, para ciertos deportes como el padel no es necesario, fútbol... respecto a las actividades si puede que sea neesaria
	 private int numPersonas = 2; //Esta variable está fija y se podrá cambiar por entidad, ya que en este ejemplo, las salas tienen un máximo de 30 personas.
	 private int idUsuario;
	 
	 /** Constructor vacío puede ser útil en diversos escenarios de programación, proporcionando 
	  * flexibilidad, compatibilidad y conveniencia en la creación y manipulación de objetos.
	  * @author: Alberto Campanero Arevalo
	  * @version: 09/05/2024
	  */
	 
	 public ServicioDeportivo () {
		 
	 }
	 
	 /**
	  * Metodo para el servicio deportivo que tenga un aforo y necesitemos saber si está o no disponible para realizarlo
	  * @param idServicio
	  * @param nombre
	  * @param descripcion
	  * @param horaIniciacion
	  * @param horaFinalizacion
	  * @param dia
	  * @param mes
	  * @param precio
	  * @param disponibilidad
	  * @param horasDisponibles
	  * @param aforo
	  * @author: Alberto Campanero Arevalo
	  * @version: 09/05/2024
	  */
	 
	 public ServicioDeportivo(int idServicio, String nombre, String descripcion, String horaIniciacion,
				String horaFinalizacion, String dia, String mes, String precio, String disponibilidad,
				ArrayList horasDisponibles, int aforo) {
			super();
			this.idServicio = idServicio;
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.horaIniciacion = horaIniciacion;
			this.horaFinalizacion = horaFinalizacion;
			this.dia = dia;
			this.mes = mes;
			this.precio = precio;
			this.disponibilidad = disponibilidad;
			this.aforo = aforo;
			
		}
	 
	 /**Constructor con el aforo para que se tenga en cuenta a la hora de llevar a cabo la inserción y/o reserva en diferentes instalaciones que lo requieren
	  * @param idServicio
	  * @param nombre
	  * @param descripcion
	  * @param horaIniciacion
	  * @param horaFinalizacion
	  * @param dia
	  * @param mes
	  * @param precio
	  * @param disponibilidad
	  * @param aforo
	  * @param numPersonas
	  * @author: Alberto Campanero Arevalo
	  * @version: 09/05/2024
	  */
	 
	 public ServicioDeportivo(int idServicio, String nombre, String descripcion, String horaIniciacion,
			String horaFinalizacion, String dia, String mes, String precio, String disponibilidad, int aforo, int numPersonas) {
		super();
		this.idServicio = idServicio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.horaIniciacion = horaIniciacion;
		this.horaFinalizacion = horaFinalizacion;
		this.dia = dia;
		this.mes = mes;
		this.precio = precio;
		this.disponibilidad = disponibilidad;
		this.aforo = aforo;
		this.numPersonas = numPersonas;
	}

	/**
	 * Constructor generado para la realizacion de la reserva deportiva sin aforo
	 * @param nombre
	 * @param horaIniciacion
	 * @param horaFinalizacion
	 * @param disponibilidad
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */
	 
	public ServicioDeportivo(String nombre, String horaIniciacion, String minInicio, String horaFinalizacion,  String minFinal, String dia, String mes, int idUsuario) {
		super();
		this.nombre = nombre;
		this.horaIniciacion = horaIniciacion;
		this.minInicio = minInicio;
		this.horaFinalizacion = horaFinalizacion;
		this.minFinal = minFinal;
		this.dia = dia;
		this.mes=mes;
		this.idUsuario= idUsuario;
	}
	
	/** Constructor realizado para el listar con todos los atributos que quiero que se muestren por pantalla
	 * 
	 * @param idServicio
	 * @param nombre
	 * @param horaIniciacion
	 * @param minInicio
	 * @param horaFinalizacion
	 * @param minFinal
	 * @param dia
	 * @param mes
	 * @param idUsuario
	 * @author: Alberto Campanero Arevalo
	 * @version: 03/05/2024
	 */
	
	public ServicioDeportivo(int idServicio, String nombre, String horaIniciacion, String minInicio, String horaFinalizacion,  String minFinal, String dia, String mes, int idUsuario) {
		super();
		this.idServicio = idServicio;
		this.nombre = nombre;
		this.horaIniciacion = horaIniciacion;
		this.minInicio = minInicio;
		this.horaFinalizacion = horaFinalizacion;
		this.minFinal = minFinal;
		this.dia = dia;
		this.mes=mes;
		this.idUsuario= idUsuario;
	}
	
	/**Metodo a través del cual se busca que el usuario pueda ver sus reservas, ya que se tendra en cuenta el IDUsuario como  foreign key
	 * 
	 * @param idServicio
	 * @param nombre
	 * @param horaIniciacion
	 * @param minInicio
	 * @param horaFinalizacion
	 * @param minFinal
	 * @param dia
	 * @param mes
	 * @author: Alberto Campanero Arevalo
	 * @version: 10/05/2024
	 */
	
	public ServicioDeportivo(int idServicio, String nombre, String horaIniciacion, String minInicio, String horaFinalizacion,  String minFinal, String dia, String mes) {
		super();
		this.idServicio = idServicio;
		this.nombre = nombre;
		this.horaIniciacion = horaIniciacion;
		this.minInicio = minInicio;
		this.horaFinalizacion = horaFinalizacion;
		this.minFinal = minFinal;
		this.dia = dia;
		this.mes=mes;
	}
	
	/**
	 * Constructor generado para tener en cuenta estos parametros a la hora de seleccionar la actividad y evitar que este ocupada / reservada
	 * @param dia
	 * @param horaIniciaion
	 * @param mes
	 * @author: Alberto Campanero Arevalo
     * @version: 28/04/2024
	 */
	
	public ServicioDeportivo (String dia, String horaIniciaion, String mes) {
		super();
		this.dia = dia;
		this.horaIniciacion = horaIniciacion;
		this.mes = mes;
	}
 
	/**Método generado para la reservar en las instalaciones y el servicio deportivo
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */
	
	public void insertarReserva() throws SQLException {
		// TODO Auto-generated method stub
		DaoServicioDeportivo dao = new DaoServicioDeportivo ();
		dao.insertar(this);
	}

	/**Método a través del cual se busca coger los datos de la base de datos en el servicio deportivo realizado 
	 *  @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 03/05/2024
	 * 
	 */
	
	public void updateServicio(int Servicio) throws SQLException {
	    DaoServicioDeportivo dao = new DaoServicioDeportivo();
	    ServicioDeportivo aux = dao.updateServicio(idServicio);
	    
	    // Establece los datos del usuario
	    this.setNombre(aux.getNombre());
	    this.setHoraIniciacion(aux.getHoraIniciacion());
	    this.setMinInicio(aux.getMinInicio());
	    this.setHoraFinalizacion(aux.getHoraFinalizacion());
	    this.setMinFinal(aux.getMinFinal());
	    this.setDia(aux.getDia());
	    this.setMes(aux.getMes());
	}

	/**Este método toma un objeto Java 
	 * y lo convierte en una cadena JSON utilizando la biblioteca Gson insertada en este programa. 
	 * Nos será de gran utilidad para enviar objetos Java y almacenarlos en formato JSON.
	 * @return
	 *  @author: Alberto Campanero Arevalo
	 * @version: 4/05/2024
	 */
	
	public String listaJsonServicio() {
		String json = "";
		Gson gson = new Gson ();
		
		json = gson.toJson(this);
		return json;
	}
	 
	/**Método a través del cual se busca actualizar el servicio deportivo realizado e insertado en la base de datos
	 *  @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 03/05/2024
	 * 
	 */
	
	public void actualizarServicio () throws SQLException {
			
			DaoServicioDeportivo dao = new DaoServicioDeportivo();
			dao.actualizarServicio(this);
		}
	 
	/**
	 * Metodo para borrar los datos del servicio deportivo
	 * @param iServicio
	 * @throws SQLException
	 * @author: Alberto Campanero Arevalo
	 * @version: 05/05/2024
	 */
	
	public void borrarServicio(int iServicio) throws SQLException {
		DaoServicioDeportivo dao = new DaoServicioDeportivo();
		dao.borrarServicio(idServicio);
	}
	
	 /**
		 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
		 * @return
		 * @author: Alberto Campanero Arevalo
		 * @version: 28/04/2024
		 */
	
	 public int getIdServicio() {
			return idServicio;
		}

	 /**
		 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
		 * @return
		 * @author: Alberto Campanero Arevalo
		 * @version: 28/04/2024
		 */
	
	 public void setIdServicio(int idServicio) {
			this.idServicio = idServicio;
		}
	 
	 /**
		 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
		 * @return
		 * @author: Alberto Campanero Arevalo
		 * @version: 28/04/2024
		 */
	 
	 public String getNombre() {
			return nombre;
		}

	 /**
		 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
		 * @return
		 * @author: Alberto Campanero Arevalo
		 * @version: 28/04/2024
		 */
	 
	 public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
	 /**
		 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
		 * @return
		 * @author: Alberto Campanero Arevalo
		 * @version: 28/04/2024
		 */
	 
	 public String getDescripcion() {
		return descripcion;
	}

	 /**
		 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
		 * @return
		 * @author: Alberto Campanero Arevalo
		 * @version: 28/04/2024
		 */

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public String getHoraIniciacion() {
		return horaIniciacion;
	}

	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public void setHoraIniciacion(String horaIniciacion) {
		this.horaIniciacion = horaIniciacion;
	}

	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public String getHoraFinalizacion() {
		return horaFinalizacion;
	}

	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public void setHoraFinalizacion(String horaFinalizacion) {
		this.horaFinalizacion = horaFinalizacion;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public String getDisponibilidad() {
		return disponibilidad;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public int getAforo() {
		return aforo;
	}

	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public String getPrecio() {
		// TODO Esbozo de método generado automáticamente
		return precio;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public String getDia() {
		return dia;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */
	
	public void setDia(String dia) {
		this.dia=dia;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public String getMes() {
		return mes;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */
	
	public void setMes(String mes) {
		this.mes=mes;
	}
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */


	public void setFechaServicio(String mes) {
		this.mes = mes;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public int getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */
	
	public String getMinInicio() {
		return minInicio;
	}

	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */
	
	public void setMinInicio(String minInicio) {
		this.minInicio = minInicio;
	}

	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public String getMinFinal() {
		return minFinal;
	}
	/**
	 * Este metodo devuelve el numero de items (numero aleatorios) existentes y de los que consta la serie
	 * @return
	 * @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	public void setMinFinal(String minFinal) {
		this.minFinal = minFinal;
	}
	/** Este método nos ayudará a listar datos por consola, facilitando el conocimiento al programador sobre la llegada de los diferentes datos que desees 
	 * 	 @author: Alberto Campanero Arevalo
	 * @version: 28/04/2024
	 */

	@Override
	public String toString() {
		return "ServicioDeportivo [nombre=" + nombre + ", horaIniciacion=" + horaIniciacion + ", horaFinalizacion="
				+ horaFinalizacion + ", dia=" + dia + ", mes=" + mes + ", minInicio=" + minInicio + ", minFinal="
				+ minFinal + ", idUsuario=" + idUsuario + "]";
	}


	}


	

	
	

	



	




	
	
















