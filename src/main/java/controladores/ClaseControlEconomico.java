package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import Contabilidad.ControlEconomico;

/**
 * Servlet implementation class ClaseControlEconomico
 */
public class ClaseControlEconomico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClaseControlEconomico() {
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
		String ingresos = request.getParameter("ingresos");
		String fechaIngreso = request.getParameter("fechaIngreso");
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		String nombreUsuario = request.getParameter("nombreUsuario");
		String apellidosUsuario = request.getParameter("apellidosUsuario");
		String saldo = request.getParameter("saldo");
		String descripcion = request.getParameter("descripcion");
		
		
		ControlEconomico a = new ControlEconomico (ingresos, fechaIngreso, idUsuario, nombreUsuario, apellidosUsuario, saldo, descripcion);

		System.out.println(a.toString());
			    
		try {
			a.insertarIngreso();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al insertar");
		}
		System.out.println(a.toString());
	    response.sendRedirect("PagCoordMenu.html");

			    }




	}


