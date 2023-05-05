package pr2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url"); // URL a la que debemos volver
		String usuario = request.getParameter("usuario"); // Nombre del usuario
		String clave = request.getParameter("clave"); // Clave
		HttpSession session = request.getSession(true); // Accedemos al entorno de sesi�n
		
		AccesoBD con = AccesoBD.getInstance();

		if ((usuario != null) && (clave != null)) {
			int codigo = con.comprobarUsuarioBD(usuario,clave);
			if (codigo>0) {
				session.setAttribute("usuario",codigo);
			}
			else {
				session.setAttribute("mensaje","Usuario y/o clave incorrectos");
			}
		}
		
		response.sendRedirect(url);
	}

}
