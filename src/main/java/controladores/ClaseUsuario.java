package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Usuarios.Usuario;
import dao.DaoUsuarios;

/**
 * Servlet implementation class Clase_usuario
 */

public class ClaseUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Usuario a = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClaseUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //Código utilizado para cifrar la contraseña
    
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request/*entrada*/, HttpServletResponse response/*salida*/) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		System.out.println("Esto lo leo");	
		 
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
    	String correoElectronico = request.getParameter("correoElectronico");
        String password = getMD5(request.getParameter("password"));
        String nombre = request.getParameter("nombre");
        int permiso = Integer.parseInt(request.getParameter("permiso"));
		
        Usuario usuario = new Usuario(idUsuario, nombre, correoElectronico, password, permiso);

		HttpSession sesion = request.getSession();
         sesion.setAttribute("idUsuario", usuario.getIdUsuario());
         sesion.setAttribute("permiso", usuario.getPermiso());
         sesion.setAttribute("nombre", usuario.getNombre());

		
		int opcion = Integer.parseInt(request.getParameter("op"));

		
		
		if(opcion == 2) {
			
			int idUsuario1 = Integer.parseInt(request.getParameter("idUsuario"));
			System.out.println("Aqui llego");
			Usuario a = new Usuario ();
			try {
				a.update(idUsuario1);
				out.print(a.listaJson());
				System.out.println(a.listaJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		
			
		} else if (opcion == 1) {
			System.out.println("Aqui llego 2");

			DaoUsuarios usuarios;
			try {
				usuarios = new DaoUsuarios();
				out.print(usuarios.dameJson()); //en mi caso se llama listar
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**Estos atributos han sido creados con el objetivo de poder cogerlos en el cuestionario, front e insertarlos en la base de datos
		 */
		
		
		System.out.println("LLega el mensaje");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String correoElectronico = request.getParameter("correoElectronico");
		String password = getMD5(request.getParameter("password"));
		String fechaNacimiento = request.getParameter("fechaNacimiento");
		String dni = request.getParameter("dni");
		String nacionalidad = request.getParameter("pais");
		String ciudad = request.getParameter("ciudad");
		String direccion = request.getParameter("direccion");
		String calle = request.getParameter("calle");
		String telefono = request.getParameter("telefono");
		String codigoPostal = request.getParameter("codigoPostal");
		String genero = request.getParameter("genero");
		int permiso = Integer.parseInt(request.getParameter("permiso"));

		
		Usuario a = new Usuario (nombre, apellidos, correoElectronico, password, fechaNacimiento, dni, nacionalidad, ciudad, direccion, calle, telefono, codigoPostal, genero, permiso);

		System.out.println(a.toString());
			    
		/** A través de este código se inserta pero tambien nos conduce a la página del login, no dejando en blanco la página una vez insertado el usuario
		 * 
		 */
		 try {
	            DaoUsuarios daoUsuarios = DaoUsuarios.getInstance();
	            daoUsuarios.insertar(a);
	            request.setAttribute("mensaje", "Usuario insertado correctamente");
	            HttpSession session = request.getSession();
	            session.setAttribute("idUsuario", a.getIdUsuario());
	            session.setAttribute("nombre", a.getNombre());

	            // Establece el mensaje de confirmación
	        } catch (SQLException e) {
	            e.printStackTrace();
	            request.setAttribute("mensaje", "Hubo un error al insertar el usuario"); // Establece el mensaje de error
	        }
	        
	        // Redirige al usuario de vuelta al formulario
	       if (permiso == 9) {
	        	request.getRequestDispatcher("PagCoordMenu.html").forward(request, response);
	       } else {
	    	   if (permiso == 1) {
		        	request.getRequestDispatcher("MenuUsuarios.jsp").forward(request, response);

	    	   }
	       }
	       
	        }
	}



