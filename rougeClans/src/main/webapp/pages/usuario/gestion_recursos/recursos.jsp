<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <title>Recursos | Rouge Clans</title>
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
                    <a href="#" class="sidebar-link active">
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


        <main class="content-recursos">


            <div class="custom_card">
                <div>
                    <div class="subtitle_card">Gestión de Recursos</div>
                    <hr style="margin-top: 0">

                    <div class="resources">
                        <div>
                            <div class="d-flex flex-column align-items-center">
                                <p>Al presionar esta opción el tiempo transcurrirá hasta que comience un día nuevo.</p>
                                <form action="#" method="POST">
                                    <button type="submit" class="btn btn-lg btn-custom"> Pasar las horas </button>
                                </form>
                            </div>
                        </div>
                        <div>
                            <div class="d-flex flex-column align-items-center">
                                <p>Solo podrá presionar este botón cuando haya utilizado todas las horas del día.</p>
                                <form action="#" method="POST">
                                    <button type="submit" class="btn btn-lg btn-custom"> Terminar el día </button>
                                </form>
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
<script src="../../../js/script.js"></script>
</body>

</html>
