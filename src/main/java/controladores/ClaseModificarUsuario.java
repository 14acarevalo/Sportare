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
import java.sql.SQLException;

import Usuarios.Usuario;
import dao.DaoControlEconomico;
import dao.DaoUsuarios;

/**
 * Servlet implementation class ClaseModificarUsuario
 */
public class ClaseModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClaseModificarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //Este codigo facilitado por Antonio, se usará para la contraseña
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//int idSesion = Integer.parseInt((String) sesion.getAttribute("idUsuario"));
		
		sesion = request.getSession();
		 
		PrintWriter out = response.getWriter();
		System.out.println("Esto lo leo");	
		
		
		int opcion = Integer.parseInt(request.getParameter("op"));

		
		
		if(opcion == 2) {
			//Proceso logica para la edicción del usuario
			
			int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
			System.out.println("Aqui llego");
			Usuario a = new Usuario ();
			try {
				a.update(idUsuario);
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
				out.print(usuarios.dameJson()); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			} else if (opcion==3) {
				
				try {
					int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

					DaoUsuarios usuarios  = new DaoUsuarios();
					usuarios.borrarUsuario(idUsuario);
					System.out.println("Estoy borrando, vamos a probar " +idUsuario);
					System.out.println("Estoy borrando opcion " +opcion);

					out.print(usuarios.listar());
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

		//NOS QUEDAMOS AQUI Y VAMOS A IR VIENDO, MIN 3.50 CLASE DEL 6 DE ABRIL
	
		System.out.println("LLega el mensaje");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String correoElectronico = request.getParameter("correoElectronico");
		String password =(request.getParameter("password"));
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
		String idUsuario = request.getParameter("idUsuario");


		Usuario a = new Usuario (nombre, apellidos, correoElectronico, password, fechaNacimiento, dni, nacionalidad, ciudad, direccion, calle, telefono, codigoPostal, genero, permiso);

		System.out.println(a.toString());
		
	 try {
		 
			System.out.println("Aqui llego 3");

         
		 if (idUsuario == null || idUsuario.isEmpty() || idUsuario == "") {
			 a.insertarUsuario();
		 } else {
			 
				System.out.println("Aqui llego 4");

			 int idInt = Integer.parseInt(idUsuario);
			 a.setIdUsuario(idInt);
			 a.actualizarUsuario();
		 }
		 // Establece el mensaje de confirmación
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Hubo un error al insertar el usuario"); // Establece el mensaje de error
        }
        
        // Redirige al usuario de vuelta al formulario
        request.getRequestDispatcher("PagVIsualizarDatosUsuario.html").forward(request, response);
}
	
}