<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.appel.model.Etudiant" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022/3/23
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title>consulterAbsenceTotal</title>
    <meta content="Responsive admin theme build on top of Bootstrap 4" name="description" />
    <meta content="Themesdesign" name="author" />
    <link rel="shortcut icon" href="../horizontal/assets/images/favicon.ico">

    <link
            href="../../plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">

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
    List<Etudiant> etudiants = request.getAttribute("listAbsences") == null ? null : (List<Etudiant>) request.getAttribute("listAbsences");
//    ArrayList<Integer> nbabsence = request.getAttribute("nbAbsence") == null ? null : (ArrayList<Integer>) request.getAttribute("nbAbsence");
//    ArrayList<Integer> nbabsenceJus = request.getAttribute("nbAbsenceJus") == null ? null : (ArrayList<Integer>)request.getAttribute("nbAbsenceJus");
//    ArrayList<Integer> nbabsenceNonJus = request.getAttribute("nbAbsenceNonJus") == null ? null : (ArrayList<Integer>)request.getAttribute("nbAbsenceNonJus");
//    ArrayList<Integer> nbPresence = request.getAttribute("nbPresence") == null ? null : (ArrayList<Integer>)request.getAttribute("nbPresence");

    String realPath = request.getServletContext().getRealPath("");

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
                    </ul>
                </div>
                <!-- end menu-extras -->

                <div class="clearfix"></div>

            </div>
            <!-- end container -->
        </div>
        <!-- end topbar-main -->
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

        <!-- end container -->

        <!-- end navbar-custom -->
    </header>
    <!-- End Navigation Bar-->

</div>
<!-- header-bg -->

<div class="wrapper">
    <div class="container-fluid">

        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">


                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="mt-0 header-title">chercher votre absences </h4>
                                        <p class="sub-title"> vous pouvez consulter les absences sur les etudiants FA.
                                        </p>
                                        <div class = "row">
                                            <div class="col-sm-12 col-md-6">
                                                <div class = "dataTables_length" id="datatable_length">

                                                            <form method="post">
                                                                <label>
                                                                    choisir le mois:
                                                                    <select name="mois" aria-controls="datatable"
                                                                            class = "form-control form-control-sm" id="numMois">
                                                                        <option value="0">--</option>
                                                                        <option value="1">1</option>
                                                                        <option value="2">2</option>
                                                                        <option value="3">3</option>
                                                                        <option value="4">4</option>
                                                                        <option value="5">5</option>
                                                                        <option value="6">6</option>
                                                                        <option value="7">7</option>
                                                                        <option value="8">8</option>
                                                                        <option value="9">9</option>
                                                                        <option value="10">10</option>
                                                                        <option value="11">11</option>
                                                                        <option value="12">12</option>
                                                                    </select>
                                                                </label>
                                                                <input type="submit" value="valider"
                                                                       name="valider" onsubmit="alert()"
                                                                       class="btn btn-primary text-center" >
                                                            </form>

                                                </div>
                                            </div>
                                        </div>

                                        </div>
                                    <div class="table-responsive">
                                        <table id="datatable" class="table table-bordered dt-responsive nowrap" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                            <thead class="thead-dark cf">
                                            <tr>

                                                <th>Prenom NOM</th>
                                                <th>Email</th>
                                                <th>Phone</th>
                                                <th>Nombre d'Absences</th>
                                                <th>Nombre d'Absences Justifies</th>
                                                <th>Nombre d'Absence Non Justifies</th>
                                                <th>Nombre de Presences</th>


                                            </tr>
                                            </thead>

                                            <tbody>

                                            <c:forEach items="${requestScope.listAbsences}" var="listAbsences" varStatus="status" >

                                                <tr>
                                                    <td>
                                                        <p style="display: inline">${listAbsences.prenom}</p>
                                                        <p class="text-uppercase" style="display: inline">${listAbsences.nom}</p>
                                                    </td>
                                                    <td>
                                                            ${listAbsences.email}
<%--                                                        <input name="email" value="${listAbsences.email}"/>--%>
                                                    </td>
                                                    <td>
                                                            ${listAbsences.phone}
<%--                                                        <input name="phone" value="${listAbsences.phone}"/>--%>
                                                    </td>
                                                    <td>
                                                            ${listAbsences.nbAbsence}
                                                            <%--                                                        <input name="phone" value="${listAbsences.phone}"/>--%>
                                                    </td>
                                                    <td>
                                                            ${listAbsences.nbAbsenceJus}
                                                            <%--                                                        <input name="phone" value="${listAbsences.phone}"/>--%>
                                                    </td>
                                                    <td>
                                                            ${listAbsences.nbAbsenceNonJus}
                                                            <%--                                                        <input name="phone" value="${listAbsences.phone}"/>--%>
                                                    </td>
                                                    <td>
                                                            ${listAbsences.nbPresence}
                                                            <%--                                                        <input name="phone" value="${listAbsences.phone}"/>--%>
                                                    </td>
                                                </tr>

                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <!-- end col -->
            </div>
            <!-- end row -->

        </div>
        <!-- end container-fluid -->
    </div>
</div>
<!-- end wrapper -->

<!-- jQuery  -->
<script src="../../horizontal/assets/js/jquery.min.js"></script>
<script src="../../horizontal/assets/js/bootstrap.bundle.min.js"></script>
<script src="../../horizontal/assets/js/jquery.slimscroll.js"></script>
<script src="../../horizontal/assets/js/waves.min.js"></script>

<script src="../../plugins/apexchart/apexcharts.min.js"></script>
<script src="../../plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>

<!--Morris Chart-->
<script src="../../plugins/morris/morris.min.js"></script>
<script src="../../plugins/raphael/raphael.min.js"></script>
<script src="../../horizontal/assets/pages/morris.init.js"></script>

<!-- App js -->
<script src="../../horizontal/assets/js/app.js"></script>

</body>

</html>

