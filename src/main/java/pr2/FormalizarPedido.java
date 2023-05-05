package pr2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FormalizarPedido
 */
@WebServlet(name = "FormalizarPedido.html", urlPatterns = { "/FormalizarPedido.html" })
public class FormalizarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormalizarPedido() {
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
		ArrayList<Producto> carrito = new ArrayList<Producto>();
		HttpSession session = request.getSession(true);
		
		AccesoBD con = AccesoBD.getInstance();
		
		carrito = (ArrayList<Producto>) session.getAttribute("pedido");
		
		float total = 0;
		for(Producto producto : carrito)
			total += producto.getPrecio() * producto.getCantidad();
		
		try {
			if(con.comprobarCarrito(carrito)) {
			
				String codigo = request.getParameter("codigo");
				String nombre = request.getParameter("nombre");
				String apellidos = request.getParameter("apellidos");
				String domicilio = request.getParameter("domicilio");
				String poblacion = request.getParameter("poblacion");
				String provincia = request.getParameter("provincia");
				String cp = request.getParameter("cp");
				
				if(con.actualizaUsuarioBD(Integer.valueOf(codigo), nombre, apellidos, domicilio, poblacion, provincia, cp)) {
					Integer cod_pedido = con.crearPedido(codigo, total);
					session.setAttribute("id-pedido", cod_pedido);
					
					for(Producto producto : carrito){
						Integer id_prod = producto.getId();
						Integer unidades = producto.getCantidad();
						Float precio = producto.getPrecio();
						con.crearDetalles(cod_pedido, id_prod, unidades, precio);	
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/pedido_finalizado.jsp");
		rd.forward(request, response);
	}

}
