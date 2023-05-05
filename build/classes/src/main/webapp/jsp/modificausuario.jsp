<%@page contentType="text/html charset=ISO-8859-1"
	import="java.util.List, pr2.*" pageEncoding="ISO8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modificar usuario</title>
</head>
<body>
<%
	UsuarioBD user = (UsuarioBD) session.getAttribute("datos-usuario");
	//Utilizamos una variable en la sesi�n para informar de los mensajes de Error
	String mensaje = (String)session.getAttribute("mensaje");
	if (mensaje != null) { //Eliminamos el mensaje consumido
		session.removeAttribute("mensaje");
%>
	<div class="alert alert-success" role="alert">
	  <%=mensaje%>
	</div>  
 	<%} %>
	<div class="row-ch">
		<div class="col-75">
			<div class="container">
				<form method="post" onsubmit="ProcesarForm(this,'usuario.html','cuerpo');return false">
					<input type="hidden" name="url" value="./jsp/modificausuario.jsp">
					<div class="row">
						<div class="col">
							<h3>Datos del Usuario</h3>
							<label for="fuser"><i class="fa fa-user"></i> Usuario</label> 
							<input type="text" id="fname" name="usuario" value="<%=user.getUsuario()%>" readonly>
							<label for="key"><i class="fa-solid fa-key"></i> Clave</label> 
							<input type="password" id="fname" name="clave" value="<%=user.getClave()%>">
							<label for="fname"><i class="fa fa-user"></i> Nombre</label> 
							<input type="text" id="fname" name="nombre" value="<%=user.getNombre()%>"> 
							<label for="fape"><i class="fa fa-user"></i> Apellidos</label> 
							<input type="text" id="fname" name="apellidos" value="<%=user.getApellidos()%>"> 
							<label for="adr"><i class="fa fa-address-card-o"></i> Direcci&oacute;n</label> 
							<input type="text" id="adr" name="domicilio" value="<%=user.getDomicilio()%>"> 
							<label for="city"><i class="fa fa-institution"></i> Poblaci&oacute;n</label> 
							<input type="text" id="city" name="poblacion" value="<%=user.getPoblacion()%>">

							<div class="row">
								<div class="col-50">
									<label for="state">Provincia</label> <input type="text"
										id="state" name="provincia" value="<%=user.getProvincia()%>">
								</div>
								<div class="col-50">
									<label for="zip">Codigo Postal</label> <input type="number"
										id="zip" name="cp" value="<%=user.getCp()%>">
								</div>
							</div>
							<label for="phone"><i class="fa-solid fa-phone"></i> Tel&eacute;fono</label> 
							<input type="number" id="phone" name="phone" value="<%=user.getTelefono()%>">
						</div>
					</div>
					<button type="submit" class="btn btn-primary">Modificar</button>
				</form>
			</div>
		</div>
	</div>


</body>
</html>