package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import Contabilidad.ControlEconomico;
import Usuarios.Usuario;
import dao.DaoControlEconomico;
import dao.DaoUsuarios;

/**
 * Servlet implementation class ClaseModificarControlEconomico
 */
public class ClaseModificarControlEconomico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   public ClaseModificarControlEconomico() {
      super();
        // TODO Auto-generated constructor stub
    
   }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		int opcion = Integer.parseInt(request.getParameter("op"));

		
		if(opcion == 2) {
			//Proceso logica para la edicción del usuario
			int idIngreso = Integer.parseInt(request.getParameter("idIngreso"));

			ControlEconomico a = new ControlEconomico ();
			try {
				a.updateControlEconomico(idIngreso);
				out.print(a.dameJson());
				System.out.println(a.dameJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (opcion==1) {
			DaoControlEconomico controlEconomico;
			try {
				controlEconomico = new DaoControlEconomico();
				out.print(controlEconomico.dameJson()); //en mi caso se llama listar
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else if (opcion==3) {
			
			try {
				int idIngreso = Integer.parseInt(request.getParameter("idIngreso"));

				DaoControlEconomico controlEconomico = new DaoControlEconomico();
				controlEconomico.borrarIngreso(idIngreso);
				System.out.println("Estoy borrando " +idIngreso);
				System.out.println("Estoy borrando opcion " +opcion);

				out.print(controlEconomico.listar());
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

		String ingresos = request.getParameter("ingresos");
		String fechaIngreso = request.getParameter("fechaIngreso");
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		String nombreUsuario = request.getParameter("nombreUsuario");
		String apellidosUsuario = request.getParameter("apellidosUsuario");
		String saldo = request.getParameter("saldo");
		String descripcion = request.getParameter("descripcion");
		String idIngreso = request.getParameter("idIngreso");
		
		ControlEconomico a = new ControlEconomico (ingresos, fechaIngreso, idUsuario, nombreUsuario, apellidosUsuario, saldo, descripcion);

		System.out.println(a.toString());
			    
		
	 try {
			System.out.println("Aqui llego 3");

		 if (idIngreso == "") {
			 a.insertarIngreso();
			 
		 } else {
			 int idInt = Integer.parseInt(idIngreso);
			 a.setIdIngreso(idInt);
			 a.actualizarIngreso();
		 }
		 // Establece el mensaje de confirmación
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Hubo un error al insertar el usuario"); // Establece el mensaje de error
        }
        
        // Redirige al usuario de vuelta al formulario
        request.getRequestDispatcher("ListarControlEconomico.html").forward(request, response);
}
}