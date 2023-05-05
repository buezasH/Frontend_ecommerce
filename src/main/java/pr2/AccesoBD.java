package pr2;

import java.sql.*;
import java.util.*;

public final class AccesoBD {
	private static AccesoBD instanciaUnica = null;
	private Connection conexionBD = null;

	public static AccesoBD getInstance() {
		if (instanciaUnica == null) {
			instanciaUnica = new AccesoBD();
		}
		return instanciaUnica;
	}
	
	private AccesoBD() {
		abrirConexionBD();
	}
	
	public void abrirConexionBD() {
		if (conexionBD == null)
		{ // daw es el nombre de la base de datos que hemos creado con anterioridad.
			String nombreConexionBD = "jdbc:mysql://localhost/bddtienda";
			try { // root y sin clave es el usuario por defecto que crea XAMPP.
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexionBD = DriverManager.getConnection(nombreConexionBD,"root","");
			}
			catch(Exception e) {
				System.err.println("No se ha podido conectar a la base de datos");
				System.err.println(e.getMessage());
			}
		}
	}
	
	public void cerrarConexionBD() {
		if (conexionBD != null) {
			try {
				conexionBD.close();
				conexionBD = null;
			}
			catch(Exception e) {
				System.err.println("No se ha podido desconectar de la base de datos");
				System.err.println(e.getMessage());
			}
		}
	}
	public boolean comprobarAcceso() {
		abrirConexionBD();
		boolean res = (conexionBD != null);
		cerrarConexionBD();
		return res;
	}
	
	public List<ProductoBD> obtenerProductosBD() {
		abrirConexionBD();
		
		ArrayList<ProductoBD> productos = new ArrayList<>();
		
		try {
			String query = "SELECT codigo,descripcion,precio,existencias,imagen FROM productos";
			Statement s = conexionBD.createStatement();
			ResultSet resultado = s.executeQuery(query);
			while(resultado.next()){
				ProductoBD producto = new ProductoBD();
				producto.setId(resultado.getInt("codigo"));
				producto.setDescripcion(resultado.getString("descripcion"));
				producto.setPrecio(resultado.getFloat("precio"));
				producto.setStock(resultado.getInt("existencias"));
				producto.setImagen(resultado.getString("imagen"));
				productos.add(producto);
			}
		}
		catch(Exception e) {
			System.err.println("Error ejecutando la consulta a la base de datos");
			System.err.println(e.getMessage());
		}
		cerrarConexionBD();
		return productos;
	}
	public int comprobarUsuarioBD(String usuario, String clave) {
		abrirConexionBD();
		
		try{
			String con = "SELECT codigo FROM usuarios WHERE usuario=? AND clave=?";
			PreparedStatement s = conexionBD.prepareStatement(con);
			s.setString(1,usuario);
			s.setString(2,clave);
			ResultSet resultado = s.executeQuery();
			 
			if ( resultado.next() ) { // El usuario/clave se encuentra en la BD
				return resultado.getInt("codigo");
			}
		}
		catch(Exception e) { // Error en la conexi�n con la BD
			System.err.println("Error verificando usuario/clave");
			System.err.println(e.getMessage());
		}
		return -1;
	}
	
