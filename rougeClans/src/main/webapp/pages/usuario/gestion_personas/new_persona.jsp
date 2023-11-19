<%@ page import="com.game.rougeclans.model.beans.Jugador" %>
<%@ page import="com.game.rougeclans.model.beans.Civilizacion" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<% if (session.getAttribute("jugador") == null){ %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="css/login_styles.css">
    <link rel="icon" type="image/jpg" href="favicon.png"/>
    <title>Login | Rouge Clans</title>
</head>
<body>
<section class="login_form">
    <div class="login text-light">
        <div>
            <img src="media/loco_image.png" width="650px" height="auto" alt="logo">
        </div>
        <div>
            <h5 class="display-7" style="margin-top: -10px; margin-bottom: 5%">CONQUISTA EL MUNDO</h5>
        </div>
        <p class="register">
            La sesión ha caducado! Vuelve a iniciar sesión para poder jugar.
            <a href="login" class="red"><p>Regresar</p></a>
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

<% } else { %>


<% Jugador jugador = (Jugador) session.getAttribute("jugador"); %>
<% Civilizacion civilizacion = (Civilizacion) session.getAttribute("civilizacion"); %>

<!DOCTYPE html>
<html lang="es">

<head>
    <title>Personas | Rouge Clans</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="icon" type="image/jpg" href="favicon.png"/>
</head>


<body>
<!-- ======== Main =========== -->

<div class="wrapper">
    <aside id="sidebar" class="js-sidebar">
        <div class="h-100">
            <div style="margin-top: 20px"></div>
            <div class="sidebar-logo">
                <a href="game"><img src="media/logo_text.png" width="220px" height="auto" alt="ROUGE CLANS"></a>
            </div>

            <ul class="sidebar-nav">
                <li class="sidebar-header">
                    Menú de Juego
                </li>
                <hr class="hr w-100" style="margin-top: -5px"/>
                <li class="sidebar-item">
                    <a href="game?action=home" class="sidebar-link">
                        <ion-icon name="apps"></ion-icon>
                        Civilizacion
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="game?action=personas" class="sidebar-link active">
                        <ion-icon name="body"></ion-icon>
                        Personas
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="game?action=recursos" class="sidebar-link">
                        <ion-icon name="archive"></ion-icon>
                        Recursos
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="game?action=guerra" class="sidebar-link">
                        <ion-icon name="eyedrop"></ion-icon>
                        Guerra
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="game?action=leaderboard" class="sidebar-link">
                        <ion-icon name="trophy"></ion-icon>
                        LeaderBoard
                    </a>
                </li>

                <hr class="hr w-100"/>

                <li class="sidebar-item">
                    <a href="logout" class="sidebar-link exit">
                        Cerrar Sesión
                        <ion-icon name="exit"></ion-icon>
                    </a>
                </li>
            </ul>

        </div>
    </aside>

    <div class="main">
        <nav class="navbar navbar-expand px-3 border-bottom">
            <button class="btn" id="sidebar-toggle" type="button">
                <ion-icon style="color: #d2d2d2; margin-top: 4.5px !important; font-size: 1.5rem;" name="apps"></ion-icon>
            </button>
            <div class="navbar-collapse navbar">
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a href="#" data-bs-toggle="dropdown" class="nav-icon pe-md-0">
                            <ion-icon style="font-size: 2.4rem; color: #d2d2d2" name="person-circle"></ion-icon>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end" style="padding: 5px">
                            <p><%=jugador.getUsuario()%></p>
                            <p><%=jugador.getCorreo()%></p>
                            <hr class="hr w-100"/>
                            <a href="#" class="sidebar-link exit" style="font-size: 0.9rem !important; padding: 0 !important;">
                                Cerrar Sesión
                                <ion-icon name="exit"></ion-icon>
                            </a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>


        <main class="content-newper">


            <div class="custom_card">
                <div>
                    <div class="d-flex justify-content-between">
                        <div>
                            <div class="subtitle_card">Crear una Persona</div>
                        </div>
                    </div>
                    <hr style="margin-top: 0">

                    <div class="infos-form">

                        <div>Recuerda que crear una persona gasta horas del día, solo podrás crear una persona si hay tiempo disponible durante el día.</div>


                        <form action="game?action=create_per" method="POST">

                            <div class="create-form">

                                <label for="profesion">Selecciona una profesión:</label>
                                <select id="profesion" name="profesion">
                                    <option value="Ninguna">Ciudadano</option>
                                    <option value="Granjero">Granjero</option>
                                    <option value="Constructor">Constructor</option>
                                    <option value="Soldado">Soldado</option>
                                </select>

                                <label for="genero">Selecciona un Género:</label>
                                <select id="genero" name="genero">
                                    <option value="M">Masculino</option>
                                    <option value="F">Femenino</option>
                                    <option value="O">Otro</option>
                                </select>

                                <label for="nombre">Escribe un nombre:</label>
                                <input type="text" id="nombre" name="nombre" placeholder="Nombre">

                            </div>

                            <div style="margin-bottom: 20px; text-align: center">Los atributos de la persona serán asignados aleatoriamente según los rangos de su profesión.</div>

                            <div>
                                <img id="personaImagen" src="" alt="persona_model">
                            </div>

                            <div class="container">
                                <div class="row justify-content-center">
                                    <div class="col-md-6">
                                        <div class="d-flex justify-content-between">
                                            <div>
                                                <a href="game?action=personas" class="text-white">Regresar</a>
                                            </div>
                                            <div>
                                                <button type="submit" class="btn btn-lg war-btn">Crear</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </form>

                    </div>


                </div>
            </div>


            <div class="custom_card">
                <div class="d-flex justify-content-between"><div class="subtitle_card">Profesiones</div></div>
                <hr style="margin-top: 0">

                <div class="prof-table">


                    <div class="infos">
                        <div class="d-flex justify-content-between">
                            <div class="subcard_title">Ciudadano</div>
                            <div>Toma 2 horas</div>
                        </div>
                        <hr style="margin-top: 0">

                        <div class="per_statistics">
                            <div class="infos">Alimento: 30-50</div>
                            <div class="infos">Moral: 20-50</div>
                            <div class="infos">Fuerza: Nada</div>
                            <div class="infos">Produce: Nada</div>
                        </div>
                    </div>

                    <div class="infos">
                        <div class="d-flex justify-content-between">
                            <div class="subcard_title">Granjero</div>
                            <div>Toma 8 horas</div>
                        </div>
                        <hr style="margin-top: 0">
                        <div class="per_statistics">
                            <div class="infos">Alimento: 10-30</div>
                            <div class="infos">Moral: 10-40</div>
                            <div class="infos">Fuerza: Nada</div>
                            <div class="infos">Produce: Alimento</div>
                        </div>
                    </div>

                    <div class="infos">
                        <div class="d-flex justify-content-between">
                            <div class="subcard_title">Constructor</div>
                            <div>Toma 8 horas</div>
                        </div>
                        <hr style="margin-top: 0">
                        <div class="per_statistics">
                            <div class="infos">Alimento: 50-70</div>
                            <div class="infos">Moral: 10-40</div>
                            <div class="infos">Fuerza: 2-20</div>
                            <div class="infos">Produce: Moral</div>
                        </div>
                    </div>

                    <div class="infos">
                        <div class="d-flex justify-content-between">
                            <div class="subcard_title">Soldado</div>
                            <div>Toma 8 horas</div>
                        </div>
                        <hr style="margin-top: 0">
                        <div class="per_statistics">
                            <div class="infos">Alimento: 70-100</div>
                            <div class="infos">Moral: 30-50</div>
                            <div class="infos">Fuerza: 15-50</div>
                            <div class="infos">Produce: Moral</div>
                        </div>
                    </div>






                </div>
            </div>

        </main>
    </div>
</div>


<script>
    // Obtener referencias a los elementos HTML
    var profesionSelect = document.getElementById('profesion');
    var generoSelect = document.getElementById('genero');
    var imagenPersona = document.getElementById('personaImagen');

    // Asignar función al evento change de los select
    profesionSelect.addEventListener('change', actualizarImagen);
    generoSelect.addEventListener('change', actualizarImagen);

    // Función para actualizar la imagen según la selección
    function actualizarImagen() {
        // Obtener valores seleccionados
        var profesionSeleccionada = profesionSelect.value;
        var generoSeleccionado = generoSelect.value;

        // Lógica para construir la URL de la imagen según la profesión y el género
        var imagenURL = construirURLImagen(profesionSeleccionada, generoSeleccionado);

        // Asignar la nueva URL de la imagen
        imagenPersona.src = imagenURL;
    }

    // Función para construir la URL de la imagen según la profesión y el género
    function construirURLImagen(profesion, genero) {
        // Lógica para construir la URL de la imagen según tus criterios
        // Puedes usar un switch o estructuras if-else según tu necesidad
        // Aquí un ejemplo simple
        var base = "media/assets"; // Reemplaza con la URL base de tus imágenes


        // Combinaciones posibles
        if (profesion === "Ciudadano" && genero === "Masculino") {
            return base + "ciudadano_masculino.jpg";
        } else if (profesion === "Ciudadano" && genero === "Femenino") {
            return base + "ciudadana_femenina.jpg";
        } else if (profesion === "Ciudadano" && genero === "Otro") {
            return base + "ciudadano_otro.jpg";
        } else if (profesion === "Granjero" && genero === "Masculino") {
            return base + "granjero_masculino.jpg";
        } else if (profesion === "Granjero" && genero === "Femenino") {
            return base + "granjera_femenina.jpg";
        } else if (profesion === "Granjero" && genero === "Otro") {
            return base + "granjero_otro.jpg";
        } else if (profesion === "Constructor" && genero === "Masculino") {
            return base + "constructor_masculino.jpg";
        } else if (profesion === "Constructor" && genero === "Femenino") {
            return base + "constructor_femenina.jpg";
        } else if (profesion === "Constructor" && genero === "Otro") {
            return base + "constructor_otro.jpg";
        } else if (profesion === "Soldado" && genero === "Masculino") {
            return base + "soldado_masculino.jpg";
        } else if (profesion === "Soldado" && genero === "Femenino") {
            return base + "soldado_femenina.jpg";
        } else if (profesion === "Soldado" && genero === "Otro") {
            return base + "soldado_otro.jpg";
        }

        // Si no hay coincidencia, puedes devolver una imagen predeterminada
        return base + "default.jpg";
    }
</script>


<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/script.js"></script>
</body>

</html>

<% } %>
