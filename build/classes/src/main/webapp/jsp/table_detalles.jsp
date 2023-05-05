<%@page contentType="text/html charset=ISO-8859-1"
	import="java.util.List, pr2.*, java.sql.Date" pageEncoding="ISO8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modificar usuario</title>
</head>
<body>
<%
	List<Detalle> detalles = (List<Detalle>) session.getAttribute("detalles");
%>
		<div class="table-responsive ">
		  <table class="table table-striped table-bordered caption-top">
		    <caption>Detalles del pedido</caption>
		 	 <thead>
			  	  <tr>
					<th> Cod. Pedido </th>
					<th> Producto </th>
					<th> Unidades </th>
					<th> Precio unitario </th>
					<th> Subtotal </th>
				  </tr>
		 	 </thead>
		 	 <tbody>
	<% 	float total = 0;
		for(Detalle detalle:detalles){
				int codigo = detalle.getC_pedido();
				String prod = detalle.getProducto();
				int uds = detalle.getUds();
				float precio_uni = detalle.getPrecio();
				total += precio_uni * uds;
	
		%>
			<tr>
				<td> <%= codigo %> </td>
				<td> <%= prod %> </td>
				<td> <%= uds %> </td>
				<td> <%= precio_uni %>&euro;</td>
                <td> <%= precio_uni * uds%>&euro;</td>
			</tr>	
	<%} %>
			</tbody>
			</table>
		</div>
		<button onclick="Cargar('pedido.html','cuerpo')" class="btn btn-primary">Volver</button>
</body>
</html>