package controladores;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.DaoControlEconomico;

public class ListarSaldo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListarSaldo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idUsuarioStr = request.getParameter("idUsuario");
        int idUsuario = Integer.parseInt(idUsuarioStr);

        System.out.println("ID USUARIO EN PAGINA CONTROL ECONOMICO: " + idUsuario);

        try {
            String respuestaJSON = DaoControlEconomico.getInstance().dameJsonSaldo(idUsuario);
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}