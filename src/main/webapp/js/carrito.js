function obtenerCarrito() {
    cadena = localStorage.getItem('carrito');
    var carrito = new Object();

    if (cadena != null)
        carrito = JSON.parse(cadena);

    console.log(carrito);

    return carrito;
}

function guardarCarrito(carrito) {

    cadena = JSON.stringify(carrito);
    console.log("Guardando carrito: " + cadena);
    localStorage.setItem('carrito', cadena);

}

function MostrarCarrito(){
    var capa = document.getElementById("carrito");
    var total = 0;
    let rowCount = capa.rows.length;
    while (rowCount > 0) {
        capa.deleteRow(0);
        rowCount = capa.rows.length;
    }

    var carrito = obtenerCarrito();
    for (var id in carrito) {
        var producto = carrito[id];
        var cantidad = producto.cantidad;
        var precio = producto.precio;
        var subtotal = cantidad * precio;
        total += subtotal;
        let row = capa.insertRow(0);
        let cell1 = row.insertCell(0); cell1.innerHTML = "<img class = 'img-carr justify-content-center' src='img/" + producto.imagen + "' alt=' " + producto.nombre + "'>";
        let cell2 = row.insertCell(1); cell2.innerHTML = producto.nombre;
        let cell3 = row.insertCell(2); cell3.innerHTML = producto.cantidad;
        let cell4 = row.insertCell(3); cell4.innerHTML = producto.precio + "€";
        let cell5 = row.insertCell(4); cell5.innerHTML = subtotal + "€";
        let cell6 = row.insertCell(5); cell6.innerHTML = "<button type='button' class='btn btn-danger' onclick='eliminarProductoDeCarrito(" + producto.id + ")'>Eliminar</button>";
        let cell7 = row.insertCell(6); cell7.innerHTML = "<button type='button' class='btn btn-success' onclick='modificarProductoEnCarrito(" + producto.id + ", 1)'>+</button>" + "<button type='button' class='btn btn-danger' onclick='modificarProductoEnCarrito(" + producto.id + ", -1)'>-</button>";

    }
    document.getElementById("total").innerHTML = "<h3>TOTAL:</h3> <h5>" + total + " € </h5>";
}

function addProductoACarrito(identificador, nombre, precio, img, existencias) {

    var carrito = new obtenerCarrito();

    if(carrito[identificador]){
        carrito[identificador].cantidad++;
    }
    else{
        var producto = new Object(); // Objeto que deseamos almacenar
        producto.id = identificador; // Identificador único del objeto
        producto.nombre = nombre; // Nombre del objeto 
        producto.cantidad = 1; // Cantidad que tenemos en el carrito (1 por ejemplo) 
        producto.precio = precio; // Precio unitario de cada elemento (100.0 por ejemplo)
        producto.imagen = img; //Imagen del producto
        producto.exis = existencias; //Existencias del producto
        carrito[identificador] = producto; 
    }
	alert("Producto añadido al carrito correctamente");
	
    guardarCarrito(carrito);
}

function modificarProductoEnCarrito(identificador, cantidad) {
    var carrito = obtenerCarrito();

	if(cantidad > 0)
	{
		if(carrito[identificador].cantidad < carrito[identificador].exis)
    		carrito[identificador].cantidad += cantidad;
    	else
    		carrito[identificador].cantidad = parseInt(carrito[identificador].exis);
	}
	else
    	carrito[identificador].cantidad += cantidad;
    	
    if(carrito[identificador].cantidad == 0){
        eliminarProductoDeCarrito(identificador);
    }
    else{
        guardarCarrito(carrito);
    }
    MostrarCarrito();
}

function eliminarProductoDeCarrito(identificador) {
    var carrito = obtenerCarrito();
    delete carrito[identificador];
    guardarCarrito(carrito);
    MostrarCarrito();
}

function eliminarTodo() {
    var carrito = obtenerCarrito();
    carrito = new Object();
    guardarCarrito(carrito);
    MostrarCarrito();
}