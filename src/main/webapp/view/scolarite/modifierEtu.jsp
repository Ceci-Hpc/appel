<%--
  Created by IntelliJ IDEA.
  User: haoya
  Date: 23/03/2022
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
  <title>modifierEtudiant</title>


  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <meta content="Responsive admin theme build on top of Bootstrap 4" name="description" />
  <meta content="Themesdesign" name="author" />
  <link rel="shortcut icon" href="../horizontal/assets/images/favicon.ico">

  <link href="../../plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">

  <!--Morris Chart CSS -->
  <link rel="stylesheet" href="../../plugins/morris/morris.css">

  <link href="../../horizontal/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
  <link href="../../horizontal/assets/css/metismenu.min.css" rel="stylesheet" type="text/css">
  <link href="../../horizontal/assets/css/icons.css" rel="stylesheet" type="text/css">
  <link href="../../horizontal/assets/css/style.css" rel="stylesheet" type="text/css">



</head>

<body>
<%
  String formation = request.getParameter("formation") == null ? "" : request.getParameter("formation");
  String type = request.getParameter("type") == null ? "" : request.getParameter("type");
  String group = request.getParameter("type") == null ? "" : request.getParameter("group");
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
                  <a class="dropdown-item text-danger" href="/role/deconnexion"><i
                          class="mdi mdi-power text-danger"></i> Logout</a>
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
              <a href="/role"><i class="dripicons-meter"></i>
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

<div class="wrapper">
  <div class="wrapper">
    <div class="card">
      <div class="card-body">
        </div>

      <h4 class="mt-0 header-title">Modification de profils</h4>
        <div class="table-responsive">
            <table class="table table-hover mb-0">
              <thead class="thead-dark cf" >
              <tr>
                <th scope="col">Identifiant</th>
                <th scope="col">Type</th>
                <th scope="col">Photo</th>
                <th scope="col">Prenom NOM</th>
                <th scope="col">Formation</th>
                <th scope="col">Groupe</th>
                <th scope="col">Validation</th>
              </tr>
              </thead>
              <tbody>
              <form method="post">
              <!-- start 5 -->
              <c:forEach
                      items="${requestScope.listEtu}" var="etudiant" >
                <tr>
                  <td>
                    <input type="hidden"
                           name="${etudiant.id}id" READONLY
                           value="${etudiant.id}" />
                      ${etudiant.id}
                  </td>
                  <td>
                    <input type="text"
                           name="${etudiant.id}type"
                           value="${etudiant.typeEtudiant.toString()}" />
<%--                    <input type="text" class="form-control"--%>
<%--&lt;%&ndash;                           placeholder="Type d'etudiants" &ndash;%&gt;--%>
<%--                           name="type"--%>
<%--&lt;%&ndash;                           aria-label="Type d'etudiants" aria-describedby="basic-addon1"&ndash;%&gt;--%>
<%--                          value="${etudiant.typeEtudiant.toString()}"/>--%>
                  </td>
                  <td>
                    <c:if test="${etudiant.photo!=null}">
                    <img
                          src="../../images/${etudiant.photo}" alt=""
                          class="thumb-md rounded-circle mr-2">
                    </c:if>
                    <c:if test="${etudiant.photo==null}">
                      <img
                              src="../../images/white.jpg" alt=""
                              class="thumb-md rounded-circle mr-2">
                    </c:if>
                  </td>
                  <td>
<%--                    <input type="hidden"--%>
<%--                           name="nom"--%>
<%--                           value="${etudiant.nom}" />--%>
<%--                    <input type="hidden"--%>
<%--                           name="prenom"--%>
<%--                           value="${etudiant.prenom}" />--%>
                    <p style="display: inline">${etudiant.prenom}</p>
                    <p class="text-uppercase" style="display: inline">${etudiant.nom}</p>
                  </td>
                  <td>
                    <input type="text"
                           name="${etudiant.id}formation"
                           value="${etudiant.formation.nomFormation}" />
<%--                    <input type="text" class="form-control"--%>
<%--&lt;%&ndash;                           placeholder="Formation" &ndash;%&gt;--%>
<%--                           name="formation"--%>
<%--&lt;%&ndash;                           aria-label="Formation" aria-describedby="basic-addon1"&ndash;%&gt;--%>
<%--                           value="${etudiant.formation.nomFormation}"/>--%>
                  </td>
                  <td>
                    <input type="text" name="${etudiant.id}group"
                           value="${etudiant.groupEtudiant.toString()}" />
<%--                    <input type="text" class="form-control"--%>
<%--&lt;%&ndash;                           placeholder="Groupe d'etudiant"&ndash;%&gt;--%>
<%--                           name="group"--%>
<%--&lt;%&ndash;                           aria-label="Groupe d'etudiant" aria-describedby="basic-addon1"&ndash;%&gt;--%>
<%--                           value="${etudiant.groupEtudiant.toString()}"/>--%>
                  </td>
                  <td>
                    <input type="submit" value="valider"
                           name="${etudiant.id}" onsubmit="alert()"
                           class="btn btn-primary">
                  </td>

                </tr>
              </c:forEach>
              </form>
              </tbody>
            </table>

              <!-- end 5 -->


<%--            <div >--%>
<%--              <div class="text-center">--%>
<%--                <input type="submit" value="valider"--%>
<%--                       name="valider" onsubmit="alert()"--%>
<%--                       class="btn btn-primary text-center" >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp--%>
<%--              </div>--%>
<%--            </div>--%>
        </div>
      </div>
    </div>
  </div>


</div>
<!-- end container-fluid -->
</div>
<!-- end wrapper -->

<script>
  function alert(){
    alert("modification reussie");
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
</body>
</html>
