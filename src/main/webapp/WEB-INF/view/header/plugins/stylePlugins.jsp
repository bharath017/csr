  <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
  
  <spring:url value="/static/" var="url"/>
  <link rel="apple-touch-icon" href="${url}assets/images/apple-touch-icon.png">
  <link rel="shortcut icon" href="${url}assets/images/favicon.ico">

  <!-- Stylesheets -->
  <link rel="stylesheet" href="${url}global/css/bootstrap.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}global/css/bootstrap-extend.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}assets/css/site.min599c.css?v4.0.2">

  <!-- Skin tools (demo site only) -->
  <link rel="stylesheet" href="${url}global/css/skintools.min599c.css?v4.0.2">
  <script src="${url}assets/js/Plugin/skintools.min599c.js?v4.0.2"></script>

  <!-- Plugins -->
  <link rel="stylesheet" href="${url}global/vendor/animsition/animsition.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}global/vendor/asscrollable/asScrollable.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}global/vendor/switchery/switchery.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}global/vendor/intro-js/introjs.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}global/vendor/slidepanel/slidePanel.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}global/vendor/flag-icon-css/flag-icon.min599c.css?v4.0.2">
  
  <!-- Fonts -->
  <link rel="stylesheet" href="${url}global/fonts/web-icons/web-icons.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}global/fonts/brand-icons/brand-icons.min599c.css?v4.0.2">
  <link rel='stylesheet' href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,300italic">
  
  <!-- sweet alert can be removable -->
  <link rel="stylesheet" href="${url}global/vendor/bootstrap-sweetalert/sweetalert.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}global/vendor/toastr/toastr.min599c.css?v4.0.2">
  <style>
  	.error {
  		color: red;
  	}
  	
  	.willka-loader {
  		background-image: url('${url}assets/images/loader.svg');
		background-repeat: no-repeat;
		width: 50px;
		height: 50px;;
  	}
  	
  	.select2-selection__rendered {
	  line-height: 38px !important;
	}
	
	.select2-selection {
	  height: 38px !important;
	}

  </style>
  
  <!-- icons -->
  <link rel="stylesheet" href="${url}global/fonts/font-awesome/font-awesome.min599c.css?v4.0.2">

  <!-- Scripts -->
  <script src="${url}global/vendor/breakpoints/breakpoints.min599c.js?v4.0.2"></script>
  <script>
    Breakpoints();
  </script>