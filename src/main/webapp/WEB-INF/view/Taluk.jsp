<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
  <meta name="description" content="bootstrap admin template">
  <meta name="author" content="">
  <spring:url value="/static/" var="url"/>
  <title>Taluk Master Data</title>
  <jsp:include page="header/plugins/stylePlugins.jsp"/>
  <!-- Page -->
    <link rel="stylesheet" href="${url}assets/examples/css/tables/basic.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}assets/examples/css/tables/datatable.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}global/vendor/datatables.net-bs4/dataTables.bootstrap4.min599c.css?v4.0.2">
    <link rel="stylesheet" href="${url}global/vendor/select2/select2.min.css">
  <link rel="stylesheet" href="${url}global/vendor/bootstrap-select/bootstrap-select.min599c.css?v4.0.2">
</head>
<body class="animsition ">
  <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

  <jsp:include page="header/header.jsp"/>
  <!-- Page -->
  <div class="page">
    <div class="page-header">
      <h1 class="page-title">Taluk Master Data</h1>
      <div class="page-header-actions">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="../index.html">Home</a></li>
          <li class="breadcrumb-item"><a href="javascript:void(0)">Region</a></li>
          <li class="breadcrumb-item active">Taluk</li>
        </ol>
      </div>
    </div>

    <div class="page-content">
      <div class="row row-lg">
      	<div class="col-lg-5">
      		<div class="panel">
        		<div class="panel-body container-fluid">
        			<h4 class="example-title">Add/Update Taluk Data</h4>
        		<form:form modelAttribute="Taluk" id="taluk-form">
						<div class="form-group">
							<label for="state_id">State<span class="text-danger">*</span></label>
							<form:hidden path="taluk_id" id="taluk_id" />
							<select name="state_id" id="state_id" class="form-control select2" data-placeholder="--Select State--">
								<option value="0" disabled selected  label="--Select State--">--Select State--</option>
								<c:forEach items="${stateOption}" var="state">
									<option value="${state.key}">${state.value}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="district_id">District<span class="text-danger">*</span></label>
							<form:select path="district_id" id="district_id" cssClass="form-control select2">
								<form:option value="0" disabled="true"  label="--Select District--"/>
							</form:select>
						</div>
						
						<div class="form-group">
							<label for="taluk_name">Taluk Name <span class="text-danger">*</span></label>
							<form:input path="taluk_name" id="taluk_name" cssClass="form-control"/>
						</div>
					
						<div class="col-lg-12">
							<br/>
							<button type="submit" class="btn btn-success" id="submitButton">Add Taluk</button>&nbsp;
							<button type="reset" class="btn btn-danger" id="resetButton">Clear</button>
						</div>
					</form:form>
        		</div>
        	</div>
      	</div>
      	<div class="col-lg-7">
      		<div class="panel">
        		<div class="panel-body container-fluid">
        			<div class="example-wrap">
        			<div class="col-sm-12 text-center">
		                		 <h4 class="example-title">Taluk List</h4>
		                		 <hr>
		                	</div>
		                <div class="row">
		                	
		                	<div class="col-sm-4">
		                	<label for="district_id_search">State</label>
		                		 <select class="form-control" id="state_id_search">
                            			<option value="0" label="--Select State--"  selected="selected">Select State </option>
                            			<c:forEach items="${stateOption}" var="state">
                            				<option value="${state.key}">${state.value}</option>
                            			</c:forEach>
                            	 </select>
		                	</div>
		                	<div class="col-sm-4">
		                		<div class="form-group">
							<label for="district_id_search">District</label>
							<select name="district_id" id="district_id_search" class="form-control">
								<option value="0"   label="--Select District--"/>
							</select>
						</div>
		                	</div>
		                	<div class="col-sm-4">
		                	<label for="search">Enter Keywords</label>
		                		 <input type="text" class="form-control" id="search" placeholder="Search...">
		                	</div>
		                </div>
		                <div class="example table-responsive">
		                  <table class="table" style="width: 100%;" id="taluk-table">
		                    <thead>
		                      <tr>
		                        <th>Taluk ID</th>
		                        <th>State Name</th>
		                        <th>District Name</th>
		                        <th>Taluk Name</th>
		                        <th>Option</th>
		                      </tr>
		                    </thead>
		                    <tbody>
		                    	
		                    </tbody>
		                  </table>
		                </div>
		              </div>
        		</div>
        	</div>
      	</div>
      </div>
    </div>
  </div>
  <!-- End Page -->


  <!-- Footer -->
  <footer class="site-footer">
    <div class="site-footer-legal">© 2018 <a href="https://themeforest.net/item/remark-responsive-bootstrap-admin-template/11989202">Remark</a></div>
    <div class="site-footer-right">
      Crafted with <i class="red-600 wb wb-heart"></i> by <a href="https://themeforest.net/user/creation-studio">Creation Studio</a>
    </div>
  </footer>
  
  <jsp:include page="header/plugins/jsPlugins.jsp"></jsp:include>
  <!-- Plugins For This Page -->
  <script src="${url}global/vendor/peity/jquery.peity.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/select2/select2.min.js"></script>
  <!-- Page -->
  <script src="${url}global/vendor/datatables.net/jquery.dataTables599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/datatables.net-bs4/dataTables.bootstrap4599c.js?v4.0.2"></script>
  <script src="${url}assets/js/Site.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/asscrollable.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/slidepanel.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/switchery.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/peity.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/asselectable.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/selectable.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/table.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/asscrollable.min599c.js?v4.0.2"></script>
  <script src="${url}assets/examples/js/charts/peity.min599c.js?v4.0.2"></script>
  <script type="text/javascript" src="${url}pageJs/region/Taluk.js"></script>
   <script type="text/javascript">
  $('.select2').select2(	  { width: '100%' });
  </script>
</body>
</html>