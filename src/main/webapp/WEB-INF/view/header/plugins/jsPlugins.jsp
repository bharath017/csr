 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 
  <spring:url value="/static/" var="url"/>
  <!-- Core  -->
  <script>
	var ticketImagePath = "${url}documents/schoolImage/"
  </script>
  <script src="${url}global/vendor/babel-external-helpers/babel-external-helpers599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/jquery/jquery.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/popper-js/umd/popper.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/bootstrap/bootstrap.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/animsition/animsition.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/mousewheel/jquery.mousewheel599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/asscrollbar/jquery-asScrollbar.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/asscrollable/jquery-asScrollable.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/ashoverscroll/jquery-asHoverScroll.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/ashoverscroll/jquery-asHoverScroll.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/validation/jquery.validate.min.js"></script>
  <!-- Plugins -->
  <script src="${url}global/vendor/switchery/switchery.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/intro-js/intro.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/screenfull/screenfull599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/slidepanel/jquery-slidePanel.min599c.js?v4.0.2"></script>

  <!-- Scripts -->
  <script src="${url}global/js/Component.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Base.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Config.min599c.js?v4.0.2"></script>
  
  <script src="${url}assets/js/Section/Menubar.min599c.js?v4.0.2"></script>
  <script src="${url}assets/js/Section/GridMenu.min599c.js?v4.0.2"></script>
  <script src="${url}assets/js/Section/Sidebar.min599c.js?v4.0.2"></script>
  <script src="${url}assets/js/Section/PageAside.min599c.js?v4.0.2"></script>
  <script src="${url}assets/js/Plugin/menu.min599c.js?v4.0.2"></script>
  
  <!-- sweet alert and toaster can be removable -->
  <script src="${url}global/vendor/bootbox/bootbox.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/bootstrap-sweetalert/sweetalert.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/toastr/toastr.min599c.js?v4.0.2"></script>
  
  <!-- Config -->
  <script src="${url}global/js/config/colors.min599c.js?v4.0.2"></script>
  <script src="${url}assets/js/config/tour.min599c.js?v4.0.2"></script>
  <script>
    Config.set('assets', 'assets');
  </script>
  <script>
	var ctx = "${pageContext.request.contextPath}"
  </script>
  <script>
   var url = ${url}
  </script>