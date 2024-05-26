<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina_principal_usuario</title>
<link rel="stylesheet" href="PaginaPrincipalUsuario.css">

  
</head>
<body>
<header>
    <div class="logo"> <img src="PicturesPortada/SPORTARE.png" alt="Logo" width="100px" height="100px"></div>
    <div class="menu"> 
        <ul>
            <li> <a href="MenuUsuarios.jsp">Inicio</a></li>
            <li> <a href="Pag_contactaConmigo.html">Contacto</a></li>
            <li><a href="Pag_informacion.html"> Informacion</a></li>
            <li><a href="CerrarSesion"> Cerrar sesión</a></li>
            
            
        </ul>
    </div>
    <div class="presentacion">
        <p class="Bienvenidos"><h1>Bienvenido a <span class="nombremarca">SPORTARE</span> <%= session.getAttribute("nombre") %> y con ID <%= session.getAttribute("idUsuario") %></h1>
        <p>Dentro de tu perfil podrás acceder a diferentes funciones dentro de nuestro servicio deportivo </p>
		    
</div>
</header>

<main>
<div class="panelUsuario">
       
    <a href="PagPerfilUsuario.jsp" class="perfiles"><img class="perfiles" src="PerfilUsuario/PERFIL USUARIO.jpg" alt="perfiles" width="58%" height="58%"></a>
    <a href="MenuServicios.jsp" ><img class="servicioDeportivo" src="PerfilUsuario/instalacion.jpg" alt="servicioDeportivo" width="58%" height="58%"> </a>
    <a href="PagEntrenamientoOnline.html"><img class="entrenamientos" src="PerfilUsuario/entrenamiento.jpg" alt="entrenamientoOnline" width="58%" height="58%"> </a>
	<a href="ConsultarSaldo.jsp"><img class="monedero" src="iconosCoordinador/monederooo.jpg" width="58%" height="58%"alt=""></a>
	
</div>

</main>

<footer>
    <div>
        <p>Disfruta del deporte en tu entidad</p>
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

 <script>
    var nombre = '<%= session.getAttribute("nombre") %>';
    console.log("Nombre de usuario: " + nombre);
</script>
</body>
</html>