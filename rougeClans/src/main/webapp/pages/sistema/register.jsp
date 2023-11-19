<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content=text/html; charset=ISO-8859-1″>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="css/login_styles.css">
    <link rel="icon" type="image/jpg" href="favicon.png"/>

    <title>Register | Rouge Clans</title>
</head>

<body>

<section class="login_form">

    <div class="login text-light">


        <div class="logo">
            <img src="media/loco_image.png" alt="logo">
        </div>
        <div>
            <h5 class="display-7" style="margin-top: -10px; margin-bottom: 5%">CONQUISTA EL MUNDO</h5>
        </div>


        <form action="login?action=register" method="POST">
            <div class="auth">

                <label for="nombre" class="sr-only">Ingrese sus Datos:</label>
                <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Nombre" required="">

                <div style="margin-top: 5px"></div>

                <label for="edad" class="sr-only"></label>
                <input type="text" id="edad" name="edad" class="form-control" placeholder="Edad" required="">

                <div style="margin-top: 5px"></div>

                <label for="correo" class="sr-only"></label>
                <input type="email" id="correo" name="correo" class="form-control" placeholder="Correo" required="">

                <div style="margin-top: 5px"></div>

                <label for="nombre_civ" class="sr-only"></label>
                <input type="text" id="nombre_civ" name="nombre_civ" class="form-control" placeholder="Nombre de la Civilización" required="">

                <div style="margin-top: 5px"></div>

                <label for="usuario" class="sr-only">Ingrese su usuario:</label>
                <input type="text" id="usuario" name="usuario" class="form-control" placeholder="Usuario" required="">

                <div style="margin-top: 5px"></div>

                <label for="contrasena" class="sr-only">Ingrese su contraseña:</label>
                <input type="password" id="contrasena" name="contrasena" class="form-control" placeholder="Contraseña" required="">

                <div style="margin-top: 5px"></div>

                <label for="confirm_contra" class="sr-only"></label>
                <input type="password" id="confirm_contra" name="confirm_contra" class="form-control" placeholder="Confirmar Contraseña" required="">

            </div>
            <button type="submit" class="btn btn-outline-danger">Registrarse</button>
        </form>

        <p class="register">
            ¿Ya tienes cuenta? <a href="login?action=login">Regresar</a>
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