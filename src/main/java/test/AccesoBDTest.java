package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.List;
import pr2.*;

class AccesoBDTest {

	@Test
	void testGetInstance() {
		AccesoBD con = AccesoBD.getInstance();
		assertNotNull(con);
	}

	@Test
	void testComprobarAcceso() {
		AccesoBD con = AccesoBD.getInstance();
		boolean res = con.comprobarAcceso();
		assertTrue(res);
	}
	@Test
	void testObtenerProductosBD() {
		AccesoBD con = AccesoBD.getInstance();
		List<ProductoBD> productos = con.obtenerProductosBD();
		assertTrue(productos.size() > 0);
	}
	
	@Test
	void testObtenerUsuarioBD() {
		AccesoBD con = AccesoBD.getInstance();
		UsuarioBD usuario = con.obtenerUsuarioBD(1);
		assertTrue(usuario != null);
	}
	/*
	@Test
	void testcrearPedido() {
		AccesoBD con = AccesoBD.getInstance();
		Integer pedido = con.crearPedido("2", 128);
		assertTrue(pedido != -1);
	}
	/*
	@Test
	void testcrearDetalles() {
		AccesoBD con = AccesoBD.getInstance();
		boolean res = con.crearDetalles(2,8,2,112);
		assertTrue(res);
	}
	*/
	@Test
	void testobtenerPedidosBD() {
		AccesoBD con = AccesoBD.getInstance();
		List<PedidoBD> pedidos = con.obtenerPedidosBD(1);
		assertTrue(pedidos.size() > 0);
	}
	@Test
	void testobtenerDetalles() {
		AccesoBD con = AccesoBD.getInstance();
		List<Detalle> detalle = con.obtenerDetalles(28);
		assertTrue(detalle.size() > 0);
	}
	@Test
	void testobtenerProductoporID() {
		AccesoBD con = AccesoBD.getInstance();
		String prod = con.obtenerProductoporID(10);
		assertTrue(prod != "");
	}
}
