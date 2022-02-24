<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
  <meta name="description" content="bootstrap admin template">
  <meta name="author" content="">
  <spring:url value="/static/" var="url"/>
  <title>District Master Data</title>
  <jsp:include page="header/plugins/stylePlugins.jsp"/>
  <!-- Page -->
  <link rel="stylesheet" href="${url}assets/examples/css/tables/basic.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}assets/examples/css/tables/datatable.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}global/vendor/datatables.net-bs4/dataTables.bootstrap4.min599c.css?v4.0.2">
</head>
<body class="animsition ">
  <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

  <jsp:include page="header/header.jsp"/>
  <!-- Page -->
  <div class="page">
    <div class="page-header">
      <h1 class="page-title">Cluster List</h1>
      <div class="page-header-actions">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="../index.html">Home</a></li>
          <li class="breadcrumb-item"><a href="javascript:void(0)">Cluster</a></li>
          <li class="breadcrumb-item active">Cluster List</li>
        </ol>
      </div>
    </div>

    <div class="page-content">
      <div class="row row-lg">
      	<div class="col-lg-12">
      		<div class="panel">
        		<div class="panel-body container-fluid">
        			<div class="example-wrap">
		                <div class="row">
		                <div class="col-lg-12">
		                		 <h4 class="example-title">${result}</h4>
		                </div>
		                	<div class="col-lg-4">
		                		 <h4 class="example-title">Cluster List</h4>
		                	</div>
		                	 <div class="col-lg-12">
		                  <form id="search-form">
		                	<div class="row">
			                	<div class="col-lg-2">
			                		 <select class="form-control" id="state_id_search">
	                            			<option value="0" label="State"  selected="selected">State </option>
	                            			<c:forEach items="${stateOption}" var="state">
	                            				<option value="${state.key}">${state.value}</option>
	                            			</c:forEach>
	                            	 </select>
			                	</div>
			                	<div class="col-lg-2">
			                		 <select class="form-control" id="district_id_search">
	                            			<option value="0" label="District"  selected="selected">District</option>
	                            	 </select>
			                	</div>
			                	<div class="col-lg-2">
			                		 <select class="form-control" id="taluk_id_search">
	                            			<option value="0" label="Taluk"  selected="selected">Taluk </option>
	                            	 </select>
			                	</div>
			                	<div class="col-lg-2">
			                		 <select class="form-control" id="hobli_id_search">
	                            			<option value="0" label="Hobli"  selected="selected">Hobli </option>
	                            	 </select>
			                	</div>
			                	<div class="col-lg-2">
			                		 <input type="text" class="form-control" id="search" placeholder="Search...">
			                	</div>
			                	<div class="col-lg-2">
			                		 <button class="btn btn-success" type="button" id="searchBtn">Search</button>
			                		 <button class="btn btn-danger" type="button" id="clearBtn">Clear</button>
			                	</div>
			                </div>
		                </form>
		               </div> 	
		                </div>
		                <div class="example table-responsive" id="districtTableNAme">
		                  <table class="table" id="cluster-table" style="width: 100%;">
		                    <thead>
		                      <tr>
		                        <th>Cluster ID</th>
		                        <th>State</th>
		                        <th>District</th>
		                        <th>Cluster Resource center name</th>
		                        <th>Cluster Resource center Address</th>
		                        <th>Cluster Resource Person name</th>
		                        <th>Action</th>
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
  <sec:csrfInput/>
  
  


  <!-- Footer -->
  <jsp:include page="header/footer.jsp"/>
  
  <jsp:include page="header/plugins/jsPlugins.jsp"></jsp:include>
  <!-- Plugins For This Page -->
  <script src="${url}global/vendor/peity/jquery.peity.min599c.js?v4.0.2"></script>
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
  <script type="text/javascript" src="${url}pageJs/Cluster/ClusterList.js"></script>
</body>
</html>