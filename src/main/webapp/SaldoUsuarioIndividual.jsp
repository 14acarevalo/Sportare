<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listar_datos_economicos</title>
 <link rel="stylesheet" href="VisualSaldo.css">


</head>
<body>
<header>
        <div class="logo"> <img src="PicturesPortada/SPORTARE.png" alt="Logo" width="100px" height="100px"></div>
        <div class="menu"> 
            <ul>
                <li> <a href="MenuUsuarios.jsp">Inicio</a></li>
                <li> <a href="Pag_contactaConmigo.html">Contacto</a></li>
                <li><a href="Pag_informacion.html"> Informacion</a></li>
            </ul>
        </div>
        <div class="presentacion">
            <p class="Bienvenidos"><h1>Bienvenido a <span class="nombremarca">LOS DATOS DE TUS ECONOMICOS DE TU ENTIDAD</span></h1>
            <p>Podrás ver todos los movimientos de tu entidad en esta página</p>
         </div>
    </header>
        
    <main>
        <div id="resultadosEconomicos" class="tablaDatos"></div>
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
    fetch('ListarSaldo?op=1&idUsuario=' + idUsuario)
        .then(res => {
            if (!res.ok) {
                throw new Error('Ocurrió un error al obtener los datos del usuario.');
            }
            return res.json();
        })
        .then(res => pintarResultadosEconomicos(res))
        .catch(error => console.error('Error:', error));
}


function pintarResultadosEconomicos(money) {
    var html = "<table>"; 
    html += "<thead><tr>";
    html += "<th>ID Ingreso</th>";
    html += "<th>Ingresos</th>";
    html += "<th>Fecha Ingresos</th>";
    html += "<th>ID Usuario</th>";
    html += "<th>Saldo</th>";
    html += "<th>Descripción del Servicio</th>";
    html += "</tr></thead>";

    html += "<tbody>";
    for (var i = 0; i < money.length; i++) {
        html += "<tr>";
        html += "<td>" + money[i].idIngreso + "</td>";
        html += "<td>" + money[i].ingresos + "</td>";
        html += "<td>" + money[i].fechaIngreso + "</td>";
        html += "<td>" + money[i].idUsuario + "</td>";
        html += "<td>" + money[i].saldo + "</td>";
        html += "<td>" + money[i].descripcion + "</td>";
        html += "</tr>";
    }
    html += "</tbody>";
    html += "</table>";
    document.getElementById("resultadosEconomicos").innerHTML = html;
}


    </script>


</body>
</html>