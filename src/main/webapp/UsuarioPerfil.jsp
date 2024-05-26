<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
    <link rel="stylesheet" href="PagVisualizarDatosPerfilUsuario.css">


</head>
<body>
<header>

        <div class="logo"> <img src="PicturesPortada/SPORTARE.png" alt="Logo" width="100px" height="100px"></div>
        <div class="menu"> 
            <ul>
                <li> <a href="MenuUsuarios.jsp">Inicio</a></li>
                <li> <a href="PagPerfilUsuario.jsp">Página Usuario</a></li>
                <li> <a href="Pag_informacion.html"> Informacion</a></li>
                <li> <a href="UsuarioPerfil.jsp"> Refrescar pantalla</a></li>
                
            </ul>
        </div>
        <div class="presentacion">
            <p class="Bienvenidos"><h1>Bienvenido a <span class="nombremarca">TUS DATOS</span></h1>
            <p>Todos los datos los podrás encontrar en este apartado</p>
            		
            
         </div>
    </header>
        
    <main>
        <div id="resultados" class="tablaDatos"></div>
    </main>

    <footer>
        <div>
            <a href="Pag_contactaConmigo.html">Contacta con nosotros</a>
            <p>Todos tus datos en esta página</p>
            <p>Visítanos en:</p>
            <div>
                <table>
                    <tr>
                        <td><a href="https://www.instagram.com/corralsemueve/?hl=es"><img src="Logos redes/logo insta.png" alt="LogoInsta" width="30pt" height="30pt"></a></td>
                        <td><a href="https://www.facebook.com/corral.semueve"><img src="Logos redes/face.png" alt="LogoFace" width="30pt" height="30pt"></a></td>
                        <td><a href="https://www.linkedin.com/in/alberto-c-162930135/"><img src="Logos redes/linke.png" alt="LogoFace" width="30pt" height="30pt"></a></td>
                    </tr>
                </table>
            </div>
        </div>   
    </footer>
   
   
<!-- Obtén el ID del usuario desde la sesión y guárdalo en un campo oculto -->
<input type="hidden" id="idUsuario" value="<%= session.getAttribute("idUsuario") %>">            		 
   <script type="text/javascript">
    window.onload = function() {
    	var idUsuario = document.getElementById("idUsuario").value;
        console.log(idUsuario); // Imprimir el valor en la consola
        llamada(idUsuario);
        
        
    };

    function llamada(idUsuario) {
        fetch('PruebaListar?op=1&idUsuario=' + idUsuario)
            .then(res => {
                if (!res.ok) {
                    throw new Error('Ocurrió un error al obtener los datos del usuario.');
                }
                return res.json();
            })
            .then(res => pintarResultados(res))
            .catch(error => console.error('Error:', error));
    }

    function pintarResultados(usuarios) {
        var html = "<table>"; 
        html += "<thead><tr>";
        html += "<th>Datos</th>";
        html += "<th>Datos personales del usuario</th>";
        html += "</tr></thead>";

        html += "<tbody>";
       
        html += "<tr><td>ID</td><td>" + usuarios.idUsuario + "</td></tr>";
        html += "<tr><td>Nombre</td><td>" + usuarios.nombre + "</td></tr>";
        html += "<tr><td>Apellidos</td><td>" + usuarios.apellidos + "</td></tr>";
        html += "<tr><td>Correo Electrónico</td><td>" + usuarios.correoElectronico + "</td></tr>";
        html += "<tr><td>Fecha de Nacimiento</td><td>" + usuarios.fechaNacimiento + "</td></tr>";
        html += "<tr><td>DNI</td><td>" + usuarios.dni + "</td></tr>";
        html += "<tr><td>Nacionalidad</td><td>" + usuarios.nacionalidad + "</td></tr>";
        html += "<tr><td>Ciudad</td><td>" + usuarios.ciudad + "</td></tr>";
        html += "<tr><td>Dirección</td><td>" + usuarios.direccion + "</td></tr>";
        html += "<tr><td>Teléfono</td><td>" + usuarios.telefono + "</td></tr>";
        html += "<tr><td>Código Postal</td><td>" + usuarios.codigoPostal + "</td></tr>";
        html += "<tr><td>Género</td><td>" + usuarios.genero + "</td></tr>";
        html += "<tr><td>Permiso</td><td>" + usuarios.permiso + "</td></tr>";

        html += "</tbody>";
        html += "</table>";
        document.getElementById("resultados").innerHTML = html;
    }

</script>
    
</body>
            	

</html>