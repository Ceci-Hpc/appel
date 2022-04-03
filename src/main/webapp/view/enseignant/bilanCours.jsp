<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: haoya
  Date: 24/03/2022
  Time: 08:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>


<html>
<head>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title>Bilan</title>


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta content="Responsive admin theme build on top of Bootstrap 4" name="description" />
    <meta content="Themesdesign" name="author" />
    <link rel="shortcut icon"
          href="../../horizontal/assets/images/favicon.ico">
    <link
            href="../../plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <!--Morris Chart CSS -->
    <link rel="stylesheet" href="../../plugins/morris/morris.css">

    <link
            href="../../plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">

    <!--Morris Chart CSS -->
    <link rel="stylesheet" href="../../plugins/morris/morris.css">

    <link href="../../horizontal/assets/css/bootstrap.min.css"
          rel="stylesheet" type="text/css">
    <link href="../../horizontal/assets/css/metismenu.min.css"
          rel="stylesheet" type="text/css">
    <link href="../../horizontal/assets/css/icons.css"
          rel="stylesheet" type="text/css">
    <link href="../../horizontal/assets/css/style.css"
          rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">




</head>

<body>


<div class="header-bg">
    <!-- Navigation Bar-->
    <header id="topnav">
        <div class="topbar-main">
            <div class="container-fluid">
                <!-- Logo-->
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
        <!-- end topbar-main -->
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
        <!-- end #navigation -->

        <!-- end container -->

        <!-- end navbar-custom -->
    </header>
    <!-- End Navigation Bar-->

</div>
<!-- header-bg -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>



<script type="text/javascript">
    window.onload = function () {
        var chart = new CanvasJS.Chart("graphique", {
            title:{
                text: "Taux d'absence pour un cours"
            },
            data: [
                {
                    // Change type to "doughnut", "line", "splineArea", etc.
                    type: "column",
                    dataPoints: [
                        <c:forEach items="${requestScope.tauxAbsenceList}" var="numSeance">
                        { label: "${numSeance.key}", y:${numSeance.value}},

                        </c:forEach>

                    ]
                }
            ]
        });
        chart.render();
    }
</script>

<div class="wrapper">
    <div class="container-fluid">
        <h3 class="text-center">Bilan de cours</h3><br>
        <form method="post">
            <label>
                choisir le cours:
                <select name="cours" aria-controls="datatable"
                        class = "form-control form-control-sm" id="nomcours">
                    <option value="0">--</option>
                    <c:forEach items="${requestScope.coursList}" var="cours">
                    <option value="${cours.id}">${cours.nomCours}</option>

                    </c:forEach>
                </select>
            </label>

            <button value="confirm" type="submit">confirm</button>
        </form>
        <div class="row">
            <div class="col-xl-6">
                <div class="card">
                    <div class="card-body">

                        <h4 class="mt-0 header-title">Le taux d'absence</h4>
                        <p class="sub-title d-inline-block text-truncate w-100">
                            The graphique
                        </p>


                        <div id="graphique" class="morris-charts" style="height: 300px"></div>

                    </div>
                </div>
            </div>
            <!-- end col -->


            <div class="col-xl-6">
                <div class="card">
                    <div class="card-body">
                        <h4 class="mt-0 header-title">Nombre d'absences</h4>
                        <ul class="list-inline widget-chart mt-4 mb-3 text-center">
                            <li class="list-inline-item">
                                <h5>${requestScope.Moyenne}</h5>
                                <p class="text-muted">Moyenne</p>
                            </li>
                            <li class="list-inline-item">
                                <h5>${requestScope.total}</h5>
                                <p class="text-muted">Total</p>
                            </li>
<%--                            <li class="list-inline-item">--%>
<%--                                <h5>4</h5>--%>
<%--                                <p class="text-muted">Maximum</p>--%>
<%--                            </li>--%>
                        </ul>

                        <h4 class="mt-0 header-title">Plus de trois absences</h4>
                        <div class="table-responsive">
                            <table class="table table-hover mb-0">
                                <thead>
                                <tr>
                                    <th scope="col">FI/FA</th>
                                    <th scope="col">Name</th>
<%--                                    <th scope="col">Cours</th>--%>
                                    <th scope="col">Absence</th>
                                </tr>
                                </thead>
                                <tbody>

                                <!-- start 5 -->
                                <c:forEach items="${requestScope.nbabsence}" var="listetu">
                                <tr>
                                    <td>
                                        <!--学生的type-->
                                            ${listetu.key.typeEtudiant}
                                    </td>
                                    <td>
<%--                                        <img src="../horizontal/assets/images/users/Zijian.jpg" alt="" class="thumb-md rounded-circle mr-2">--%>
                                        <!--显示学生nom和prenom-->
                                            ${listetu.key.prenom} , ${listetu.key.nom.toUpperCase()}
                                    </td>
<%--                                    <td>--%>
<%--                                        ${listetu.key.formation.cours.nomCours}<!--对应课程-->--%>
<%--                                    </td>--%>
                                    <td>
                                        ${listetu.value}<!--缺席次数-->
                                    </td>

                                </tr>
                                <!-- end 5 -->

                                </c:forEach>

                                </tbody>
                            </table>
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