	public boolean registrarUsuarioBD(UsuarioBD usuario) {
		abrirConexionBD();

		try {
			String cons = "SELECT codigo FROM usuarios WHERE usuario=?";
			PreparedStatement s1 = conexionBD.prepareStatement(cons);
			s1.setString(1,usuario.getUsuario());
			ResultSet resultado = s1.executeQuery();
			 
			if ( !(resultado.next()) ) { // El usuario no se encuentra en la BD
				String con = "INSERT INTO usuarios (activo, admin, usuario,clave,nombre,apellidos,telefono,domicilio,poblacion, provincia,cp) VALUES (1,0,?,?,?,?,?,?,?,?,?)";
				PreparedStatement s = conexionBD.prepareStatement(con);
				s.setString(1,usuario.getUsuario());
				s.setString(2,usuario.getClave());
				s.setString(3,usuario.getNombre());
				s.setString(4,usuario.getApellidos());
				s.setString(5,usuario.getTelefono());
				s.setString(6,usuario.getDomicilio());
				s.setString(7,usuario.getPoblacion());
				s.setString(8,usuario.getProvincia());
				s.setString(9,usuario.getCp());	
				s.executeUpdate();
				return true;
			}
			
		}
		catch(Exception e) {
			System.err.println("Error registrando usuario");
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public boolean comprobarCarrito(ArrayList <Producto> carrito){
		boolean res = true;
		abrirConexionBD();
		
		try {
			String cons = "SELECT existencias FROM productos WHERE codigo=?";
			PreparedStatement s1 = conexionBD.prepareStatement(cons);
			
			for(int i=0;i<carrito.size();i++){
				//comprueba que las existencias son menores que la cantidad que se quiere comprar
				s1.setInt(1,carrito.get(i).getId());
				ResultSet resultado = s1.executeQuery();
				if ( resultado.next() ) { // El producto se encuentra en la BD
					if(resultado.getInt("existencias") < carrito.get(i).getCantidad()){
						res = false;
						carrito.get(i).setCantidad(resultado.getInt("existencias"));
					}
				}
			}
			cerrarConexionBD();
		}
		catch(Exception e) {
			System.err.println("Error comprobando carrito");
			System.err.println(e.getMessage());
			e.printStackTrace();
			return res;
		}
		return res;
		
	}
	public UsuarioBD obtenerUsuarioBD(int codigo)
	{
		abrirConexionBD();
		UsuarioBD usuario = new UsuarioBD();
		try {
			String query = "SELECT * FROM usuarios WHERE codigo=?";
			PreparedStatement s = conexionBD.prepareStatement(query);
			s.setInt(1,codigo);
			ResultSet resultado = s.executeQuery();
			if ( resultado.next() ) {
				usuario.setUsuario(resultado.getString("usuario"));
				usuario.setClave(resultado.getString("clave"));
				usuario.setNombre(resultado.getString("nombre"));
				usuario.setApellidos(resultado.getString("apellidos"));
				usuario.setTelefono(resultado.getString("telefono"));
				usuario.setDomicilio(resultado.getString("domicilio"));
				usuario.setPoblacion(resultado.getString("poblacion"));
				usuario.setProvincia(resultado.getString("provincia"));
				usuario.setCp(resultado.getString("cp"));
			}
		}
		catch(Exception e) {
			System.err.println("Error obteniendo usuario");
			System.err.println(e.getMessage());
		}
		cerrarConexionBD();
		return usuario;
		
	}
	
	private final static String SQL_ACTUALIZA_USUARIO = "UPDATE usuarios SET nombre = ?, apellidos = ?, domicilio = ?, poblacion = ?, provincia = ?, cp = ? WHERE codigo = ?";
    
    public boolean actualizaUsuarioBD(int codigo, String nombre, String apellidos, String domicilio, String poblacion,String provincia, String cp) {
        boolean actualizado = false;
        
        abrirConexionBD();
        
        try {

            PreparedStatement ps = conexionBD.prepareStatement(SQL_ACTUALIZA_USUARIO);
            
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, domicilio);
            ps.setString(4, poblacion);
            ps.setString(5, provincia);
            ps.setString(6, cp);
            ps.setInt(7, codigo);
            
            ps.execute();
            
            actualizado = (ps.getUpdateCount()>0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        cerrarConexionBD();
        return actualizado;
    }
    
    public int crearPedido(String user, float total) {
    	int cod = -1;
    	abrirConexionBD();
    	
    	try {
    		String con = "INSERT INTO pedidos (persona , fecha, importe, estado) VALUES (?,?,?,?)";
			PreparedStatement s = conexionBD.prepareStatement(con);
			
			java.sql.Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			
			s.setInt(1,Integer.valueOf(user));
			s.setDate(2, fecha);
			s.setFloat(3, total);
			s.setInt(4, 0);
			s.execute();
			
			if(s.getUpdateCount()>0) {
				String query = "SELECT * FROM pedidos WHERE persona=? AND fecha=? AND importe=? AND estado=?";
				PreparedStatement s1 = conexionBD.prepareStatement(query);
				
				s1.setInt(1,Integer.valueOf(user));
				s1.setDate(2, fecha);
				s1.setFloat(3, total);
				s1.setInt(4, 0);
				ResultSet resultado = s1.executeQuery();
				
				if(resultado.next())
					cod = resultado.getInt("codigo");
			}

    	} catch(Exception e) {
    		System.err.println("Error creando pedido");
			System.err.println(e.getMessage());
			e.printStackTrace();
    	}
    	
    	cerrarConexionBD();
    	return cod;
    }
    
    public boolean crearDetalles(Integer id_pedido, Integer id_prod , Integer unidades, float precio) {
    	
    	abrirConexionBD();
    	
    	try {
    		String con = "INSERT INTO detalle (	codigo_pedido, codigo_producto, unidades, precio_unitario) VALUES (?,?,?,?)";
			PreparedStatement s = conexionBD.prepareStatement(con);
			
			s.setInt(1, id_pedido);
			s.setInt(2, id_prod);
			s.setInt(3, unidades);
			s.setFloat(4, precio);
			s.executeUpdate();
			
			cerrarConexionBD();
			
			return true;
    		
    	} catch(Exception e) {
    		System.err.println("Error creando detalle producto " + String.valueOf(id_prod));
			System.err.println(e.getMessage());
			e.printStackTrace();
    	}
    	return false;
    }
    
    public List<PedidoBD> obtenerPedidosBD(int persona) {
    	ArrayList<PedidoBD> pedidos = new ArrayList<>();
    	
    	abrirConexionBD();
    	
    	try {
    		String query = "SELECT * FROM pedidos WHERE persona=?";
    		PreparedStatement s1 = conexionBD.prepareStatement(query);
    		
    		s1.setInt(1, persona);
			ResultSet resultado = s1.executeQuery();
    		
    		while(resultado.next()){
    			PedidoBD pd = new PedidoBD();
    			pd.setCodigo(resultado.getInt("codigo"));
    			pd.setPersona(resultado.getInt("persona"));
    			pd.setFecha(resultado.getDate("fecha"));
    			pd.setImporte(resultado.getFloat("importe"));
    			pd.setEstado(resultado.getInt("estado"));
    			pedidos.add(pd);
    		}
    		
    	} catch (Exception e) {
    		System.err.println("Error ejecutando la consulta a la base de datos de pedidos");
    		e.printStackTrace();
    	}
    	
    	cerrarConexionBD();
    	return pedidos;
    }
    public String obtenerProductoporID(int cod) {
    	String prod = "";
    	abrirConexionBD();
    	
    	try {
	    	String query = "SELECT * FROM productos WHERE codigo=?";
	    	PreparedStatement s1 = conexionBD.prepareStatement(query);
	    	
	    	s1.setInt(1, cod);
	    	ResultSet resultado = s1.executeQuery();
	    	
	    	if(resultado.next())
	    		prod = resultado.getString("descripcion");
	    	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	cerrarConexionBD();
    	return prod;
    }
	public List<Detalle> obtenerDetalles(Integer cod) {
		ArrayList<Detalle> detalles = new ArrayList<>();
    	
    	abrirConexionBD();
    	
    	try {
    		String query = "SELECT * FROM detalle WHERE codigo_pedido=?";
    		PreparedStatement s1 = conexionBD.prepareStatement(query);
    		
    		s1.setInt(1, cod);
			ResultSet resultado = s1.executeQuery();
    		
    		while(resultado.next()){
    			Detalle d = new Detalle();
    			d.setC_pedido(cod);
    			d.setId_producto(resultado.getInt("codigo_producto"));
    			d.setUds(resultado.getInt("unidades"));
    			d.setPrecio(resultado.getFloat("precio_unitario"));
    			detalles.add(d);
    		}
    		
    	} catch (Exception e) {
    		System.err.println("Error ejecutando la consulta a la base de datos de detalles");
    		e.printStackTrace();
    	}
    	
    	cerrarConexionBD();
    	return detalles;
	}

    private final static String SQL_CANCELA_PEDIDO = "UPDATE pedidos SET estado = 3 WHERE codigo = ?";

	public boolean cancelarPedido(Integer cod) {
		boolean actualizado = false;
        
        abrirConexionBD();
        
        
        try {

            PreparedStatement ps = conexionBD.prepareStatement(SQL_CANCELA_PEDIDO);
            
            ps.setInt(1, cod);
            ps.execute();
            
            actualizado = (ps.getUpdateCount()>0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return actualizado;
	}
	
}