
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.appel.enumType.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.appel.model.Justificatif" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.appel.dao.JustificatifDAO" %>
<!doctype html>
<html lang="en">

<head>

    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title>Acceuil Scolarite</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta content="Responsive admin theme build on top of Bootstrap 4"
          name="description"/>
    <meta content="Themesdesign" name="author"/>
    <link rel="shortcut icon"
          href="../../horizontal/assets/images/favicon.ico">

    <link href="../../plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css"
          rel="stylesheet">

    <link href="../../horizontal/assets/css/bootstrap.min.css"
          rel="stylesheet" type="text/css">
    <link href="../../horizontal/assets/css/metismenu.min.css"
          rel="stylesheet" type="text/css">
    <link href="../../horizontal/assets/css/icons.css"
          rel="stylesheet" type="text/css">
    <link href="../../horizontal/assets/css/style.css"
          rel="stylesheet" type="text/css">

</head>

<body>



<div class="header-bg">
    <!-- Navigation Bar-->
    <header id="topnav">
        <div class="topbar-main">
            <div class="container-fluid">
                <!-- Logo-->
                <div>
                    <a href="index.html" class="logo">
                        <img
                                src="../../images/logo-dark.png"
                                class="logo-lg" alt="" height="22">
                    </a>
                </div>
                <!-- End Logo-->

                <div class="menu-extras topbar-custom navbar p-0">

                    <ul class="navbar-right ml-auto list-inline float-right mb-0">

                        <!-- full screen -->
                        <li class="dropdown notification-list list-inline-item d-none d-md-inline-block">
                            <a class="nav-link waves-effect" href="#"
                               id="btn-fullscreen">
                                <i class="fas fa-expand noti-icon"></i>
                            </a>
                        </li>

                        <li class="dropdown notification-list list-inline-item">
                            <div class="dropdown notification-list nav-pro-img">
                                <a class="dropdown-toggle nav-link arrow-none waves-effect nav-user"
                                   data-toggle="dropdown" href="#"
                                   role="button" aria-haspopup="false"
                                   aria-expanded="false">
                                    <c:if
                                            test="${requestScope.utilisateur.photo!=null}">
                                    <img
                                            src="../../images/requestScope.utilisateur.photo"
                                            alt="user"
                                            class="rounded-circle">
                                    </c:if>
                                    <c:if
                                            test="${requestScope.utilisateur.photo==null}">
                                        <img
                                                src="../../images/profile.jpg"
                                                alt="user"
                                                class="rounded-circle">
                                    </c:if>
                                        </a>
                                <div class="dropdown-menu dropdown-menu-right dropdown-menu-animated profile-dropdown">
                                    <!-- item-->
                                    <a class="dropdown-item"
                                       href="/role/profileEtu"><i
                                            class="mdi mdi-account-circle"></i>
                                        Profile</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item text-danger"
                                       href="/role/deconnexion"><i
                                            class="mdi mdi-power text-danger"></i>
                                        Logout</a>
                                </div>
                            </div>
                        </li>

                        <li class="menu-item dropdown notification-list list-inline-item">
                            <!-- Mobile menu toggle-->
                            <a class="navbar-toggle nav-link">
                                <div class="lines">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </div>
                            </a>
                            <!-- End mobile menu toggle-->
                        </li>

                    </ul>

                </div>
                <!-- end menu-extras -->

                <div class="clearfix"></div>

            </div>
            <!-- end container -->
        </div>
        <!-- end topbar-main -->

        <!-- MENU Start -->
        <div class="navbar-custom">
            <div class="container-fluid">

                <div id="navigation">

                    <!-- Navigation Menu-->
                    <ul class="navigation-menu">

                        <li class="has-submenu">
                            <a href="/role"><i
                                    class="dripicons-meter"></i>
                                Accueil</a>
                        </li>

                        <li class="has-submenu">
                            <a
                                    href="/role/justificatifSco"><i
                                    class="dripicons-briefcase"></i>
                                Justificatif</a>
                        </li>
                        <li class="has-submenu">
                            <a href="/role/createEmploi"><i
                                    class="dripicons-broadcast"></i>
                                Construire l'emploi du temps</a>
                        </li>
                        <li class="has-submenu">
                            <a href="/role/modifierEtudiant"><i
                                    class="dripicons-broadcast"></i>
                                Modifier le profil d'un étudiant</a>
                        </li>
                        <li class="has-submenu">
                            <a href="/role/consulterAbsence"><i
                                    class="dripicons-broadcast"></i>
                                statistique</a>
                        </li>


                    </ul>
                    <!-- End navigation menu -->
                </div>
                <!-- end #navigation -->
            </div>
            <!-- end container -->
        </div>
        <!-- end navbar-custom -->

    </header>
    <!-- End Navigation Bar-->

</div>
<!-- header-bg -->
<script src="../../horizontal/assets/js/jquery.min.js"></script>
<script
        src="../../horizontal/assets/js/bootstrap.bundle.min.js"></script>
<script
        src="../../horizontal/assets/js/jquery.slimscroll.js"></script>
<script src="../../horizontal/assets/js/waves.min.js"></script>

<script src="../../plugins/apexchart/apexcharts.min.js"></script>
<!-- Jquery-Ui -->
<script src="../../plugins/jquery-ui/jquery-ui.min.js"></script>
<script
        src="../../plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>

<script src="../../plugins/moment/moment.js"></script>
<script
        src='../../plugins/fullcalendar/js/fullcalendar.min.js'></script>
<script src="../../horizontal/assets/pages/calendar-init.js"></script>

<!-- App js -->
<script src="../../horizontal/assets/js/app.js"></script>

</body>
</html>
