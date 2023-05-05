package pr2;


public class Detalle {
	private int c_pedido;
	private int id_producto;
	private String prod;
	private int uds;
	private float precio;
	
	public int getC_pedido() {
		return c_pedido;
	}
	public void setC_pedido(int c_pedido) {
		this.c_pedido = c_pedido;
	}
	public String getProducto() {
		return prod;
	}
	public void setProducto(String producto) {
		this.prod = producto;
	}
	public int getUds() {
		return uds;
	}
	public void setUds(int uds) {
		this.uds = uds;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
}
