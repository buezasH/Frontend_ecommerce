package pr2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.InputStreamReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.util.ArrayList;

/**
 * Servlet implementation class RecogerCarrito
 */
@WebServlet({ "/RecogerCarrito.html" })
public class RecogerCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecogerCarrito() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Producto> carrito = new ArrayList<Producto>();
		HttpSession session = request.getSession(true);

		JsonReader jsonReader = Json.createReader(new InputStreamReader(request.getInputStream()));
		JsonObject jobj = jsonReader.readObject();

		AccesoBD con = AccesoBD.getInstance();
		if (session.getAttribute("usuario") == null)
			response.sendRedirect("jsp/loginTienda.jsp");
		else {
			int codigo = (int) session.getAttribute("usuario");
			try {
				for (String key : jobj.keySet()) { // Se recorren todos los productos pasados en el JSON
					JsonObject prod = (JsonObject) jobj.getJsonObject(key);

					Producto nuevo = new Producto();
					nuevo.setId(Integer.parseInt(prod.getString("id")));
					nuevo.setNombre(prod.getString("nombre"));
					nuevo.setPrecio(Float.parseFloat(prod.getString("precio")));
					nuevo.setCantidad(prod.getInt("cantidad"));
					nuevo.setImagen(prod.getString("imagen"));
					carrito.add(nuevo);
				}
				jsonReader.close();
				
				con.comprobarCarrito(carrito);
					session.setAttribute("pedido", carrito);
					UsuarioBD user = null;

					user = con.obtenerUsuarioBD(codigo);
					session.setAttribute("cabecera", user);

					System.out.println("Codigo de usuario " + codigo);

			} catch (Exception e) {
				e.printStackTrace();
				if (session.getAttribute("usuario") == null)
					response.sendRedirect("/jsp/loginTienda.jsp");
				response.sendRedirect("/html/carrito.html");
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/resguardo.jsp");
			rd.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
