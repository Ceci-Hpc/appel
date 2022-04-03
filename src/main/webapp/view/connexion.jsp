<%--
  Created by IntelliJ IDEA.
  User: acyeol
  Date: 2022/3/21
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<%
    String email = request.getParameter("email") == null ? "" : request.getParameter("email");
    String password = request.getParameter("password") == null ? "" :
            request.getParameter("password");
    String emailErreur = request.getAttribute("email_erreur") == null ? "" : (String) request.getAttribute("email_erreur");
    String passwordErreur = request.getAttribute("password_erreur") == null ? "" : (String) request.getAttribute("password_erreur");
    String generaleErreur = request.getAttribute("generale_erreur") == null ? "" : (String) request.getAttribute("generale_erreur");
%>
<div class="login-container d-flex align-item-center justify-content-center">
    <form method="post" class="login-form text-center" autocomplete="off">
        <h1 class ="mb-5 font-weight-light text-uppercase">Login</h1>

        <img src="../images/ut1.png" alt="image" class="picture" id="tupian"><br>
        <div class="form-group">
            <input type="text" name="email" value="<%= email %>"
                   class="form-control rounded-pill form-control-lg" placeholder="Identifiant">
            <small class="d-flex d-sm-flex justify-content-center justify-content-sm-center text-danger erreur"><%= emailErreur %></small>
        </div>
        <div class="form-group">
            <input type="password" name="password"
                   value="<%= password %>"
                   class="form-control rounded-pill form-control-lg" placeholder="Mot de passe">
            <small
                    class="d-flex d-sm-flex justify-content-center justify-content-sm-center text-danger erreur"><%= passwordErreur %></small>
        </div>
<%--        <div class="forgot-link d-flex align-items-center justify-content-between">--%>
<%--            <div class="form-check">--%>
<%--                <input type="checkbox" class="form-check-input" id="remember">--%>
<%--                <label for="remember">Remember Password</label>--%>
<%--            </div>--%>
<%--            <a href="#">Forgot Password?</a>--%>
<%--        </div>--%>
        <small class="d-flex d-sm-flex justify-content-center justify-content-sm-center text-danger erreur"><%= generaleErreur %></small>
        <button type="submit" class="btn mt-5 btn-custom btn-block text-uppercase  rounded-pill btn-lg">Connexion</button>
<%--        <p class="mt-3 font-weight-normal"> Don't have an account <a href="#"><strong>Register Now</strong> </a></p>--%>

    </form>
</div>
<!-- jQuery  -->
<script src="../horizontal/assets/js/jquery.min.js"></script>
<script src="../horizontal/assets/js/bootstrap.bundle.min.js"></script>
<script src="../horizontal/assets/js/jquery.slimscroll.js"></script>
<script src="../horizontal/assets/js/waves.min.js"></script>

<script src="../plugins/apexchart/apexcharts.min.js"></script>
<script
        src="../plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>

<!-- Dropzone js -->
<script src="../plugins/dropzone/dist/dropzone.js"></script>

<!-- App js -->
<script src="../horizontal/assets/js/app.js"></script>
<script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-1b93190375e9ccc259df3a57c1abc0e64599724ae30d7ea4c6877eb615f89387.js"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js'></script>
<script src="../js/planning.js"></script>
<!-- jQuery  -->
<script src="../horizontal/assets/js/jquery.min.js"></script>
<script src="../horizontal/assets/js/bootstrap.bundle.min.js"></script>
<script src="../horizontal/assets/js/jquery.slimscroll.js"></script>
<script src="../horizontal/assets/js/waves.min.js"></script>

<script src="../plugins/apexchart/apexcharts.min.js"></script>
<script src="../plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>

<!--Morris Chart-->
<script src="../plugins/morris/morris.min.js"></script>
<script src="../plugins/raphael/raphael.min.js"></script>
<script src="../horizontal/assets/pages/morris.init.js"></script>

<!-- App js -->
<script src="../horizontal/assets/js/app.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
