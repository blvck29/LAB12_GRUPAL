<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <title>LeaderBoard | Rouge Clans</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="../../../css/styles.css">
    <link rel="icon" type="image/jpg" href="favicon.png"/>
</head>


<body>
<!-- ======== Main =========== -->

<div class="wrapper">
    <aside id="sidebar" class="js-sidebar">
        <div class="h-100">
            <div style="margin-top: 20px"></div>
            <div class="sidebar-logo">
                <a href="#"><img src="../../../media/logo_text.png" width="220px" height="auto" alt="ROUGE CLANS"></a>
            </div>

            <ul class="sidebar-nav">
                <li class="sidebar-header">
                    Menú de Juego
                </li>
                <hr class="hr w-100" style="margin-top: -5px"/>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link">
                        <ion-icon name="apps"></ion-icon>
                        Civilizacion
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link">
                        <ion-icon name="body"></ion-icon>
                        Personas
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link">
                        <ion-icon name="archive"></ion-icon>
                        Recursos
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link">
                        <ion-icon name="eyedrop"></ion-icon>
                        Guerra
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link active">
                        <ion-icon name="trophy"></ion-icon>
                        LeaderBoard
                    </a>
                </li>

                <hr class="hr w-100"/>

                <li class="sidebar-item">
                    <a href="#" class="sidebar-link exit">
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
                            <p>Nombre de usuario</p>
                            <p>Correo</p>
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
                                    <form action="#" method="POST">
                                        <button type="submit" class="btn war-btn">Días Jugados</button>
                                    </form>
                                </th>
                                <th scope="col">
                                    <form action="#" method="POST">
                                        <button type="submit" class="btn war-btn">Población Total</button>
                                    </form>
                                </th>
                                <th scope="col">
                                    <form action="#" method="POST">
                                        <button type="submit" class="btn war-btn">Moral Total</button>
                                    </form>
                                </th>
                                <th scope="col">
                                    <form action="#" method="POST">
                                        <button type="submit" class="btn war-btn">Guerras Ganadas</button>
                                    </form>
                                </th>
                                <th scope="col">
                                    <form action="#" method="POST">
                                        <button type="submit" class="btn war-btn">Win-Rate</button>
                                    </form>
                                </th>
                                <th scope="col">
                                    <form action="#" method="POST">
                                        <button type="submit" class="btn war-btn">Fuerza Total</button>
                                    </form>
                                </th>
                                <th scope="col">
                                    <form action="#" method="POST">
                                        <button type="submit" class="btn war-btn">Anciano del Pueblo</button>
                                    </form>
                                </th>
                                <th scope="col">
                                    <form action="#" method="POST">
                                        <button type="submit" class="btn war-btn">Producción de Alimento</button>
                                    </form>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- Contenido de la tabla -->

                            <% for(int i=1; i<11; i++){%>

                            <tr>
                                <td><%=i%></td>
                                <td>Aldoradin</td>
                                <td>X</td>
                                <td>X</td>
                                <td>X</td>
                                <td>X</td>
                                <td>X</td>
                                <td>X</td>
                                <td>X</td>
                                <td>X</td>
                            </tr>

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
<script src="../../../js/script.js"></script>
</body>

</html>