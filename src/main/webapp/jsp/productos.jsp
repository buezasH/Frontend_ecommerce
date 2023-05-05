<%@page contentType="text/html charset=ISO-8859-1" import="java.util.List, pr2.*" pageEncoding="ISO8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>&nbsp;</title>
</head>
<body>
	<%
	AccesoBD con = AccesoBD.getInstance();
	List<ProductoBD> productos = con.obtenerProductosBD();
	%>
	<div class="mt-4 p-5 bg-secondary text-white rounded">
		<p class="text-center h2">¡Descubre nuestra selección de productos!</p>
	</div>
	<div class="wrapper">
			<div class="container">
				<div class="row g-1">
		<%
		for(ProductoBD producto : productos){
			int codigo = producto.getId();
			String descripcion = producto.getDescripcion();
			float precio = producto.getPrecio();
			int existencias = producto.getStock();
			String imagen = producto.getImagen();
		%>
					<div class="col-md-3">
						<div class="card p-3">
							<div class="text-center"> <a href="#" onclick="Cargar('./html/producto1.html','cuerpo')"><img src="./img/<%=imagen%>" alt="<%=descripcion%>" width="200"> </a></div>
							<div class="product-details"> <span class="font-weight-bold d-block"><%=precio%>&euro;</span> <span><%=descripcion%></span>
								<div class="buttons d-flex flex-row">
									<%
										if (existencias > 0) {
									%>
									<button type="button" class="btn btn-success" onclick="addProductoACarrito('<%=codigo%>','<%=descripcion%>','<%=precio%>','<%=imagen%>', '<%=existencias%>')">
										Añadir al carrito!
									</button>
									<%
										}
										else {
									%>
										&nbsp;
									<%
										}
									%>
									 
								</div>
								<div class="weight"> </div>
							</div>
						</div>
					</div>
		<%
		}
		%>
				</div>
			</div>
		</div>
</body>
</html>