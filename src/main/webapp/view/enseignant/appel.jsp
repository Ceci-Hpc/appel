<%--
  Created by IntelliJ IDEA.
  User: haoya
  Date: 23/03/2022
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.appel.enumType.EtatAppel" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title>ficheAppel</title>


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta content="Responsive admin theme build on top of Bootstrap 4" name="description"/>
    <meta content="Themesdesign" name="author"/>
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
        <div class="topbar-main">
            <div class="container-fluid">
                <!-- Logo-->
                <div>
                    <a href="/role" class="logo" style="width: 10%; height: auto">
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
                                <a class="dropdown-toggle nav-link arrow-none waves-effect nav-user"
                                   data-toggle="dropdown" href="#" role="button" aria-haspopup="false"
                                   aria-expanded="false">
                                    <c:if
                                            test="${requestScope.utilisateur.photo!=null}">
                                        <img
                                                src="../images/${requestScope.utilisateur.photo}"
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
                                            class="dropdown-item text-danger" href="/role/deconnexion"><i
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
                            <a href="/role"><i
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

<div class="card">
    <div class="card-body">
        <h6 class="text-center"><c:out
                value="${requestScope.seance.cours.nomCours}"/>
        </h6>
        <H6 class="text-center"> Formation:
            ${requestScope.seance.cours.formation.nomFormation}</H6>
        <h6 class="text-center">Nombre Total : ${requestScope.nbEtudiant}</h6>

        <!--information de la seance-->
        <div class="row justify-content-between">
            <div class="col-7">
                N° de séance:${requestScope.seance.numSeance}
            </div>
            <div class="col-5">
                Salle: ${requestScope.seance.salle}
            </div>
            <c:if test="${requestScope.isEnrg==false}">
                <div class="text-center">
                    <input class="form-check-input" type="radio" name="defaut" value="0" id="check1"
                           onclick="presente()" checked>
                    <label class="form-check-label" for="check1">
                        Présence &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    </label>
                    <input class="form-check-input" type="radio" name="defaut" value="1" id="check2" onclick="retard()">
                    <label class="form-check-label" for="check2">
                        Retard &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    </label>
                    <input class="form-check-input" type="radio" name="defaut" value="2" id="check3"
                           onclick="absence()">
                    <label class="form-check-label" for="check3">
                        Absence &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    </label>
                </div>
            </c:if>
        </div>

        <div class="table-responsive">
            <form method="post">
                <table class="table table-hover mb-0">
                    <thead>
                    <tr>
                        <th scope="col" style="font-size: 0.8em">FI/FA</th>
                        <th scope="col" style="font-size: 0.8em">Photo</th>
                        <th scope="col" style="font-size: 0.8em">Prénom Nom</th>
                        <th scope="col" style="font-size: 0.8em">Etat</th>
                    </tr>
                    </thead>
                    <tbody>

                    <!-- start 5 -->
                    <c:if
                            test="${requestScope.isEnrg==false}">
                        <c:forEach
                                items="${requestScope.notification}" var="etudiant" varStatus="loop">
                            <tr>
                                <td style="font-size:0.8em">
                                    <input type="hidden" readonly
                                           name="formation"
                                           value="${etudiant.key.typeEtudiant.toString()}" />
                                        ${etudiant.key.typeEtudiant.toString()}
                                </td>
                                <td style="font-size:0.8em;">
                                    <c:if
                                            test="${etudiant.key.photo!=null}">
                                    <img
                                            src="../../images/${etudiant.key.photo}" alt="" class="thumb-md rounded-circle mr-2">
                                    </c:if>
                                    <c:if
                                            test="${etudiant.key.photo==null}">
                                    <img
                                            src="../../images/white.jpg" alt="" class="thumb-md rounded-circle mr-2">
                                    </c:if>
                                <td style="font-size:0.8em;">
                                    <input type="hidden" readonly
                                           name="nom"
                                           value="${etudiant.key.nom}" />
                                    <input type="hidden" readonly
                                           name="prenom"
                                           value="${etudiant.key.prenom}" />
                                    <p style="display: inline">${etudiant.key.prenom}</p>
                                    <p class="text-uppercase" style="display: inline">${etudiant.key.nom}</p>
                                <c:if
                                        test="${requestScope.prevu[loop.count-1]==true}">
                                    <p class="text-uppercase" style="display: inline">**absence prevue</p>
                                </c:if>
                                    <br>
                                    <c:forEach var="it" items="${etudiant.value}">
                                        <p class="text-danger" style="display: inline">${it}&nbsp</p>
                                    </c:forEach>
                                </td>
                                <td style="font-size:0.8em; height: min-content">
                                    <div class="col text-left" >
                                        <div class="form-check" style="font-size:0.8em;">
                                            <input class="form-check-input pre" type="radio" name="${etudiant.key.id}" value="0" id="${etudiant.key.id}1">
                                            <label class="form-check-label" for="${etudiant.key.id}1">
                                                Présence
                                            </label>
                                        </div>
                                        <div class="form-check" style="font-size:0.8em;">
                                            <input
                                                    class="form-check-input retard" type="radio" name="${etudiant.key.id}" value="1" id="${etudiant.key.id}2">
                                            <label class="form-check-label" for="${etudiant.key.id}2">
                                                Retard
                                            </label>
                                        </div>
                                        <div class="form-check" style="font-size:0.8em;">
                                            <input
                                                    class="form-check-input abs" type="radio" name="${etudiant.key.id}" value="2" id="${etudiant.key.id}3">
                                            <label class="form-check-label" for="${etudiant.key.id}3">
                                                Absence
                                            </label>
                                        </div>
                                    </div>
                                </td>

                            </tr>
                        </c:forEach>
                    </c:if>
                    <!-- end 5 -->
                    <c:if
                            test="${requestScope.isEnrg==true}">
                        <c:forEach
                                items="${requestScope.etats}" var="etat" varStatus="loop">
                            <tr>
                                <td style="font-size:0.8em">
                                    <input type="hidden" readonly
                                           name="formation"
                                           value="${etat.etudiant.typeEtudiant.toString()}"/>
                                        ${etat.etudiant.typeEtudiant.toString()}
                                </td>
                                <td style="font-size:0.8em;">
                                    <c:if
                                        test="${etat.etudiant.photo!=null}">
                                    <img
                                            src="../../images/${etat.etudiant.photo}" alt="" class="thumb-md rounded-circle mr-2">
                                </c:if>
                                    <c:if
                                            test="${etat.etudiant.photo==null}">
                                        <img
                                                src="../../images/white.jpg" alt="" class="thumb-md rounded-circle mr-2">
                                    </c:if></td>
                                <td style="font-size:0.8em;">
                                    <input type="hidden" readonly
                                           name="nom"
                                           value="${etat.etudiant.nom}" />
                                    <input type="hidden" readonly
                                           name="prenom"
                                           value="${etat.etudiant.prenom}" />
                                    <p style="display:
                                            inline">${etat.etudiant.prenom}</p>
                                    <p
                                            class="text-uppercase" style="display: inline">${etat.etudiant.nom}</p>
                                </td>
                                <td style="font-size:0.8em; height: min-content">
                                    <div class="col text-left" >
                                        <div class="form-check" style="font-size:0.8em;">
                                            <c:if
                                                    test="${etat.etatAppel!=EtatAppel.PRESENCE}">
                                                <input
                                                        class="form-check-input pre" type="radio" name="${etat.etudiant.id}" value="0" id="${etat.etudiant.id}1">
                                            </c:if>
                                            <c:if
                                                    test="${etat.etatAppel==EtatAppel.PRESENCE}">
                                                <input
                                                        class="form-check-input pre" type="radio" name="${etat.etudiant.id}" value="0" id="${etat.etudiant.id}1" checked>
                                            </c:if>
                                            <label
                                                    class="form-check-label" for="${etat.etudiant.id}1">
                                                Présence
                                            </label>
                                        </div>
                                        <div class="form-check" style="font-size:0.8em;">
                                            <c:if
                                                    test="${etat.etatAppel!=EtatAppel.RETART}">
                                                <input
                                                        class="form-check-input retard" type="radio" name="${etat.etudiant.id}" value="1" id="${etat.etudiant.id}2">
                                            </c:if>
                                            <c:if
                                                    test="${etat.etatAppel==EtatAppel.RETART}">
                                                <input
                                                        class="form-check-input retard" type="radio" name="${etat.etudiant.id}" value="1" id="${etat.etudiant.id}2" checked>
                                            </c:if>
                                            <label
                                                    class="form-check-label" for="${etat.etudiant.id}2">
                                                Retard
                                            </label>
                                        </div>
                                        <div class="form-check" style="font-size:0.8em;">
                                            <c:if
                                                    test="${etat.etatAppel!=EtatAppel.ABSENCE}">
                                                <input
                                                        class="form-check-input abs" type="radio" name="${etat.etudiant.id}" value="2" id="${etat.etudiant.id}3">
                                            </c:if>
                                            <c:if
                                                    test="${etat.etatAppel==EtatAppel.ABSENCE}">
                                                <input
                                                        class="form-check-input abs" type="radio" name="${etat.etudiant.id}" value="2" id="${etat.etudiant.id}3" checked>
                                            </c:if>
                                            <label
                                                    class="form-check-label" for="${etat.etudiant.id}3">
                                                Absence
                                            </label>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    </tbody>
                </table>
                <div>
                        <div class="text-center">
                            <c:if
                                    test="${requestScope.isValide==false}">
                                <input type="submit"
                                       value="enregistrer"
                                       name="enregistrer"
                                       class="btn btn-primary waves-effect waves-light">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                                <input type="submit" value="valider" onclick="sendAbsence()"
                                       name="valider"
                                       class="btn btn-success waves-effect waves-light ">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                                <input type="submit"
                                       value="imprimer"
                                       name="imprimer" id="javaExcel"
                                       class="btn btn-primary waves-effect waves-light">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                                <input type="button"
                                       value="Changer groupe"
                                       name="changer"
                                       class="btn btn-secondary waves-effect" data-bs-toggle="modal" data-bs-target="#exampleModal"/>
                                <!-- Modal -->
                                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Chagenement de groupe</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <p>E-Mail: <input
                                                        type="text "name="email"></p>

                                            </div>
                                            <div class="modal-footer">
                                                <button
                                                        type="submit" class="btn btn-primary" data-bs-dismiss="modal" name="ajouter" value="ajouter">Ajouter</button>
                                                <button
                                                        type="submit" class="btn btn-danger" name="supprimer" value="supprimer">Suprrimer</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <c:if
                                    test="${requestScope.isValide==true}">
                                <input type="submit"
                                       value="imprimer"
                                       name="imprimer" id="javaExcel"
                                       class="btn btn-primary waves-effect waves-light">
                            </c:if>
                        </div>
                </div>
            </form>
        </div>
    </div>
