package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import Usuarios.Usuario;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
HttpSession sesion;
    public Login() {
        super();
    }

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
    	String correoElectronico = request.getParameter("correoElectronico");
        String password = getMD5(request.getParameter("password"));
        String nombre = request.getParameter("nombre");
        int permiso = Integer.parseInt(request.getParameter("permiso"));

        System.out.println("Id: " + idUsuario);

        System.out.println("Correo electrónico: " + correoElectronico);
        System.out.println("Contraseña: " + password);
        System.out.println("Permiso: " + permiso);
        System.out.println("Nombre: " + nombre);

        Usuario usuario = new Usuario(idUsuario, nombre, correoElectronico, password, permiso);
        try {
            if (usuario.logeo(idUsuario, nombre, password, permiso)) {
                sesion = request.getSession();
                
                sesion.setAttribute("idUsuario", usuario.getIdUsuario());
                sesion.setAttribute("permiso", usuario.getPermiso());
                sesion.setAttribute("nombre", usuario.getNombre());

                System.out.println("ID de usuario establecido en la sesión: " + usuario.getIdUsuario());

                
                if (permiso == 9) {
                    response.sendRedirect("PagCoordMenu.html");
                } else if (permiso == 1) {
                    response.sendRedirect("MenuUsuarios.jsp");
                } else {
                    response.sendRedirect("Pag_entrarUsuario.html");
                }
            } else {
                response.sendRedirect("PagError.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("PagError.html");
        }
    }
}