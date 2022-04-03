<%--
Created by IntelliJ IDEA.
User: mehdi
Date: 22/03/2022
Time: 14:49
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.appel.enumType.Role" %>
<!doctype html>
<html lang="en">

<head>

    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title>Profile</title>
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

<%
    String prenom = request.getParameter("prenom") == null ? "" : request.getParameter("prenom");
    String nom = request.getParameter("nom") == null ? "" : request.getParameter("nom");
    String email = request.getParameter("email") == null ? "" : request.getParameter("email");
    String phone = request.getParameter("phone") == null ? "" : request.getParameter("phone");
    String formation = request.getParameter("formation") == null ?
            "" :
            request.getParameter("formation");
    String type = request.getParameter("type") == null ? "" :
            request.getParameter("type");
    String nomErreur = request.getAttribute("nom_erreur") == null ? "" : (String) request.getAttribute("nom_erreur");
    String prenomErreur = request.getAttribute("prenom_erreur") == null ? "" : (String) request.getAttribute("prenom_erreur");
    String emailErreur = request.getAttribute("email_erreur") == null ? "" : (String) request.getAttribute("email_erreur");
    String phoneErreur = request.getAttribute("phone_erreur") == null ? "" : (String) request.getAttribute("phone_erreur");
    String formationErreur = request.getAttribute("formation_erreur") == null ? "" : (String) request.getAttribute("formation_erreur");
    String typeErreur = request.getAttribute("type_erreur") == null ? "" : (String) request.getAttribute("type_erreur");
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
                            <a href="/role/consulterAbsEtu"><i
                                    class="dripicons-broadcast"></i>
                                Statistique</a>
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
    <div class="container-fluid">

        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">

                        <h4 class="mt-0 header-title">Information
                            personnelle</h4>
                        <p class="sub-title">there are your profil, if
                            you want to change, please click the
                            button modifier</p>
                        <form method="post" enctype="multipart/form-data">
                            <c:if
                                    test="${requestScope.photo!=null}">
                            <img class="rounded-circle" alt="200x200"
                                 src="../../images/${requestScope.photo}" height="200"
                                 width="200"
                                 data-holder-rendered="true"></c:if>
                            <input name="photo"
                                   type="file"
                                   multiple="multiple">
                            <br>
                            <div class="table-responsive">
                                <table class="table table-striped mb-0">
                                    <thead>
                                    <tr>
                                        <th>Prenom</th>
                                        <th>Nom</th>
                                        <th>Email</th>
                                        <th>Phone</th>
                                        <c:if
                                                test="${requestScope.role==Role.ETUDIANT}">
                                        <th>Formation</th>
                                        <th>Type</th>
                                        </c:if>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>

                                        <td><input type="text"
                                                   name="prenom"
                                                   value="${requestScope.utilisateur.prenom}">
                                        </td>
                                        <td><input type="text"
                                                   name="nom"
                                                   value="${requestScope.utilisateur.nom}">
                                            <small class="d-flex d-sm-flex justify-content-center justify-content-sm-center text-danger erreur"><%= nomErreur %></small>
                                        </td>
                                        <td><input type="text"
                                                   name="email"
                                                   value="${requestScope.utilisateur.email}">
                                        </td>
                                        <td><input type="text"
                                                   name="phone"
                                                   value="${requestScope.utilisateur.phone}">
                                        </td>
                                        <c:if
                                                test="${ requestScope.role == Role.ETUDIANT}">
                                            <td><input type="text"
                                                       name="formation"
                                                       value="<c:out value="${requestScope.nomFormation}"/>"
                                                       readonly
                                                       placeholder=${requestScope.nomFormation}></td>
                                            <td><input type="text"
                                                       name="type"
                                                       value="<c:out value="${requestScope.nomType}"/>"
                                                       readonly placeholder=${requestScope.nomType}></td>
                                        </c:if>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>


                            <div class="button-items">
                                <button type="submit"
                                        class="btn btn-primary waves-effect waves-light">modifier
                                </button>
                            </div>
                        </form>
                    </div>
                </div>


            </div>


        </div>


    </div>
    <!-- end container-fluid -->
</div>
<!-- end wrapper -->

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
