<%@page contentType="text/html charset=ISO-8859-1" import="java.util.List, pr2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<%
	//Utilizamos una variable en la sesiï¿½n para informar de los mensajes de Error
	String mensaje = (String)session.getAttribute("mensaje");
	if (mensaje != null) { //Eliminamos el mensaje consumido
		session.removeAttribute("mensaje");
%>
<div class="alert alert-danger" role="alert">
  <%=mensaje%>
</div>  
<%
	}
	if ((session.getAttribute("usuario") == null) || ((Integer)session.getAttribute("usuario") <=0 ))
		{ //Mostramos el formulario para la introducciï¿½n del usuario y la clave
		%>
		
		<!-- Login -->
		<form  method="post" onsubmit="ProcesarForm(this,'Login','cuerpo');return false">
			<input type="hidden" name="url" value="./jsp/loginTienda.jsp">
			  <div class="mb-3 mt-3 ">
			    <label for="usuario" class="form-label">Usuario:</label>
			    <input type="text" class="form-control" id="usuario" placeholder="Usuario..." name="usuario" required>
			    <div class="valid-feedback"><i class="fa-solid fa-check"></i></div>
			    <div class="invalid-feedback">Por favor, rellena este campo.</div>
			  </div>
			  <div class="mb-3">
			    <label for="clave" class="form-label">Contraseña:</label>
			    <input type="password" class="form-control" id="clave" placeholder="Contraseña..." name="clave" required>
			    <div class="valid-feedback"><i class="fa-solid fa-check"></i></div>
			    <div class="invalid-feedback">Por favor, rellena este campo.</div>
			  </div>
			  <div class="container mt-3">
			  	<div class="row">
			  		<div class="col-sm-6">
			  			<button type="submit" class="btn btn-primary">Entrar</button>
			  		</div>
			  		<div class ="col-sm-6">
			  			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">¡Registrarse!</button>
			  		</div>
			  	</div>
			  </div>  
	</form>
		<!-- Modal para registro -->
		
		<!-- The Modal -->
		<div class="modal" id="myModal">
		  <div class="modal-dialog">
		    <div class="modal-content">
		
		      <!-- Modal Header -->
		    <div class="modal-header">
		        <h4 class="modal-title">Formulario de Registro</h4>
		        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
		    </div>
		
		      <!-- Modal body -->
		    <div class="modal-body">
		        <form method="post" onsubmit="ProcesarForm(this,'Registro','cuerpo');return false">		
					<input type="hidden" name="url" value="./jsp/loginTienda.jsp">
					<div class="mb-3">
						<label for="nombre" class="form-label">Nombre:</label>
						<input type="text" class="form-control" id="nombre" placeholder="Nombre..." name="nombre" required>
						<label for="apellidos" class="form-label">Apellidos:</label>
						<input type="text" class="form-control" id="apellidos" placeholder="Apellidos..." name="apellidos" required>
						<label for="usuario" class="form-label">Usuario:</label>
						<input type="text" class="form-control" id="usuario" placeholder="Usuario..." name="usuario" required>
						<label for="clave" class="form-label">Contraseña:</label>
						<input type="password" class="form-control" id="clave" placeholder="Contraseña..." name="clave" required>
						<label for="domicilio" class="form-label">Domicilio:</label>
						<input type="text" class="form-control" id="domicilio" placeholder="Domicilio..." name="domicilio" required>
						<label for="poblacion" class="form-label">Población:</label>
						<input type="text" class="form-control" id="poblacion" placeholder="Población..." name="poblacion" required>
						<label for="provincia" class="form-label">Provincia:</label>
						<input type="text" class="form-control" id="provincia" placeholder="Provincia..." name="provincia" required>
						<label for="cp" class="form-label">Codigo Postal:</label>
						<input type="text" class="form-control" id="cp" placeholder="Código Postal..." name="cp" required>
						<label for="telefono" class="form-label">Teléfono:</label>
						<input type="text" class="form-control" id="telefono" placeholder="Teléfono..." name="telefono" required>
		      		</div>
		      		<div class="col-sm-6">
			  			<button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Registrarse</button>
			  		</div>
			  	</form>
				</div>

		    </div>
		  </div>
		</div>
		<%
		}
		else {
		//Si existe un usuario, se muestran las opciones del apartado del usuario
		%>
		<div class = row>
			<h3 class="text-center">Panel de control de Usuario</h3>
		</div>
		<div class = "row">
			<div class = "col">
				<div class="card text-center bg-light">
				  <div class="card-body">
				    <h5 class="card-title">Modificar mis datos</h5>
				    <p class="card-text">Modificar los datos relacionados con el usuario.</p>
				    <button onclick="Cargar('usuario.html','cuerpo')" class="btn btn-primary">Modificar</button>
				  </div>
				</div>
			</div>
			<div class = "col">
				<div class="card text-center bg-light">
				  <div class="card-body">
				    <h5 class="card-title">Modificar mis pedidos</h5>
				    <p class="card-text">Modificar/Cancelar mis pedidos y toda la informacion relacionada con ellos.</p>
				    <button onclick="Cargar('pedido.html','cuerpo')" class="btn btn-primary">Modificar</button>
				  </div>
				</div>
			</div>
		</div>
		<div class = "row">
			<div class = "col">
				<div class="d-flex justify-content-center">
					<form method="post" onsubmit="ProcesarForm(this,'Logout','cuerpo');return false">
						<input type="hidden" name="url" value="./jsp/loginTienda.jsp">
						<button type="submit" class="btn btn-danger">Cerrar Sesion</button>
					</form>
				</div>
			</div>
		</div>
		
		<%
		}
		%> 

</body>
</html>