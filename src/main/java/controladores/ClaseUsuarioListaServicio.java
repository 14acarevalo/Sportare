package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.DaoControlEconomico;
import dao.DaoServicioDeportivo;

/**
 * Servlet implementation class ClaseUsuarioListaServicio
 */
public class ClaseUsuarioListaServicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClaseUsuarioListaServicio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String idUsuarioStr = request.getParameter("idUsuario");
    int idUsuario = Integer.parseInt(idUsuarioStr);

    System.out.println("ID USUARIO EN PAGINA SERVICIO DEPORTIVO: " + idUsuario);

    try {
        String respuestaJSON = DaoServicioDeportivo.getInstance().dameJsonServicioUsuario(idUsuario); 
        System.out.println("JSON obtenido: " + respuestaJSON);

        PrintWriter respuesta = response.getWriter();
        respuesta.print(respuestaJSON);
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error al obtener el JSON del saldo del usuario: " + e.getMessage());
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write("{\"error\":\"Error al obtener los datos\"}");
    }
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
