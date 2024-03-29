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
    <title>Recursos | Rouge Clans</title>
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
                <a href="#"><img src="media/logo_text.png" width="220px" height="auto" alt="ROUGE CLANS"></a>
            </div>


            <ul class="sidebar-nav">
                <li class="sidebar-header">
                    Reloj del Juego
                </li>
                <hr class="hr w-100" style="margin-top: -5px"/>
                <li class="sidebar-item">
                    <a href="game?action=home" class="sidebar-link">
                        <ion-icon name="hourglass"></ion-icon>
                        <%=civilizacion.getDaysElapsed()%> Días | <%=civilizacion.getTimeElapsed()%> Horas
                    </a>
                </li>
            </ul>

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
                    <a href="game?action=personas" class="sidebar-link">
                        <ion-icon name="body"></ion-icon>
                        Personas
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="game?action=recursos" class="sidebar-link active">
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


        <main class="content-recursos">


            <div class="custom_card">
                <div>
                    <div class="subtitle_card">Gestión de Recursos</div>
                    <hr style="margin-top: 0">

                    <div class="resources">
                        <div>
                            <div class="d-flex flex-column align-items-center">
                                <p>Al presionar esta opción el tiempo transcurrirá hasta que comience un día nuevo.</p>
                                <div>
                                    <button type="button" style="padding-top: 2px" class="btn btn-lg war-btn"><a href="game?action=pass_hours" class="text-white">Pasar las horas</a></button>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="d-flex flex-column align-items-center">
                                <p>Este botón solo funcionará cuando hayas utilizado todas las horas del día.</p>

                                <%if (civilizacion.getTimeElapsed() == 24){%>
                                <div>
                                    <button type="button" style="padding-top: 2px" class="btn btn-lg war-btn"><a href="game?action=finish_day" class="text-white">Acabar el día</a></button>
                                </div>
                                <%} else {%>
                                <div>
                                    <button type="button" style="padding-top: 2px" class="btn btn-lg war-btn disabled"><a href="#" class="text-white">Acabar el día</a></button>
                                </div>
                                <% }%>

                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div class="custom_card">
                <div>

                    <div class="d-flex justify-content-between">
                        <div class="subcard_title">Población</div>
                        <div><a href="#" class="link-a">Gestionar</a></div>
                    </div>

                    <hr style="margin-top: 0">
                    <div class="rec-statistics">
                        <div class="infos">Total: 11</div>
                        <div class="infos-sign">→ </div>
                        <div class="infos">Esperado: 16</div>

                        <div class="infos">Granjeros: 3</div>
                        <div class="infos-sign">→ </div>
                        <div class="infos">Necesitas más</div>

                        <div class="infos">Constructores: 2</div>
                        <div class="infos-sign">→ </div>
                        <div class="infos">Necesitas más</div>

                        <div class="infos">Soldados: 4</div>
                        <div class="infos-sign">→ </div>
                        <div class="infos">Son suficentes</div>

                        <div class="infos">Ciudadanos: 2</div>
                        <div class="infos-sign">→ </div>
                        <div class="infos">Necesitas más</div>
                    </div>
                </div>
            </div>

            <div class="custom_card">
                <div>
                    <div class="d-flex justify-content-between">
                        <div class="subcard_title">Recursos</div>
                        <div><a href="#" class="link-a">Gestionar</a></div>
                    </div>

                    <hr style="margin-top: 0">
                    <div class="rec-statistics-2">
                        <div class="infos">Alimento: 178</div>
                        <div class="infos-sign">→ </div>
                        <div class="infos">Esperado: 312</div>

                        <div class="infos">Moral: 69</div>
                        <div class="infos-sign">→ </div>
                        <div class="infos">Población infeliz</div>
                    </div>

                </div>
            </div>

            <div class="custom_card">
                <div>
                    <div class="d-flex justify-content-between">
                        <div class="subcard_title">Poder Militar</div>
                        <div><a href="#" class="link-a">Gestionar</a></div>
                    </div>
                    <hr style="margin-top: 0">
                    <div class="infos">Recuerda: los constructores solo suman su fuerza a la defensa, cuando inicies una guerra ellos permanecerán en la aldea.</div>
                    <div style="margin-top: 10px"></div>
                    <div class="rec-statistics-2">
                        <div class="infos">Tropas en Defensa: 6</div>
                        <div class="infos-sign">→ </div>
                        <div class="infos">Fuerza Total: 186</div>

                        <div class="infos">Tropas en Ataque: 4</div>
                        <div class="infos-sign">→ </div>
                        <div class="infos">Fuerza Total: 123</div>
                    </div>
                </div>
            </div>


        </main>
    </div>
</div>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/script.js"></script>
</body>

</html>

<% } %>