</div>
</div>
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
<script language="JavaScript">

    function sendAbsence() {
        <%--var  list = <%(List<Etudiant>)request.getAttribute("listAllEtudiant")%>--%>
        var  list = document.getElementsByClassName("abs")

        var needSend = new Array();
        if(list.length > 0){
            for (var i = 0; i < list.length; i++) {
                var etudiant = list[i];
                if(etudiant.checked){
                    needSend= needSend+","+etudiant.name;
                }
            }
        }

        $.ajax({
            type:"GET",
            url:"./../api/front/email/sendAbsence?ids="+needSend.substring(1,needSend.length)+"&type=0",
            contentType: "application/json", //必须这样写
            dataType:"JSON",
            success:function(data){
                if(data.code ==" 200"){
                    alert("发送成功")
                }else{
                    alert(data.msg);
                }
            }   ,
            error: function(){
                //请求出错处理
                alert("发送错误");
            }
        });

    }

</script>
<script language="JavaScript">
    function presente() {
        var choix = document.getElementsByClassName("pre");
        for (var i = 0; i < choix.length; i++) {
            choix[i].checked = "checked";
        }
    }
</script>
<script language="JavaScript">
    function retard() {
        var choix = document.getElementsByClassName("retard");
        for (var i = 0; i < choix.length; i++) {
            choix[i].checked = "checked";
        }
    }
</script>
<script language="JavaScript">
    function absence() {
        var choix = document.getElementsByClassName("abs");
        for (var i = 0; i < choix.length; i++) {
            choix[i].checked = "checked";
        }
    }
</script>

</html>
