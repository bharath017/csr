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
  <title>Add Cluster</title>
  <jsp:include page="header/plugins/stylePlugins.jsp"/>
  <!-- Page -->
  <link rel="stylesheet" href="${url}assets/examples/css/tables/basic.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}assets/examples/css/tables/datatable.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}global/vendor/datatables.net-bs4/dataTables.bootstrap4.min599c.css?v4.0.2">
    <link rel="stylesheet" href="${url}global/vendor/select2/select2.min.css">
  <link rel="stylesheet" href="${url}global/vendor/bootstrap-select/bootstrap-select.min599c.css?v4.0.2">
  <jsp:include page="header/plugins/stylePlugins.jsp"/>
</head>
<body class="animsition ">
  <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

  <jsp:include page="header/header.jsp"/>
  <!-- Page -->
  <div class="page">
    <div class="page-header">
       <!-- <h1 class="page-title">Add Cluster</h1> --> 
      <div class="page-header-actions">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="../index.html">Home</a></li>
          <li class="breadcrumb-item"><a href="javascript:void(0)">Cluster</a></li>
          <li class="breadcrumb-item active">Add Cluster</li>
        </ol>
      </div>
    </div>

 <div class="page-content">
      <div class="panel">
        <div class="panel-body container-fluid">
        
          <form:form modelAttribute="cluster" action="${ctx}/Cluster/addCluster" method="post">
          	<div class="row row-lg">
          		<div class="col-lg-12">
          			<h5 class="page-title">Add Cluster</h5>
          			<hr>
          		</div>
        			
					<div class="form-group col-lg-4">
          			<label for="state">State <span class="text-danger">*</span></label>
          			<select class="select2 form-control" id="state_id" name="state_id">
          				<option value="" disabled="disabled" selected="selected" label="--Select State--">Select State</option>
          			  	<c:forEach items="${stateOption}" var="state">
          			  		<option value="${state.key}">${state.value}</option>
          			  	</c:forEach>
          			</select>
          		</div>
          		<div class="form-group col-lg-4">
          			<label for="state">District <span class="text-danger">*</span></label>
          			<select class="select2 form-control" name="district_id" id="district_id">
          				<option value="" disabled="disabled" selected="selected" label="--Select District--">Select District</option>
          			</select>
          		</div>
          		<div class="form-group col-lg-4">
          			<label for="state">Taluk <span class="text-danger">*</span></label>
          			<select class="select2 form-control" name="taluk_id" id="taluk_id">
          				<option value="" disabled="disabled" selected="selected" label="--Select Taluk--">Select Taluk</option>
          			</select>
          		</div>
          		<div class="form-group col-lg-4">
          			<label for="state">Hobli <span class="text-danger">*</span></label>
          			<select class="select2 form-control" name="hobli_id" id="hobli_id">
          				<option value="" disabled="disabled" selected="selected" label="--Select Hobli--">Select Hobli</option>
          			</select>
          		</div>
	      			<div class="form-group  col-lg-4">
						<label for="district_name">Cluster Resource Center Name <span class="text-danger">*</span></label>
						<form:input path="cluster_resource_center_name" id="cluster_resource_center_name" cssClass="form-control"/>
					</div>
					<div class="form-group  col-lg-4">
						<label for="district_name">Cluster Resource Center Address <span class="text-danger">*</span></label>
						<form:textarea path="cluster_resource_center_address" id="cluster_resource_center_address" cssClass="form-control"></form:textarea>
					</div>
				<div class="form-group  col-lg-4">
						<label for="district_name">Cluster Resource Person Name <span class="text-danger">*</span></label>
						<form:input path="cluster_resource_person_name" id="cluster_resource_person_name" cssClass="form-control"/>
					</div>
					<div class="form-group  col-lg-4">
						<label for="district_name">Cluster Resource Person Phone <span class="text-danger">*</span></label>
						<form:input path="cluster_resource_person_phone" id="cluster_resource_person_phone" cssClass="form-control"/>
					</div>
					
						<div class="col-lg-12 text-center">
							<br/>
							<button type="submit" class="btn btn-success" id="submitButton">Add Cluster</button>&nbsp;
							<button type="reset" class="btn btn-danger" id="resetButton">Clear</button>
						</div>
		     	 </div>
		      </form:form>
		   	</div>
		   </div>
		</div>
	</div>
		      
  <!-- End Page -->


  <!-- Footer -->
  <jsp:include page="header/footer.jsp"/>
  
  <jsp:include page="header/plugins/jsPlugins.jsp"></jsp:include>
  <!-- Plugins For This Page -->
  <script src="${url}global/vendor/peity/jquery.peity.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/select2/select2.min.js"></script>
  <!-- Page -->
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
  <script src="${url}global/js/Plugin/bootstrap-select.min599c.js?v4.0.2"></script>
  <script type="text/javascript" src="${url}pageJs/Cluster/AddCluster.js"></script>
    <script type="text/javascript">
  $('.select2').select2(
		  { width: '100%' });
  </script>
</body>
</html>