package pr2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Registro
 */
@WebServlet("/Registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registro() {
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
		UsuarioBD usuario = new UsuarioBD();
		String url = request.getParameter("url"); // URL a la que debemos volver
		String user = request.getParameter("usuario"); // Nombre del usuario
		String clave = request.getParameter("clave"); // Clave
		HttpSession session = request.getSession(true); 
		
		AccesoBD con = AccesoBD.getInstance();
		
		usuario.setUsuario(user);
		usuario.setNombre(request.getParameter("nombre"));
		usuario.setApellidos(request.getParameter("apellidos"));
		usuario.setClave(clave);
		usuario.setTelefono(request.getParameter("telefono"));
		usuario.setDomicilio(request.getParameter("domicilio"));
		usuario.setProvincia(request.getParameter("provincia"));
		usuario.setPoblacion(request.getParameter("poblacion"));
		usuario.setCp(request.getParameter("cp"));
		
		if (con.registrarUsuarioBD(usuario)) {
			session.setAttribute("mensaje", "Usuario registrado correctamente");
		}
		else {
			session.setAttribute("mensaje","El usuario ya existe, introduce otro usuario");
		}
		
		response.sendRedirect(url);
	}

}
