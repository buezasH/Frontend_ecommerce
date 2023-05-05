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
	List<PedidoBD> pedidos = (List<PedidoBD>) session.getAttribute("pedidos");
	//Utilizamos una variable en la sesi�n para informar de los mensajes de Error
	String mensaje = (String)session.getAttribute("mensaje");
	if (mensaje != null) { //Eliminamos el mensaje consumido
		session.removeAttribute("mensaje");
%>
	<div class="alert alert-success" role="alert">
	  <%=mensaje%>
	</div>  
 	<%} %>
		<div class="table-responsive ">
		  <table class="table table-striped table-bordered caption-top">
		    <caption>Listado de pedidos</caption>
		 	 <thead>
			  	  <tr>
					<th> Codigo </th>
					<th> Fecha </th>
					<th> Importe </th>
					<th> Estado </th>
					<th> Detalles </th>
					<th>  </th>
				  </tr>
		 	 </thead>
		 	 <tbody>
	<% for(PedidoBD pedido:pedidos){
				int codigo = pedido.getCodigo();
				Date fecha = pedido.getFecha();
				float importe = pedido.getImporte();
				int estado = pedido.getEstado();
	
		%>
			<tr>
				<td> <%= codigo %> </td>
				<td> <%= fecha %> </td>
				<td> <%= importe %>&euro; </td>
				<td> <%if(estado == 0) {%>
                			Pendiente
                		<% } else if(estado == 1) {%>
                			Enviado
                			<% } else if(estado == 2) {%>
                				Recibido
                				<% } else if(estado == 3) {%>
                					Cancelado
                					<% } else {%>
                							Desconocido
                					<% }%>
                </td>
                <td class = "text-center"><button type="button" class="btn btn-warning" onclick = "Cargar('detalles.html?codigo=<%=codigo %>', 'cuerpo')"> <i class="fa-solid fa-box-open"></i> </button></td>
                <td class = "text-center"> <%if(estado == 0){ %><button type="button" class="btn btn-danger" onclick = "Cargar('cancelar.html?codigo=<%=codigo %>', 'cuerpo')"> Cancelar </button> <%} %></td>

			</tr>	
	<%} %>
			</tbody>
			</table>
		</div>
</body>
</html>