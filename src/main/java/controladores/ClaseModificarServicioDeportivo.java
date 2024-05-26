package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import ServiciosDeportivos.ServicioDeportivo;
import Usuarios.Usuario;
import dao.DaoServicioDeportivo;
import dao.DaoUsuarios;

/**
 * Servlet implementation class ClaseModificarServicioDeportivo
 */
public class ClaseModificarServicioDeportivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClaseModificarServicioDeportivo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        System.out.println("Esto lo leo");

        int opcion = Integer.parseInt(request.getParameter("op"));

        if(opcion == 2) {
            // Proceso lógica para la edición del servicio deportivo
            int idServicio = Integer.parseInt(request.getParameter("idServicio"));
            System.out.println("Aquí llego");
            ServicioDeportivo a = new ServicioDeportivo();
            
            try {
                DaoServicioDeportivo daoServicio = new DaoServicioDeportivo();
                ServicioDeportivo servicioActualizado = daoServicio.updateServicio(idServicio);

                // Si el servicio deportivo se actualizó correctamente
                if (servicioActualizado != null) {
                    out.print(servicioActualizado.listaJsonServicio());
                    System.out.println(servicioActualizado.listaJsonServicio());
                } else {
                    // Manejo en el caso donde el servicio no se pudo actualizar
                    out.print("Error: No se pudo actualizar el servicio deportivo");
                    System.out.println("Error: No se pudo actualizar el servicio deportivo");
                }
            } catch (SQLException e) {
                // Cuidado con la excepción
                e.printStackTrace();
                out.print("Error: Ocurrió un error al actualizar el servicio deportivo");
                System.out.println("Error: Ocurrió un error al actualizar el servicio deportivo");
            }
            
        } else if (opcion == 1) {
            System.out.println("Aquí llego 2");

            try {
                DaoServicioDeportivo servicio = new DaoServicioDeportivo();
                out.print(servicio.dameJsonServicio()); 
            } catch (SQLException e) {
                // Manejamos otra vez la excepción
                e.printStackTrace();
                out.print("Error: Ocurrió un error al obtener la lista de servicios deportivos");
                System.out.println("Error: Ocurrió un error al obtener la lista de servicios deportivos");
            }
        }

        else if (opcion==3) {
			
			try {
				System.out.println("Llegamos aqui?");
				int idServicio = Integer.parseInt(request.getParameter("idServicio"));

				DaoServicioDeportivo servicio  = new DaoServicioDeportivo();
				servicio.borrarServicio(idServicio);
				System.out.println("Estoy borrando, vamos a probar " +idServicio);
				System.out.println("Estoy borrando opcion " +opcion);

				out.print(servicio.listarServicio());
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
	
		System.out.println("Llega el mensaje");
	    String nombre = request.getParameter("nombre");
	    String horaIniciacion = request.getParameter("horaIniciacion");
	    String minInicio = request.getParameter("minInicio");
	    String horaFinalizacion = request.getParameter("horaFinalizacion");
	    String minFinal = request.getParameter("minFinal");
	    String dia = request.getParameter("dia");
	    String mes = request.getParameter("mes");
	    String idUsuarioString = request.getParameter("idUsuario"); // Lo llamo asi porque lo puse en String en su momento
	    int idUsuario = 0; // Añado un valor

	    if (idUsuarioString != null && !idUsuarioString.isEmpty()) {
	        idUsuario = Integer.parseInt(idUsuarioString);
	    }
	    String idServicio = request.getParameter("idServicio");
	                
	    
	    ServicioDeportivo a = new ServicioDeportivo (nombre, horaIniciacion, minInicio, horaFinalizacion, minFinal, dia, mes, idUsuario);

	    System.out.println(a.toString());
	    
	     try {
	         
	            System.out.println("Aqui llego 3");

	         
	         if (idServicio == null || idServicio.isEmpty() || idServicio.equals("")) {
	             a.insertarReserva();
	         } else {
	             
	                System.out.println("Aqui llego 4");

	             int idInt = Integer.parseInt(idServicio);
	             a.setIdServicio(idInt);
	             a.actualizarServicio();
	         }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            request.setAttribute("mensaje", "Hubo un error al insertar el usuario"); // Establece el mensaje de error
	        }
	        
	        // Redirige al usuario de vuelta al formulario
	        request.getRequestDispatcher("ListarServicios.html").forward(request, response);
	}
}