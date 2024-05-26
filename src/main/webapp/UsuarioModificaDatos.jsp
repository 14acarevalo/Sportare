<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<title>Pagina_para_crear_usuario</title>
    <link rel="stylesheet" href="Pag_crearUsuario.css">
<script type="text/javascript">
function llamada(idUsuario, op){
    fetch('ClaseUsuarioModificaSuPerfil?idUsuario='+idUsuario+"&op="+op)
    .then(response => response.json())
    .then(data => pintarFormulario(data))
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}



function pintarFormulario(usuarios) {
	console.log("NOFUNCIONA")

    document.getElementById("nombre").value = usuarios.nombre;
    document.getElementById("apellidos").value = usuarios.apellidos;
    document.getElementById("correoElectronico").value = usuarios.correoElectronico;
    document.getElementById("dni").value = usuarios.dni;
    document.getElementById("fechaNacimiento").value = usuarios.fechaNacimiento;
    document.getElementById("pais").value = usuarios.nacionalidad;
    document.getElementById("ciudad").value = usuarios.ciudad;
    document.getElementById("direccion").value = usuarios.direccion;
    document.getElementById("calle").value = usuarios.calle;
    document.getElementById("codigoPostal").value = usuarios.codigoPostal;
    document.getElementById("telefono").value = usuarios.telefono;
    document.getElementById("genero").value = usuarios.genero;
}

window.onload = function(){
    let idUsuario = getParameterByName("idUsuario");
    let opcion = getParameterByName("op");
    llamada(idUsuario, opcion);
}
</script>
</head>
<body>



<header>
<div class="logo"> <img src="PicturesPortada/SPORTARE.png" alt="logo" width="300px" height="300px"></div>
 <p>Actualiza los datos de los usuarios rellenando los campos que deseas cambiar. </p>
</header>

<main>
 <div class="cuestionario">

 <form name="ModificarUsuario" action="ClaseUsuarioModificaSuPerfil" method="post">
     <input type="hidden" name="idUsuario"   value="<%= session.getAttribute("idUsuario") %>">
 
<label for="nombre"> Nombre </label>
<input type="text" id="nombre" name="nombre" placeholder="Nombre"><br>
<label for="apellidos"> Apellidos </label>
<input type="text" id="apellidos" name="apellidos" placeholder="Apellidos" required><br>
<label for="correoElectronico"> Correo Electronico </label>
<input type="text" id="correoElectronico" name="correoElectronico" placeholder="Correo electronico" required><br>
<label for="dni"> DNI </label>
<input type="text" id="dni" name="dni" placeholder="DNI" required><br>
<label for="fechaNacimiento"> Fecha de Nacimiento </label>
<input type="text" id="fechaNacimiento" name="fechaNacimiento" placeholder="dd-mm-aa" required><br>
<label for="nacionalidad"> Nacionalidad </label>
<input type="text" id="pais" name="pais" placeholder="Nacionalidad" required><br>
<label for="ciudad"> Ciudad </label>
<input type="text" id="ciudad" name="ciudad" placeholder="Ciudad" required><br>
<label for="direccion"> Dirección </label>
<input type="text" id="direccion" name="direccion" placeholder="Direccion" required><br>
<label for="calle"> Calle </label>
<input type="text" id="calle" name="calle" placeholder="Calle" required><br>
<label for="codigoPostal"> Codigo Postal </label>
<input type="text" id="codigoPostal" name="codigoPostal" placeholder="Codigo Postal" required><br>
<label for="telefono"> Telefono </label>
<input type="text" id="telefono" name="telefono" placeholder="Telefono" required><br>
<label for="genero"> Genero </label>
<input type="text" id="genero" name="genero" placeholder="Genero" required><br>
 <input type="submit" value="Actualizar">

</form>

</div>
</main>
<footer>
<div>
        <a href="Pag_contactaConmigo.html">Contacta con nosotros</a>
        <p>Servicio deportivo ligado a la gestión deportiva</p>
        <p>Estará destinado para toda entidad, ayuntamiento, club, organización que desee organizar su actividad deportiva</p>
              <p>Puedes encontrarnos en: </p>
              <div>
                  <table>
                      <th>
                          <tr><a href="https://www.instagram.com/corralsemueve/?hl=es"> <img src="Logos redes/logo insta.png" alt="LogoInsta" width="30pt" height="30pt"> </a> </tr>
                          <tr><a href="https://www.facebook.com/corral.semueve"> <img src="Logos redes/face.png" alt="LogoFace" width="30pt" height="30pt"> </a></tr>
                          <tr><a href="https://www.linkedin.com/in/alberto-c-162930135/"> </a><img src="Logos redes/linke.png" alt="LogoFace" width="30pt" height="30pt"> </tr>
                      </th>
                  </table>
              </div>
      </div>   
</footer>


</body>
</html>