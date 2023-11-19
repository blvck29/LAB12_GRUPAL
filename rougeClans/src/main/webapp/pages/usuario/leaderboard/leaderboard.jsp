<%@ page import="com.game.rougeclans.model.beans.Jugador" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.game.rougeclans.model.beans.Civilizacion" %>
<%@ page import="com.game.rougeclans.model.Dtos.Top10Jugadores" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="top10" scope="request" type="ArrayList<com.game.rougeclans.model.Dtos.Top10Jugadores>" />

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
    <title>LeaderBoard | Rouge Clans</title>
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
                    <a href="game?action=leaderboard" class="sidebar-link active">
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


        <main class="content-board">


            <div class="custom_card">
                <div>
                    <div class="container-fluid d-flex justify-content-center align-items-center">
                        <ion-icon name="star"></ion-icon><div class="subtitle_card" style="font-size: 3rem; font-weight: bolder">LeaderBoard</div><ion-icon name="star"></ion-icon>
                    </div>
                    <div class="container-fluid d-flex justify-content-center align-items-center">
                        <div class="subtitle_card" style="font-size: 1.2rem">Conoce a los mejores Líderes de todos los tiempos...</div>
                    </div>
                    <hr style="margin-top: 0">




                    <div class="container-fluid">
                        <table class="red-table table table-dark table-hover">
                            <thead>
                            <tr>
                                <th scope="col">Rank</th>
                                <th scope="col">Jugador</th>

                                <th scope="col">
                                    <a href="<%=request.getContextPath()%>/game?action=leaderboard&order_by=dias_jugados">
                                        <button type="submit" class="btn war-btn">Días Jugados</button>
                                    </a>
                                </th>
                                <th scope="col">
                                    <a href="<%=request.getContextPath()%>/game?action=leaderboard&order_by=poblacion_total">
                                        <button type="submit" class="btn war-btn">Población Total</button>
                                    </a>
                                </th>
                                <th scope="col">
                                    <a href="<%=request.getContextPath()%>/game?action=leaderboard&order_by=moral_total">
                                        <button type="submit" class="btn war-btn">Moral Total</button>
                                    </a>
                                </th>
                                <th scope="col">
                                    <a href="<%=request.getContextPath()%>/game?action=leaderboard&order_by=guerras_ganadas">
                                        <button type="submit" class="btn war-btn">Guerras Ganadas</button>
                                    </a>
                                </th>
                                <th scope="col">
                                    <a href="<%=request.getContextPath()%>/game?action=leaderboard&order_by=winrate">
                                        <button type="submit" class="btn war-btn">Win-Rate</button>
                                    </a>
                                </th>
                                <th scope="col">
                                    <a href="<%=request.getContextPath()%>/game?action=leaderboard&order_by=fuerza_total">
                                        <button type="submit" class="btn war-btn">Fuerza Total</button>
                                    </a>
                                </th>
                                <th scope="col">
                                    <a href="<%=request.getContextPath()%>/game?action=leaderboard&order_by=anciano_del_pueblo">
                                        <button type="submit" class="btn war-btn">Anciano del Pueblo</button>
                                    </a>
                                </th>
                                <th scope="col">
                                    <a href="<%=request.getContextPath()%>/game?action=leaderboard&order_by=produccion_de_alimento" method="POST">
                                        <button type="submit" class="btn war-btn">Producción de Alimento</button>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- Contenido de la tabla -->

                            <% int i = 1;%>
                            <% for(Top10Jugadores dto: top10){%>
                            <% if(jugador.getUsuario().equalsIgnoreCase(dto.getCivilizacion().getJugador().getUsuario())){;%>
                            <tr class="bg-dark">
                                <td><%=i%></td>
                                <td><%=dto.getCivilizacion().getJugador().getUsuario()%></td>
                                <td><%=dto.getCivilizacion().getDaysElapsed()%></td>
                                <td><%=dto.getPoblacionTotal()%></td>
                                <td><%=dto.getMoralTotal()%></td>
                                <td><%=dto.getGuerrasGanadas()%></td>
                                <td><%=String.format("%.2f", dto.getWinrate())%>%</td>
                                <td><%=dto.getFuerzaTotal()%></td>
                                <td><%=dto.getMaximoDiasPersona()%></td>
                                <td><%=dto.getProduccionAlimento()%></td>
                            </tr>

                            <%} else {%>
                            <tr>
                                <td><%=i%></td>
                                <td><%=dto.getCivilizacion().getJugador().getUsuario()%></td>
                                <td><%=dto.getCivilizacion().getDaysElapsed()%></td>
                                <td><%=dto.getPoblacionTotal()%></td>
                                <td><%=dto.getMoralTotal()%></td>
                                <td><%=dto.getGuerrasGanadas()%></td>
                                <%if(dto.getWinrate()==0){%>
                                <td>N.A.</td>
                                <%} else {%>
                                <td><%=String.format("%.2f", dto.getWinrate())%>%</td>
                                <%};%>
                                <td><%=dto.getFuerzaTotal()%></td>
                                <td><%=dto.getMaximoDiasPersona()%></td>
                                <td><%=dto.getProduccionAlimento()%></td>
                            </tr>
                            <% };%>
                            <% i++;%>
                            <%}%>

                            </tbody>
                        </table>
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

<%}%>
