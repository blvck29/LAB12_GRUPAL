<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta http-equiv="Content-Type" content=text/html; charset=ISO-8859-1″>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
  <link rel="stylesheet" href="css/login_styles.css">
  <link rel="icon" type="image/jpg" href="favicon.png"/>

  <title>Login | Rouge Clans</title>
</head>

<body>

<section class="login_form">

  <div class="login text-light">

    <div style="margin-top: 240px"></div>
    <div>
      <h1 class="display-3">Bienvenido</h1>
    </div>


      <form action="#" method="POST">
        <div class="auth">

          <label for="usuario" class="sr-only">Usuario</label>
          <input type="text" id="usuario" class="form-control" placeholder="Usuario" required="" autofocus="">
          <div style="margin-top: 15px"></div>
          <label for="contrasena" class="sr-only">Contraseña</label>
          <input type="password" id="contrasena" class="form-control" placeholder="Contraseña" required="">
         </div>
        <button type="submit" class="btn btn-outline-danger">Acceder</button>
      </form>

    <p class="register">
      ¿No tienes cuenta? <a href="#">Registrarse</a>
    </p>

  </div>

  <video muted autoplay loop>
    <source src="media/background.mp4" type="video/mp4">
  </video>

  <div class="color_layer"></div>
  <div class="overlay_layer"></div>

</section>

</body>


</html>