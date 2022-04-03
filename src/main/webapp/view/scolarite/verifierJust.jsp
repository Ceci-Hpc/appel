<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2022/3/24
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verifier justificatifs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.appel.enumType.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.appel.model.Justificatif" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.appel.dao.JustificatifDAO" %>
<!doctype html>
<html lang="en">
<body>


<%
    List<Justificatif> justificatifs = (List<Justificatif>) request.getAttribute("justificatifs");
    int nbrJust = (Integer) request.getAttribute("nbrJust");
    String nomPrenom = (String) request.getAttribute("nomPrenom");

    System.out.println(nbrJust);
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
                            <a href="/role/justificatifSco"><i
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

    <div class="d-sm-flex justify-content-between align-items-center mb-4">
        <h4 class="mt-0 header-title">Justificatifs</h4>
    </div>
    <div class="table-responsive" id="no-more-tables">
        <table class="table table-striped table-hover">
            <thead class="thead-dark cf">
            <tr class="table-active">
                <th scope="col">Id</th>
                <th scope="col">Titre Justificatif</th>
                <th scope="col">Nom et Prénom</th>
                <th scope="col">Cours</th>
                <th scope="col">Date</th>
                <th scope="col">Document</th>
                <th scope="col">Action</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach
                    items="${requestScope.justificatifs}" var="justificatifs">
                <form method="post">
                    <tr>
                        <td>
                            <input type="hidden" readonly
                                   name="id"
                                   value="${justificatifs.id}"/>
                                ${justificatifs.id}
                        </td>
                        <td>
                            <input type="hidden" readonly
                                   name="titre"
                                   value="${justificatifs.titre}"/>
                                ${justificatifs.titre}
                        </td>
                        <td>
                            <input type="hidden" readonly
                                   name="nom"
                                   value="${justificatifs.etudiant.prenom} ${justificatifs.etudiant.nom}"/><!--scope permer d'acceder
                                   a tous les elements dans un objet de cette maniere!-->
                                ${justificatifs.etudiant.prenom} ${justificatifs.etudiant.nom}
                        </td>
                        <td>
                            <input type="hidden" readonly
                                   name="cours"
                                   value="${justificatifs.seance.cours.nomCours}"/><!--要放课吗？放什么？ 放你大爷 聪哥yyds!-->
                                ${justificatifs.seance.cours.nomCours}
                        </td>
                        <td>
                            <input type="hidden" readonly
                                   name="date"
                                   value="${justificatifs.date}"/>
                                ${justificatifs.date}
                        </td>
                        <td>
                            <a
                                    href="../document/${justificatifs.document}"
                                    download="jus.pdf">${justificatifs.document}</ a>
                        </td>
                        <td>
                            <input class="btn btn-primary" type="submit"
                                   name="Check" onclick="sendEmail('${justificatifs.etudiant.id}')"
                                   value="valider"/>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </form>
</div>
<!-- header-bg -->

</body>

<script language="JavaScript">
    function sendEmail(id) {
        if(id==undefined || id.length<1){
            alert("Recepteur n'existe pas");
            return;
        }
        $.ajax({
            type:"GET",
            url:"./../api/front/email/sendAbsence?ids="+id,
            contentType: "application/json",
            dataType:"JSON",
            success:function(data){
                if(data.code ==" 200"){
                    alert("Envoie succès")
                }else{
                    alert(data.msg);
                }
            }   ,
            error: function(){

                alert("Erreur");
            }
        });

    }
</script>
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
</html>
