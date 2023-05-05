package pr2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class ListadoUsuario
 */
@WebServlet(name = "usuario.html", urlPatterns = { "/usuario.html" })
public class ListadoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(true);
    	String url = request.getParameter("url"); // URL a la que debemos volver
    	UsuarioBD usuario = new UsuarioBD();
    	
		AccesoBD con = AccesoBD.getInstance();
		
		try {
			int codigo = (int) session.getAttribute("usuario");
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String domicilio = request.getParameter("domicilio");
			String poblacion = request.getParameter("poblacion");
			String provincia = request.getParameter("provincia");
			String cp = request.getParameter("cp");
			
			if(con.actualizaUsuarioBD(codigo, nombre, apellidos, domicilio, poblacion, provincia, cp)) {
				session.setAttribute("mensaje", "Usuario actualizado correctamente. Refresca para ver los cambios");
			}
			else {
				session.setAttribute("mensaje","No se ha podido actualizar el usuario");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(url);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		AccesoBD con = AccesoBD.getInstance();
		int codigo = (int) session.getAttribute("usuario");
		
		try{
			UsuarioBD usuario = null;
			usuario = con.obtenerUsuarioBD(Integer.valueOf(codigo));
			session.setAttribute("datos-usuario", usuario);
			request.getRequestDispatcher("/jsp/modificausuario.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
         
	}



}
