<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PagReservaInstalacionesCoordinador</title>
<link rel="stylesheet" href="ReservaInstalacionesUsuario.css">

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
        <p class="Bienvenidos"><h1><span class="nombremarca">SPORTARE</span></h1>
        <p>¿Quieres realizar tu reserva? Está es tu página</p>
     </div>
</div>
    </header>

    <main>
        <div class="instalaciones">
         <form name="Halterofilia" action="ClaseReservaConAforoUsuario" method="post">
            <select id="nombreServicio" name="nombreServicio" required>
                <option value=""><strong>Selecciona el nombre del servicio:</strong></option>
                <option value="Wod">Halterofilia</option>


            </select><br>

            <label for="horaInicio"><strong>Hora de comienzo</strong></label>
            <select id="horaInicio" name="horaInicio" required>
                <option value="">Hora</option>               
                <option value="9">9</option>               
                <option value="16">16</option>
                <option value="18">18</option>
               

            </select> 
            
            <select id="minInicio" name="minInicio" required>
                <option value="">Minuto</option>
                <option value="00">00</option>
            </select>
           <br>
            
            <label for="horaFin"><strong>Hora de finalizacion</strong></label>
            <select id="horaFin" name="horaFin" required>
                <option value="">Hora</option>
                
                
                <option value="10">10</option>
                <option value="17">17</option>
                <option value="19">19</option>
               
            </select> 
            
            <select id="minFinal" name="minFinal" required>
                <option value="">Minuto</option>
                <option value="00">00</option>
            </select>
       <br>
                <label for="dia"><strong>Dia</strong></label>
                <select id="dia" name="dia" required>
                    <option value="">Dia</option>
                   
                    <option value="2">Martes 2</option>                    
                    <option value="4">Jueves 4</option>                  
                    <option value="9">Martes 9</option>                  
                    <option value="11">Jueves 11</option>                  
                    <option value="15">Martes 15</option>                 
                    <option value="17">Jueves 17</option>                   
                    <option value="22">Martes 22</option>                  
                    <option value="24">Jueves 24</option>                   
                   <option value="29">Martes 29</option>                  
                    <option value="31">Jueves 31</option>                                       
                </select>

                <label for="mes"><strong>Mes</strong></label>
                <select id="mes" name="mes" required>
                    <option value="">mes</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>

                </select>

                <br>

            <label for="idUsuario"><strong>ID Usuario</strong></label>
            <input type="text" id="idUsuario" name="idUsuario" placeholder="ID Usuario" required><br>
            <input type="submit" value="Reservar">
            
        </form>
    </div>
    </main>

    <footer>
        <div class="fin">
            <p> Todas nuestras instalaciones se encuentran ubicadas en el polideportivo municipal </p> 
             <p>Cualquier consulta, puede encontrarnos en <a href="Pag_contactaConmigo.html"></a></p>
         <p>Más información en: </p>
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