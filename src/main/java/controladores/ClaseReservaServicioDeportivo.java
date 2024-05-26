package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Contabilidad.ControlEconomico;
import ServiciosDeportivos.ServicioDeportivo;
import Usuarios.Usuario;
import dao.DaoControlEconomico;
import dao.DaoServicioDeportivo;

/**
 * Servlet implementation class ClaseReservaServicioDeportivo
 */
public class ClaseReservaServicioDeportivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClaseReservaServicioDeportivo() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
		    String nombreServicio = request.getParameter("nombreServicio");
		    String horaInicio = request.getParameter("horaInicio");
		    String minInicio = request.getParameter("minInicio");
		    String horaFin = request.getParameter("horaFin");
		    String minFinal = request.getParameter("minFinal");
		    String dia = request.getParameter("dia");
		    String mes = request.getParameter("mes");
		    int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

		    try {
	            // Obtengo el saldo del usuario desde el DAO ControlEconomico
	            DaoControlEconomico SaldoPersonalUsuario = new DaoControlEconomico(); // Reemplaza ControlEconomicoDao por el nombre real de tu clase DAO
	            String saldoUsuarioS = SaldoPersonalUsuario.obtenerSaldoUsuario(idUsuario);

	            // Verificó si el servicio y el saldo del usuario están disponibles
	            if (saldoUsuarioS != null && !saldoUsuarioS.isEmpty()) {
	                int costoServicio = 1; // Este es el precio que yo le voy a poner a mis instalaciones como se vio anteriormente, 7 € = 1 credito
	                int saldoUsuarioN = Integer.parseInt(saldoUsuarioS);

	                DaoServicioDeportivo daoServicioDeportivo = DaoServicioDeportivo.getInstance();
	                boolean servicioDisponible = daoServicioDeportivo.verificarDisponibilidad(nombreServicio, horaInicio, minInicio, horaFin, minFinal, dia, mes, idUsuario);

	                if (servicioDisponible && saldoUsuarioN >= costoServicio) {
	                    // Realizar reserva
	                    ServicioDeportivo servicio = new ServicioDeportivo(nombreServicio, horaInicio, minInicio, horaFin, minFinal, dia, mes, idUsuario);
	                    daoServicioDeportivo.insertar(servicio);
	                    System.out.println("Reserva exitosa");
	                    
	                    request.getRequestDispatcher("OperacionRealizadaCoor.html").forward(request, response);
	                } else {
	                    // Mostraré mensaje de error al usuario
	                    if (!servicioDisponible) {
	                        System.out.println("El servicio no está disponible en la fecha y hora seleccionadas");
	                    } else if (saldoUsuarioN < costoServicio) {
	                        System.out.println("No hay suficiente saldo");
	                    }
	                    request.getRequestDispatcher("PistaOcupada.html").forward(request, response);
	                }
	            } else {
	                // Mostraré mensaje de error al usuario
	                System.out.println("No se pudo obtener el saldo del usuario");
	                request.getRequestDispatcher("ErrorSaldo.html").forward(request, response);
	            }
	        } catch (SQLException e) {
	            // Manejo el error de base de datos
	            e.printStackTrace();
	            request.getRequestDispatcher("PagError.html").forward(request, response);
	        }
	}
}