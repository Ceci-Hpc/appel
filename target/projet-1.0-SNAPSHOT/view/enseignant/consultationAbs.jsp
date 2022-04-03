<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.appel.model.Etat" %>
<%@ page import="com.example.appel.dao.EtatDAO" %><%--
  Created by IntelliJ IDEA.
  User: haoya
  Date: 24/03/2022
  Time: 08:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title>Consultation Abcenses et Retards</title>


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta content="Responsive admin theme build on top of Bootstrap 4" name="description" />
    <meta content="Themesdesign" name="author" />
    <link rel="shortcut icon"
          href="../../horizontal/assets/images/favicon.ico">

    <link href="../../plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">

    <!--Morris Chart CSS -->
    <link rel="stylesheet" href="../../plugins/morris/morris.css">

    <link href="../../horizontal/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../horizontal/assets/css/metismenu.min.css" rel="stylesheet" type="text/css">
    <link href="../../horizontal/assets/css/icons.css" rel="stylesheet" type="text/css">
    <link href="../../horizontal/assets/css/style.css" rel="stylesheet" type="text/css">

</head>

<body>

<div class="header-bg">
    <!-- Navigation Bar-->
    <header id="topnav">

        <!-- MENU Start -->
        <div class="navbar-custom">
            <div class="container-fluid">
                <div>
                    <a href="/role" class="logo">
                        <img src="../../images/logo-dark.png" class="logo-lg" alt="" height="22">
                    </a>
                </div>
                <!-- End Logo-->

                <div class="menu-extras topbar-custom navbar p-0">

                    <ul class="navbar-right ml-auto list-inline float-right mb-0">

                        <!-- full screen -->
                        <li class="dropdown notification-list list-inline-item d-none d-md-inline-block">
                            <a class="nav-link waves-effect" href="#" id="btn-fullscreen">
                                <i class="fas fa-expand noti-icon"></i>
                            </a>
                        </li>

                        <li class="dropdown notification-list list-inline-item">
                            <div class="dropdown notification-list nav-pro-img">
                                <a class="dropdown-toggle nav-link arrow-none waves-effect nav-user" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                                    <c:if
                                            test="${requestScope.utilisateur.photo!=null}">
                                        <img
                                                src="../../images/${requestScope.utilisateur.photo}"
                                                alt="user" class="rounded-circle">
                                    </c:if>
                                    <c:if
                                            test="${requestScope.utilisateur.photo==null}">
                                        <img
                                                src="../../images/profile.jpg"
                                                alt="user" class="rounded-circle">
                                    </c:if>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right dropdown-menu-animated profile-dropdown">
                                    <!-- item-->
                                    <a class="dropdown-item"
                                       href="/role/profileEtu"><i
                                            class="mdi mdi-account-circle"></i> Profile</a>
                                    <div class="dropdown-divider"></div>
                                    <a
                                            class="dropdown-item text-danger" href="/role/deconnexion"><i class="mdi mdi-power text-danger"></i> Logout</a>
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

                <div id="navigation">

                    <!-- Navigation Menu-->
                    <ul class="navigation-menu">

                        <li class="has-submenu">
                            <a href="/role/emploi"><i
                                    class="dripicons-meter"></i> Accueil</a>
                        </li>

                        <li class="has-submenu">
                            <a
                                    href="/role/consulterCours"><i
                                    class="dripicons-briefcase"></i> Bilan</a>
                        </li>
                        <li class="has-submenu">
                            <a href="/role/consultationAbs"><i
                                    class="dripicons-broadcast"></i> statistique</a>
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

<div class="wrapper">
    <div class="wrapper">
        <div class="card">
            <div class="card-body">
                <h5 class="text-center">Consulter les absences et retards</h5>

                <form method="post">
                    <div class="text-center">
                        <div class="input-group">
                            <input type="text" aria-label="Nom d'etudiant" placeholder="ID d'etudiant"
                                   class="form-control" name="nomEtu" value="${param.nomEtu}">
                            <input type="text" aria-label="Nom de cours" placeholder="Nom de cours"
                                   class="form-control" name="nomCours" value="${param.nomCours}">
                            <button class="btn btn-outline-primary" type="submit" id="liveAlertBtn">Chercher</button>
                        </div><br>
                    </div>
                </form>


                <div class="table-responsive">
                    <table class="table table-hover mb-0">
                        <thead>
                        <tr>
                            <th scope="col">Formation</th>
                            <th scope="col">Name</th>
                            <th scope="col">Cours</th>
                            <th scope="col">Absence</th>
                            <th scope="col">Retard</th>
                        </tr>
                        </thead>
                        <c:if test="${not empty requestScope.erreur}">
                            <div class="alert alert-warning d-flex align-items-center" role="alert">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img" aria-label="Warning:">
                                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                </svg>
                                <div>
                                        ${requestScope.erreur}
                                </div>
                            </div>

                        </c:if>


                        <tbody>

                        <c:if test="${not empty requestScope.absence }">

                            <tr>
                                <td>
                                    <input type="text" readonly class="form-control-plaintext"
                                           name="formation"
                                           value="${requestScope.absence.get(0).etudiant.formation.nomFormation}" />
                                </td>

                                <td>
                                    <c:if
                                            test="${requestScope.absence.get(0).etudiant.photo!=null}">
                                    <img
                                            src="../../images/${requestScope.absence.get(0).etudiant.photo}" alt=""
                                         class="thumb-md rounded-circle mr-2">
                                    </c:if>
                                    <c:if
                                            test="${requestScope.absence.get(0).etudiant.photo==null}">
                                        <img src="../../images/white.jpg"
                                             alt=""
                                             class="thumb-md rounded-circle mr-2">
                                    </c:if>
                                        ${requestScope.absence.get(0).etudiant.prenom} ${requestScope.absence.get(0).etudiant.nom}
                                </td>

                                <td>
                                        ${requestScope.absence.get(0).ficheAppel.seance.cours.nomCours}
                                </td>
                                <td>
                                        ${requestScope.absence.size()}
                                </td>

                                <td>
                                        ${requestScope.retard.size()}
                                </td>
                            </tr>

                        </c:if>

                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>


</div>
<!-- end container-fluid -->
</div>
<!-- end wrapper -->

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
