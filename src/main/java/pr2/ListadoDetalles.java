package pr2;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ListadoDetalles
 */
@WebServlet(name = "detalles.html", urlPatterns = { "/detalles.html" })
public class ListadoDetalles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoDetalles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		AccesoBD con = AccesoBD.getInstance();
		String codigo = request.getParameter("codigo");
		
		try{
			List<Detalle> detalles = con.obtenerDetalles(Integer.valueOf(codigo));
			for(Detalle detalle:detalles)
				detalle.setProducto(con.obtenerProductoporID(detalle.getId_producto()));
			session.setAttribute("detalles", detalles);
			request.getRequestDispatcher("/jsp/table_detalles.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
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
