package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import ServiciosDeportivos.ServicioDeportivo;
import dao.DaoServicioDeportivo;

/**
 * Servlet implementation class ClaseReservarConAforo
 */
public class ClaseReservarConAforo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClaseReservarConAforo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// En tu servlet ClaseReservarConAforo.java

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String nombreServicio = request.getParameter("nombreServicio");
	    String horaInicio = request.getParameter("horaInicio");
        String minInicio = request.getParameter("minInicio");
        String horaFin = request.getParameter("horaFin");
        String minFinal = request.getParameter("minFinal");
        String dia = request.getParameter("dia");
        String mes = request.getParameter("mes");
	    int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));


        try {
            DaoServicioDeportivo daoServicioDeportivo = DaoServicioDeportivo.getInstance();

            if (daoServicioDeportivo.verificarDisponibilidadAforo(nombreServicio,horaInicio, minInicio, horaFin, minFinal, dia, mes, idUsuario)) {
                
                System.out.println("Reserva exitosa");
                request.getRequestDispatcher("OperacionRealizadaCoor.html").forward(request, response);
            } else {
                System.out.println("El servicio está completo en la fecha seleccionada");
                request.getRequestDispatcher("PagError.html").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al procesar la reserva");
            request.getRequestDispatcher("PagServicioCompleto.html").forward(request, response);
        }
    }
}
