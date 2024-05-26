<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Operacion Realizada</title>
<link rel="stylesheet" href="conseguido.css">
<script type="text/javascript">
function llamada() {
    var welcome = "Un mundo de posibilidades deportivas se te ofrecen a continuación. Disfruta del deporte en tu localidad. Recuerda mirar bien tu id de tu usuario, lo necesitarás para acceder a nuestra aplicación";
    alert(welcome);
}
</script>
</head>
<body onload="llamada()">
<header>
    <div class="logo"><img src="PicturesPortada/SPORTARE.png" alt="logocorral" width="100px" height="100px"></div>
</header>

<main>
    <div class="operacion">
        <p><strong>BIENVENIDO: </strong> <%= session.getAttribute("nombre") %></p>
        <a href="Pag_entrarUsuario.html">INSERTA TU USUARIO</a>
        <img src="Pictures_proyecto/conseguido.png" alt="">
    </div>
</main>

<footer>
    <div>
        <p>Servicio deportivo ligado a la gestión deportiva</p>
        <p>Puedes encontrarnos en: </p>
        <div>
            <table>
                <tr>
                    <td><a href="https://www.instagram.com/corralsemueve/?hl=es"><img src="Logos redes/logo insta.png" alt="LogoInsta" width="30pt" height="30pt"></a></td>
                    <td><a href="https://www.facebook.com/corral.semueve"><img src="Logos redes/face.png" alt="LogoFace" width="30pt" height="30pt"></a></td>
                    <td><a href="https://www.linkedin.com/in/alberto-c-162930135/"><img src="Logos redes/linke.png" alt="LogoLinkedIn" width="30pt" height="30pt"></a></td>
                </tr>
            </table>
        </div>
    </div>
</footer>
</body>
</html>
