<%@ page import="com.game.rougeclans.model.beans.Jugador" %>
<%@ page import="com.game.rougeclans.model.beans.Civilizacion" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.game.rougeclans.model.Dtos.PersonaEnLista" %>
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
<% ArrayList<PersonaEnLista> listaPersonas = (ArrayList<PersonaEnLista>) request.getAttribute("listaPersonas"); %>

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


        <main class="content-personas">


            <div class="custom_card">
                <div>
                    <div class="d-flex justify-content-between">
                        <div>
                            <div class="subtitle_card">Gestión de Personas</div>
                            <div class="badge" style="font-size: 1.4rem">Cantidad: <%=listaPersonas.size()%></div>
                        </div>
                        <div>
                            <button type="button" style="padding-top: 2px" class="btn btn-lg war-btn"><a href="game?action=create_person" class="text-white">Crear</a></button>
                        </div>
                    </div>
                    <hr style="margin-top: 0">




                    <div class="container-fluid">
                        <div class="row">
                            <nav id="scrollspy" class="col-md-2 d-none d-md-block">
                                <!-- Contenido del scrollspy (opcional) -->
                            </nav>

                            <div data-spy="scroll" data-target="#scrollspy" data-offset="0">
                                <!-- Contenido de la tabla con contenedor de desplazamiento -->
                                <div class="table-container" style="max-height: 310px; overflow-y: auto;">
                                    <table class="table table-dark table-hover">
                                        <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">Nombre</th>
                                            <th scope="col">Profesión</th>
                                            <th scope="col">Genero</th>
                                            <th scope="col">Alimento</th>
                                            <th scope="col">Moral</th>
                                            <th scope="col">Tiempo</th>
                                            <th scope="col">Fuerza</th>
                                            <th scope="col">Produce</th>
                                            <th scope="col">Editar</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <!-- Contenido de la tabla -->

                                        <%if(!(listaPersonas.isEmpty())){%>
                                        <%int i=1; %>
                                        <% for (PersonaEnLista persona : listaPersonas) {%>

                                        <% String genero = "";
                                            switch (persona.getGenero()){
                                                case "M":
                                                    genero = "Masculino";
                                                    break;
                                                case "F":
                                                    genero = "Femenino";
                                                    break;
                                                case "O":
                                                    genero = "Otro";
                                                    break;
                                            }%>


                                        <%
                                            String produce = "Nada";

                                            switch (persona.getProfesion()){
                                                case "Granjero":
                                                    produce = "Alimento = " + persona.getProduce();
                                                    break;
                                                case "Constructor":
                                                case "Soldado":
                                                    produce = "Moral = " + persona.getProduce();
                                                    break;
                                            }
                                        %>

                                        <tr>
                                            <td><%=i%></td>
                                            <td><%=persona.getNombre()%></td>
                                            <td><%=persona.getProfesion()%></td>
                                            <td><%=genero%></td>
                                            <td><%=persona.getAlimentoDia()%></td>
                                            <td><%=persona.getMoral()%></td>
                                            <td><%=persona.getDaysAlive()%> días</td>
                                            <td><%=persona.getFuerza()%></td>
                                            <td><%=produce%></td>
                                            <td>
                                                <div>
                                                    <button type="button" style="padding-top: 2px" class="btn war-btn">
                                                        <a href="game?action=edit_person&id=<%=persona.getIdPersona()%>" class="text-white">
                                                            <ion-icon style="padding-top: 5px"  name="pencil"></ion-icon>
                                                        </a>
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>

                                        <%i++;%>
                                        <%}%>

                                        <%} else {%>
                                            <tr>
                                                <td colspan="10">
                                                    No hay personas creadas...
                                                </td>
                                            </tr>
                                        <% } %>




                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>




                </div>
            </div>
            <div class="custom_card">
                <div>

                    <div class="d-flex justify-content-between">
                        <div class="subcard_title">Ciudadano</div>
                        <div>
                            <button type="button" class="btn war-btn"><a href="game?action=create_person" class="text-white">Crear</a></button>
                        </div>
                    </div>

                    <hr style="margin-top: 0">
                    <div class="per_statistics">
                        <div class="infos">Alimento: 30-50</div>
                        <div class="infos">Moral: 20-50</div>
                        <div class="infos">Fuerza: Nada</div>
                        <div class="infos">Produce: Nada</div>
                    </div>
                </div>
            </div>

            <div class="custom_card">
                <div>
                    <div class="d-flex justify-content-between">
                        <div class="subcard_title">Granjero</div>
                        <div>
                            <button type="button" class="btn war-btn"><a href="game?action=create_person" class="text-white">Crear</a></button>
                        </div>
                    </div>
                    <hr style="margin-top: 0">
                    <div class="per_statistics">
                        <div class="infos">Alimento: 10-30</div>
                        <div class="infos">Moral: 10-40</div>
                        <div class="infos">Fuerza: Nada</div>
                        <div class="infos">Produce: Alimento</div>
                    </div>
                </div>
            </div>

            <div class="custom_card">
                <div>
                    <div class="d-flex justify-content-between">
                        <div class="subcard_title">Constructor</div>
                        <div>
                            <button type="button" class="btn war-btn"><a href="game?action=create_person" class="text-white">Crear</a></button>
                        </div>
                    </div>
                    <hr style="margin-top: 0">
                    <div class="per_statistics">
                        <div class="infos">Alimento: 50-70</div>
                        <div class="infos">Moral: 10-40</div>
                        <div class="infos">Fuerza: 2-20</div>
                        <div class="infos">Produce: Moral</div>
                    </div>
                </div>
            </div>

            <div class="custom_card">
                <div>
                    <div class="d-flex justify-content-between">
                        <div class="subcard_title">Soldado</div>
                        <div>
                            <button type="button" class="btn war-btn"><a href="game?action=create_person" class="text-white">Crear</a></button>
                        </div>
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
