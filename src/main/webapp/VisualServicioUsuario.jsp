<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="PagVisualizarDatosUsuarios.css">
</head>
<body>
    <header>
        <div class="logo"> <img src="PicturesPortada/SPORTARE.png" alt="Logo" width="100px" height="100px"></div>
        <div class="menu"> 
            <ul>
                <li> <a href="MenuUsuario.jsp">Inicio</a></li>
                <li> <a href="Pag_contactaConmigo.html">Contacto</a></li>
                <li><a href="Pag_informacion.html"> Informacion</a></li>
            </ul>
        </div>
        <div class="presentacion">
            <p class="Bienvenidos"><h1>Bienvenido a <span class="nombremarca">LOS DATOS DE LAS RESERVAS</span></h1>
            <p>Todos los datos los podrás encontrar en este apartado</p>
         </div>
    </header>
        
    <main>
        <div id="resultadosDeportivos" class="tablaDatos"></div>
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
<input type="hidden" id="idUsuario" value="<%= session.getAttribute("idUsuario") %>">            		 
    
    
    <script type="text/javascript">

window.onload = function() {
	var idUsuario = document.getElementById("idUsuario").value;
    console.log(idUsuario); // Imprimir el valor en la consola
    llamada(idUsuario);
    
    
};

function llamada(idUsuario) {
    fetch('ClaseUsuarioListaServicio?op=1&idUsuario=' + idUsuario)
        .then(res => {
            if (!res.ok) {
                throw new Error('Ocurrió un error al obtener los datos del usuario.');
            }
            return res.json();
        })
        .then(res => pintarResultadosDeportivos(res))
        .catch(error => console.error('Error:', error));
}


function pintarResultadosDeportivos(servicio) {
    var html = "<table>"; 
    html += "<thead><tr>";
    html += "<th>Identificador del servicio</th>";
    html += "<th>Nombre del servicio</th>";
    html += "<th>Hora inicio</th>";
    html += "<th>Minuto inicio</th>";
    html += "<th>Hora Finalización</th>";
    html += "<th>Minuto final</th>";
    html += "<th>Dia</th>";
    html += "<th>Mes</th>";
    html += "<th>Id Usuario</th>";
    html += "</tr></thead>";

    html += "<tbody>";
    for (var i = 0; i < servicio.length; i++) {
        html += "<tr>";
        html += "<td>" + servicio[i].idServicio + "</td>";
        html += "<td>" + servicio[i].nombre + "</td>";
        html += "<td>" + servicio[i].horaIniciacion + "</td>";
        html += "<td>" + servicio[i].minInicio + "</td>";
        html += "<td>" + servicio[i].horaFinalizacion + "</td>";
        html += "<td>" + servicio[i].minFinal + "</td>";
        html += "<td>" + servicio[i].dia + "</td>";
        html += "<td>" + servicio[i].mes + "</td>";
        html += "<td>" + servicio[i].idUsuario + "</td>";

        html += "</tr>";
    }
    html += "</tbody>";
    html += "</table>";
    document.getElementById("resultadosDeportivos").innerHTML = html;
}


    </script>
</body>
</html>