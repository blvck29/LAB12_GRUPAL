<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <title>Inicio | Rouge Clans</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/styles.css">
    <link rel="icon" type="image/jpg" href="favicon.png"/>
</head>


<body>
<!-- ======== Main wrapper for dashboard =========== -->

<div class="wrapper">
    <aside id="sidebar" class="js-sidebar">
        <div class="h-100">
            <div style="margin-top: 20px"></div>
            <div class="sidebar-logo">
                <a href="#"><img src="../media/logo_text.png" width="220px" height="auto" alt="ROUGE CLANS"></a>
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
                    <a href="#" class="sidebar-link">
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
                        <div class="dropdown-menu dropdown-menu-end">
                            <a href="#" class="dropdown-item">Profile</a>
                            <a href="#" class="dropdown-item">Details</a>
                            <a href="#" class="dropdown-item">Logout</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>


        <main class="content py-5 px-5">

            <div class="card">
                <div class="card-header">
                    Datos de la Civilización
                </div>
                <div class="card-body">
                    <blockquote class="blockquote mb-0">
                        <p><display4>Nombre de la Civilización</display4></p>
                        <footer class="blockquote-footer"><cite title="Source Title"></cite>Puesto #10023</footer>
                        <div class="user_data">
                            <p>Líder: @Usuario</p>
                            <p>Tiempo: @Dias @Horas</p>
                            <p>Estado: @Paz/Guerra</p>
                        </div>
                    </blockquote>
                </div>
            </div>


            <div class="card">
                <div class="card-header">
                    Datos de la Civilización
                </div>
                <div class="card-body">
                    <blockquote class="blockquote mb-0">
                        <p><display4>Nombre de la Civilización</display4></p>
                        <footer class="blockquote-footer"><cite title="Source Title"></cite>Puesto #10023</footer>
                    </blockquote>
                </div>
            </div>


            <div class="card">
                <div class="card-header">
                    Datos de la Civilización
                </div>
                <div class="card-body">
                    <blockquote class="blockquote mb-0">
                        <p><display4>Nombre de la Civilización</display4></p>
                        <footer class="blockquote-footer"><cite title="Source Title"></cite>Puesto #10023</footer>
                    </blockquote>
                </div>
            </div>

            <div class="card">
                <div class="card-header">
                    Datos de la Civilización
                </div>
                <div class="card-body">
                    <blockquote class="blockquote mb-0">
                        <p><display4>Nombre de la Civilización</display4></p>
                        <footer class="blockquote-footer"><cite title="Source Title"></cite>Puesto #10023</footer>
                    </blockquote>
                </div>
            </div>


        </main>
    </div>
</div>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/script.js"></script>
</body>

</html>
