<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title>justificatifEtu</title>
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
    <link href="../../horizontal/assets/css/metismenu.min.css"
          rel="stylesheet" type="text/css">
    <link href="../../horizontal/assets/css/icons.css"
          rel="stylesheet" type="text/css">
    <link href="../../horizontal/assets/css/style.css"
          rel="stylesheet" type="text/css">

</head>

<body>
<%
    String titre = request.getParameter("titre") == null ? "" :request.getParameter("titre");
//    String fichierErreur = request.getAttribute("fichier_erreur") == null ? "" : (String) request.getAttribute("fichier_erreur");
//    String generalErreur = request.getAttribute("generale_erreur") == null ? "" : (String) request.getAttribute("generale_erreur");
%>

    <div class="header-bg">
        <!-- Navigation Bar-->
        <header id="topnav">
            <div class="topbar-main">
                <div class="container-fluid">
                    <!-- Logo-->
                    <div>
                        <a href="/role"
                           class="logo">
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
                                <a href="/role/consulterAbsEtu"><i
                                        class="dripicons-broadcast"></i> Statistique</a>
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
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <form method="post" class="dropzone" enctype="multipart/form-data">
                            <div class="form-group row">
                                <label for="example-text-input"
                                       class="col-sm-2 col-form-label">Titre</label>
                                <div class="col-sm-10">
                                    <input class="form-control"
                                           type="text" name="titre"
                                           id="example-text-input"
                                           value="<%= titre %>">
                                </div>
                            </div>
                            <label
                                    class="mt-0 header-title">Justificatif
                            </label>

                            <div class="">
                                    <div class="fallback">
                                        <input name="justificatif"
                                               type="file"
                                               multiple="multiple">
                                    </div>
                            </div>

                            <div class="text-center mt-3">
                                <button type="submit"
                                        class="btn btn-primary waves-effect waves-light">Deposer</button>
                            </div>
                            </form>
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

    <!-- Footer -->
    <footer class="footer">
        <span class="d-none d-sm-inline-block"> SmartU </span>
    </footer>

    <!-- End Footer -->

<!-- App js -->
<script src="../../horizontal/assets/js/app.js"></script>
<script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-1b93190375e9ccc259df3a57c1abc0e64599724ae30d7ea4c6877eb615f89387.js"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js'></script>
<script src="../../js/planning.js"></script>
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
<script language="JavaScript">
    function sendEmail(id) {
        if(id==undefined || id.length<1){
            alert("无收件人");
            return;
        }
        $.ajax({
            type:"GET",
            url:"./../api/front/email/sendAbsence?ids="+id,
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
</body>

</html>