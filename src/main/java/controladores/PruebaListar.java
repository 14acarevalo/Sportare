package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import Usuarios.Usuario;
import dao.DaoUsuarios;

/**
 * Servlet implementation class PruebaListar
 */
public class PruebaListar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PruebaListar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String idUsuarioStr = request.getParameter("idUsuario");
		    int idUsuario = Integer.parseInt(idUsuarioStr);
		    
		    System.out.println("ID USUARIO " +idUsuario);		
		try {
			String respuestaJSON = DaoUsuarios.getInstance().dameJsonIndividual(idUsuario);
			System.out.println(respuestaJSON);
			
			PrintWriter respuesta = response.getWriter();
			
			respuesta.print(respuestaJSON);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			
			
			
		
		
		
		
		
		/** ESTO PARA LA CONSOLA
		 * try {
			ArrayList<Usuario>	listaEnObjetos = DaoUsuarios.getInstance().listar();

			for(Usuario a : listaEnObjetos) {
				
				System.out.println (a.toString());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
	
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
