<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, pr2.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pedido final</title>
</head>
<body>
	<%
		UsuarioBD user = (UsuarioBD)session.getAttribute("cabecera");
	    Integer usuario = (Integer)session.getAttribute("usuario");
	    Integer idpedido = (Integer)session.getAttribute("id-pedido");
		ArrayList<Producto> pedido = (ArrayList<Producto>)session.getAttribute("pedido");
		session.removeAttribute("pedido");
		session.removeAttribute("cabecera");
		%>
	<div class="row-ch">
		<div class="col-75">
			<div class="container">
				<h3>Gracias <%=user.getUsuario() %> por tu pedido</h3>
				<label for="fname"><i class="fa fa-user"></i> Numero de pedido: #<%= idpedido%></label>
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope = "col">Nombre</th>
							<th scope = "col">Cantidad </th>
							<th scope = "col">Precio </th>
						</tr>
					</thead>
					<tbody>
				 <%
				 int preciotot = 0;
					for(Producto producto : pedido){
						String nombre = producto.getNombre();
						int cant = producto.getCantidad();
						float precio = producto.getPrecio();
						preciotot += precio*cant;
					%>
						<tr>
							<td><%=nombre %></td>
							<td><%=cant %></td>
							<td><span class="price"><%=precio * cant%>&euro;</span></td>
						</tr>
					<%} %>
					<tr>
						<td>Total </td>
						<td></td>
						<td><span class="price" style="color: black"><b><%=preciotot %>&euro;</b></span></td>
					</tr>
					</tbody>
					</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		localStorage.clear();
	</script>
</body>
</html>