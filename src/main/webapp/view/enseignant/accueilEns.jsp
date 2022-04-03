<%@ page import="com.example.appel.util.DatePlanning" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file="/css/planning.css"%>
    <%--    <jsp:include page="/css/planning.css"/>--%>
</style>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title>accueilEns</title>
    <meta content="Responsive admin theme build on top of Bootstrap 4" name="description" />
    <meta content="Themesdesign" name="author" />
    <link rel="shortcut icon"
          href="../../horizontal/assets/images/favicon.ico">

    <link
            href="../../plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">

    <!-- Dropzone css -->
    <link href="../../plugins/dropzone/dist/dropzone.css"
          rel="stylesheet" type="text/css">

    <link href="../../horizontal/assets/css/bootstrap.min.css"
          rel="stylesheet"
          type="text/css">
    <link href="../../horizontal/assets/css/metismenu.min.css" rel="stylesheet" type="text/css">
    <link href="../../horizontal/assets/css/icons.css" rel="stylesheet" type="text/css">
    <link href="../../horizontal/assets/css/style.css" rel="stylesheet"
          type="text/css">

</head>

<%--<body>--%>
<%
    String date = DatePlanning.getStrFormat(new Date(), "yyyy-MM-dd");
    if (request.getAttribute("date") != null) {
        date = (String) request.getAttribute("date");
    }
%>

<div class="header-bg">
    <!-- Navigation Bar-->
    <header id="topnav">
        <div class="topbar-main">
            <div class="container-fluid">
                <!-- Logo-->
                <div>
                    <a href="/role" class="logo">
                        <img
                                src="../../images/logo-dark.png" class="logo-lg" alt="" height="22">
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

<%--<div class="wrapper">--%>
<%--    <div class="container-fluid">--%>

<%--        <div class="row">--%>

<%--        </div>--%>
<!-- end row -->
<%--    <div class="d-sm-flex justify-content-between align-items-center mb-4">--%>
<%--        <h3 class="text-dark mb-0">Emploi du temps</h3>--%>
<%--    </div>--%>
<div class="container">
    <div class="p-3" style="background: #f5f5f5;">
        <form method="post">
            <div class="row">
                <div class="col-sm-6 col-md-6 col-lg-6 col-xl-6 d-sm-flex justify-content-sm-center justify-content-lg-center justify-content-xl-center">
                    <div class="row">
                        <div class="col-4 col-sm-4 col-md-4 d-flex d-sm-flex justify-content-center align-items-center justify-content-sm-center align-items-sm-center"><label for="date" class="col-form-label d-md-flex justify-content-md-center align-items-md-center">Date</label></div>
                        <div class="col-6 col-sm-8 col-md-8 d-flex d-sm-flex d-md-flex justify-content-center align-items-center justify-content-sm-center align-items-sm-center justify-content-md-center align-items-md-center"><input id="date" name="date" type="date" value="<%= date %>"></div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-6 col-lg-6 col-xl-6 d-flex d-sm-flex justify-content-center align-items-center justify-content-sm-center align-items-sm-center justify-content-lg-center justify-content-xl-center"><button id="rechercher" class="btn btn-primary d-xl-flex" type="submit" name="planning_action" value="search" >Rechercher</button></div>
                <div class="next_previous">
                    <button type="submit" name="planning_action" value="previous">Précédent</button>
                    <button type="submit" name="planning_action" value="next">Suivant</button>
                </div>
            </div>
        </form>
    </div>
</div>


<div class="cd-schedule loading">
    <div class="timeline">
        <ul>
            <li><span>09:00</span></li>
            <li><span>09:30</span></li>
            <li><span>10:00</span></li>
            <li><span>10:30</span></li>
            <li><span>11:00</span></li>
            <li><span>11:30</span></li>
            <li><span>12:00</span></li>
            <li><span>12:30</span></li>
            <li><span>13:00</span></li>
            <li><span>13:30</span></li>
            <li><span>14:00</span></li>
            <li><span>14:30</span></li>
            <li><span>15:00</span></li>
            <li><span>15:30</span></li>
            <li><span>16:00</span></li>
            <li><span>16:30</span></li>
            <li><span>17:00</span></li>
            <li><span>17:30</span></li>
            <li><span>18:00</span></li>
        </ul>
    </div> <!-- .timeline -->

    <div class="events">
        <ul class="wrap">
            <c:forEach var="seances"
                       items="${requestScope.seanceFilter}">
                <li class="events-group">
                    <div class="top-info">
                        <span>${DatePlanning.getDayOfWeek(DatePlanning.getWeektoMillis(seances.key))}</span>
                        <small>${DatePlanning.getStringToLong(seances.key, "dd/MM")}</small>
                    </div>
                    <ul>
                        <c:forEach var="seance" items="${seances.value}">
                            <li class="single-event"
                                data-start="${DatePlanning.getStrFormat(seance.dateDebut, "HH:mm")}" data-end="${DatePlanning.getStrFormat(seance.dateFin, "HH:mm")}" data-content="event-rowing-workout" data-event="event-2">
                                <c:if test="${requestScope.role !=
                                Role.ETUDIANT}" >
                                    <a
                                            href="/role/appel?id=${seance.id}" >
                                        <em
                                                class="event-name">${seance.cours.nomCours}</em>
                                    </a>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="event-modal">
        <header class="header">
            <div class="content">
                <span class="event-date"></span>
                <h3 class="event-name"></h3>
            </div>

            <div class="header-bg"></div>
        </header>

        <div class="body">
            <div class="event-info"></div>
            <div class="body-bg"></div>
            <a href="">Fiche d'appel</a>
        </div>

        <a href="#0" class="close">Close</a>
    </div>

    <div class="cover-layer"></div>
</div> <!-- .cd-schedule -->

<%--    </div>--%>
<%--    <!-- end container-fluid -->--%>
<%--</div>--%>
<!-- end wrapper -->

<!-- Footer -->
<%--<footer class="footer">--%>
<%--    <span class="d-none d-sm-inline-block"> SmartU </span>--%>
<%--</footer>--%>

<!-- End Footer -->

<!-- jQuery  -->
<script src="../../horizontal/assets/js/jquery.min.js"></script>
<script src="../../horizontal/assets/js/bootstrap.bundle.min.js"></script>
<script src="../../horizontal/assets/js/jquery.slimscroll.js"></script>
<script src="../../horizontal/assets/js/waves.min.js"></script>

<script src="../../plugins/apexchart/apexcharts.min.js"></script>
<script
        src="../../plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>

<!-- Dropzone js -->
<script src="../../plugins/dropzone/dist/dropzone.js"></script>

<!-- App js -->
<script src="../../horizontal/assets/js/app.js"></script>
<script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-1b93190375e9ccc259df3a57c1abc0e64599724ae30d7ea4c6877eb615f89387.js"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js'></script>
<script src="../../js/planning.js"></script>

<!--Morris Chart-->
<script src="../../plugins/morris/morris.min.js"></script>
<script src="../../plugins/raphael/raphael.min.js"></script>
<script src="../../horizontal/assets/pages/morris.init.js"></script>

</body>

</html>