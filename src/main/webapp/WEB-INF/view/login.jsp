<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<spring:url value="/static/" var="url"/>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
  <meta name="description" content="bootstrap admin template">
  <meta name="author" content="">

  <title>Login V3 | Remark Admin Template</title>
  <jsp:include page="header/plugins/stylePlugins.jsp"></jsp:include>
 
  <!-- Page -->
  <link rel="stylesheet" href="${url}assets/examples/css/pages/login-v3.min599c.css?v4.0.2">
 
</head>
<body class="animsition page-login-v3 layout-full">
  <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->


  <!-- Page -->
  <div class="page vertical-align text-center" data-animsition-in="fade-in" data-animsition-out="fade-out">>
    <div class="page-content vertical-align-middle animation-slide-top animation-duration-1">
      <div class="panel">
        <div class="panel-body">
          <div class="brand">
            <img class="brand-img" src="${url}assets/images/logo-colored.png" alt="...">
            <h2 class="brand-text font-size-18">CSR School Login</h2>
          </div>
          <form method="post" action='<spring:url value="/login"/>'>
            <div class="form-group form-material floating" data-plugin="formMaterial">
              <input type="text" class="form-control" name="custom_username" id="username"/>
              <label class="floating-label">Username</label>
            </div>
            <div class="form-group form-material floating" data-plugin="formMaterial">
              <input type="password" class="form-control" name="custom_password" />
              <label class="floating-label">Password</label>
            </div>
            <div class="form-group clearfix">
              <div class="checkbox-custom checkbox-inline checkbox-primary checkbox-lg float-left">
                <input type="checkbox" id="inputCheckbox" name="remember">
                <label for="inputCheckbox">Remember me</label>
              </div>
              <a class="float-right" href="forgot-password.html">Forgot password?</a>
            </div>
            <button type="submit" class="btn btn-primary btn-block btn-lg mt-40">Sign in</button>
            <sec:csrfInput/>
          </form>
          <p>Still no account? Please go to <a href="register-v3.html">Sign up</a></p>
        </div>
      </div>

      <footer class="page-copyright page-copyright-inverse">
        <p>WEBSITE BY Creation Studio</p>
        <p>© 2018. All RIGHT RESERVED.</p>
        <div class="social">
          <a class="btn btn-icon btn-pure" href="javascript:void(0)">
            <i class="icon bd-twitter" aria-hidden="true"></i>
          </a>
          <a class="btn btn-icon btn-pure" href="javascript:void(0)">
            <i class="icon bd-facebook" aria-hidden="true"></i>
          </a>
          <a class="btn btn-icon btn-pure" href="javascript:void(0)">
            <i class="icon bd-google-plus" aria-hidden="true"></i>
          </a>
        </div>
      </footer>
    </div>
  </div>
  <!-- End Page -->

  <jsp:include page="header/plugins/jsPlugins.jsp"></jsp:include>

  <!-- Plugins For This Page -->
  <script src="${url}global/vendor/jquery-placeholder/jquery.placeholder599c.js?v4.0.2"></script>

  <!-- Page -->
  <script src="${url}assets/js/Site.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/asscrollable.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/slidepanel.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/switchery.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/jquery-placeholder.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/material.min599c.js?v4.0.2"></script>
  <script>
    (function(document, window, $) {
      'use strict';

      var Site = window.Site;
      $(document).ready(function() {
        Site.run();
      });
    })(document, window, jQuery);
  </script>
</body>
</html>