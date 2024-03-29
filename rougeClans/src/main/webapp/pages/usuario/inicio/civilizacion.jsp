<%@ page import="com.game.rougeclans.model.beans.Jugador" %>
<%@ page import="com.game.rougeclans.model.beans.Civilizacion" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="top10" scope="request" type="ArrayList<com.game.rougeclans.model.Dtos.Top10Jugadores>" />
<jsp:useBean id="ultima_guerra" scope="request" type="com.game.rougeclans.model.Dtos.HistorialGuerras" />


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

<% int poblacionTotal = (int) request.getAttribute("poblacionTotal");%>
<% int moralTotal = (int) request.getAttribute("moralTotal");%>
<% int guerrasGanadas = (int) request.getAttribute("guerrasGanadas");%>
<% double winRate = (double) request.getAttribute("winRate");%>
<% int fuerzaTotal = (int) request.getAttribute("fuerzaTotal");%>
<% int edadAnciano = (int) request.getAttribute("edadAnciano");%>
<% int produccionAlimento = (int) request.getAttribute("produccionAlimento");%>
<% int puesto = (int) request.getAttribute("puesto");%>
<% int puestoOponente = (int) request.getAttribute("puesto_oponente");%>

<!DOCTYPE html>
<html lang="es">

<head>
    <title>Inicio | Rouge Clans</title>
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
                    <a href="game?action=home" class="sidebar-link active">
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
                            <a href="logout" class="sidebar-link exit" style="font-size: 0.9rem !important; padding: 0 !important;">
                                Cerrar Sesión
                                <ion-icon name="exit"></ion-icon>
                            </a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>


        <main class="content">

            <div class="custom_card">
                <div>
                    <div class="title_card" style="font-weight: bold"><%=civilizacion.getNombre()%></div>
                    <div class="badge">Puesto #<%=puesto%></div>
                    <hr style="margin-top: 0">
                </div>
                    <div class="personal_statistics">
                        <div class="infos">Dias Jugados: <%=civilizacion.getDaysElapsed()%></div>
                        <div class="infos">Población Total: <%=poblacionTotal%></div>
                        <div class="infos">Moral Total: <%=moralTotal%></div>
                        <div class="infos">Guerras Ganadas: <%=guerrasGanadas%></div>

                        <%if(winRate==0){%>
                        <div class="infos">Win Rate: N.A.</div>
                        <%} else {%>
                        <div class="infos">Win Rate:  <%=String.format("%.2f", winRate)%>%</div>
                        <%};%>
                        <div class="infos">Fuerza Total: <%=fuerzaTotal%></div>
                        <div class="infos">Anciano del Pueblo: <%=edadAnciano%></div>
                        <div class="infos">Producción de Alimento: <%=produccionAlimento%></div>
                    </div>
            </div>



            <div class="custom_card">
                <div>
                    <div class="subtitle_card">Última Guerra</div>
                    <hr style="margin-top: 0">
                    <%if (ultima_guerra.getResultado()!=null){%>
                    <div class="war-statistics">
                        <div class="infos">Oponente: <%=ultima_guerra.getCivilizacionOponente().getJugador().getUsuario()%></div>
                        <div class="infos">Puesto: #<%=puestoOponente%></div>
                        <div class="infos">Civilización: <%=ultima_guerra.getCivilizacionOponente().getNombre()%></div>
                        <div class="infos">Fecha: Día <%=ultima_guerra.getFecha()%></div>
                        <div class="infos">Resultado: <%=ultima_guerra.getResultado()%></div>
                    </div>
                    <%}else  {%>
                    <div class="war-statistics">
                        <div class="infos">NO HAS ESTADO EN UNA GUERRA AÚN</div>
                    </div>
                    <%}%>


                </div>
            </div>



            <div class="custom_card">
                <div>
                    <div class="subcard_title">Gestión de Personas</div>
                    <hr style="margin-top: 0">
                    <div class="infos">
                        Una civilización grande es más fuerte.
                        <a href="#" class="access_subcard">
                            <ion-icon name="arrow-forward-circle"></ion-icon>
                        </a>
                    </div>
                </div>
            </div>
            <div class="custom_card">
                <div>
                    <div class="subcard_title">Gestión de Recursos</div>
                    <hr style="margin-top: 0">
                    <div class="infos">
                        Asegura y cuida el futuro del pueblo.
                        <a href="#" class="access_subcard">
                            <ion-icon name="arrow-forward-circle"></ion-icon>
                        </a>
                    </div>
                </div>
            </div>
            <div class="custom_card">
                <div>
                    <div class="subcard_title">Iniciar una Guerra</div>
                    <hr style="margin-top: 0">
                    <div class="infos">
                        Conviertete en el más fuerte de todos.
                        <a href="#" class="access_subcard">
                            <ion-icon name="arrow-forward-circle"></ion-icon>
                        </a>
                    </div>
                </div>
            </div>
            <div class="custom_card">
                <div>
                    <div class="subcard_title">LeaderBoard</div>
                    <hr style="margin-top: 0">
                    <div class="infos">
                        @<%=top10.get(0).getCivilizacion().getJugador().getUsuario()%> posee la civilización más poderosa
                        <a href="#" class="access_subcard">
                            <ion-icon name="arrow-forward-circle"></ion-icon>
                        </a>
                    </div>
                </div>
            </div>


            <div class="custom_card">
                <div>
                    <div class="subtitle_card">Personas Creadas</div>
                    <hr style="margin-top: 0">
                </div>
            </div>


        </main>
    </div>
</div>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/script.js"></script>
</body>

</html>

<% } %>
