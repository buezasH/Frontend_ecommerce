<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, pr2.*"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<title>Resguardo</title>
</head>
<body>
	<%
		UsuarioBD user = (UsuarioBD)session.getAttribute("cabecera");
	    Integer usuario = (Integer)session.getAttribute("usuario");
		ArrayList<Producto> pedido = (ArrayList<Producto>)session.getAttribute("pedido");
		%>
	<div class="row-ch">
		<div class="col-75">
			<div class="container">
				<form method="post" onsubmit="ProcesarForm(this,'FormalizarPedido.html?','cuerpo');return false" >

					<div class="row">
						<div class="col-50">
						 <input type="hidden" name="codigo" value="<%=usuario%>"/>
							<h3>Direccion de Facturaci&oacute;n</h3>
							<label for="fname"><i class="fa fa-user"></i> Nombre</label> 
							<input type="text" id="fname" name="nombre" value="<%=user.getNombre()%>">
							<label for="fname"><i class="fa fa-user"></i> Apellidos</label> 
							<input type="text" id="fname" name="apellidos" value="<%=user.getApellidos()%>">
							<label for="adr"><i class="fa fa-address-card-o"></i> Direcci&oacute;n</label> 
							<input type="text" id="adr" name="domicilio" value="<%=user.getDomicilio()%>"> 
							<label for="city"><i class="fa fa-institution"></i> Poblaci&oacute;n</label> 
							<input type="text" id="city" name="poblacion" value="<%=user.getPoblacion()%>">

							<div class="row">
								<div class="col-50">
									<label for="state">Provincia</label> 
									<input type="text" id="state" name="provincia" value="<%=user.getProvincia() %>">
								</div>
								<div class="col-50">
									<label for="zip">Codigo Postal</label>
									<input type="text" id="zip" name="cp" value="<%=user.getCp()%>">
								</div>
							</div>
						</div>

						<div class="col-50">
							<h3>Pasarela de Pago</h3>
							<label for="fname">Tarjetas admitidas</label>
							<div class="icon-container">
								<i class="fa fa-cc-visa" style="color: navy;"></i> <i
									class="fa fa-cc-amex" style="color: blue;"></i> <i
									class="fa fa-cc-mastercard" style="color: red;"></i> <i
									class="fa fa-cc-discover" style="color: orange;"></i>
							</div>
							<label for="cname">Nombre </label> <input type="text" id="cname"
								name="cardname"> <label for="ccnum">N&uacute;mero</label>
							<input type="text" id="ccnum" name="cardnumber">

							<div class="row">
								<div class="col-50">
									<label for="expyear">Fecha de expiraci&oacute;n</label> 
									<input type="month" id="expyear" name="expyear">
								</div>
								<div class="col-50">
									<label for="cvv">CVV</label> <input type="text" id="cvv"
										name="cvv">
								</div>
							</div>
						</div>

					</div>
					<label> <input type="checkbox" checked="checked"
						name="sameadr"> La direcci&oacute;n de envio es la misma
						que la de facturaci&oacute;n

					</label> <input type="submit" value="Pagar" class="btn">
				</form>
			</div>
		</div>
		<%
		int total = 0;
		for(Producto producto : pedido){
			total += producto.getCantidad();
		}
		%>
		<div class="col-25">
			<div class="container">
				<h4>
					Carrito <span class="price" style="color: black"> 
					<i class="fa fa-shopping-cart"></i> <b><%=total %></b>
					</span>
				</h4>
				<%
				int preciotot = 0;
				for(Producto producto : pedido){
					String nombre = producto.getNombre();
					int cant = producto.getCantidad();
					float precio = producto.getPrecio();
					preciotot += precio*cant;
				%>
				<p>
					<a href="#"><%=nombre %> x<%=cant %></a> <span class="price"><%=precio * cant%>&euro;</span>
				</p>
				<hr>
				<%} %>
				<p>
					Total <span class="price" style="color: black"><b><%=preciotot %>&euro;</b></span>
				</p>
			</div>
		</div>

	</div>
</body>
</html>