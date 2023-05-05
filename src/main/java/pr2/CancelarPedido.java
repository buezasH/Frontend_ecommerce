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
 * Servlet implementation class CancelarPedido
 */
@WebServlet(name = "cancelar.html", urlPatterns = { "/cancelar.html" })
public class CancelarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarPedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		AccesoBD con = AccesoBD.getInstance();
		
		
		try{
			String cod_pedido = request.getParameter("codigo");
			int cod_usu = (int) session.getAttribute("usuario");
			if(con.cancelarPedido(Integer.valueOf(cod_pedido))){
				List<PedidoBD> pedidos = con.obtenerPedidosBD(cod_usu);
				session.setAttribute("pedidos", pedidos);
				session.setAttribute("mensaje", "Pedido cancelado correctamente");
			}
			
			request.getRequestDispatcher("/jsp/modificapedidos.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


}
