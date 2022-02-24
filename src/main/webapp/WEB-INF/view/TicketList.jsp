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
  <title>Ticket History</title>
  <jsp:include page="header/plugins/stylePlugins.jsp"/>
  <!-- Page -->
  <link rel="stylesheet" href="${url}global/vendor/select2/select2.min.css">
  <link rel="stylesheet" href="${url}assets/examples/css/tables/basic.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}assets/examples/css/tables/datatable.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}global/vendor/datatables.net-bs4/dataTables.bootstrap4.min599c.css?v4.0.2">
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
      <h1 class="page-title">Ticket History</h1>
      <div class="page-header-actions">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="../index.html">Home</a></li>
          <li class="breadcrumb-item"><a href="javascript:void(0)">Ticket History</a></li>
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
		                		<form id="search-form">
				                	<div class="row">
					                	<div class="form-group col-lg-3">
					                		
					                		 <select id="school_id" class="select2">
					                		 	<option value="0">Select School</option>
					                		 </select>
					                	</div>
					                	<div class="form-group col-lg-2">
					                		 <select id="category_id" class="form-control select2">
					                		 	<option value="0">All Category</option>
					                		 </select>
					                	</div>
					                	<div class="form-group col-lg-2">
					                		 <select id="sub_category_id" class="form-control select2">
					                		 	<option value="0">All Sub Category</option>
					                		 </select>
					                	</div>
					                	<div class="form-group col-lg-2">
					                		 <select id="ticket_status" class="form-control">
					                		 	<option value="">All</option>
					                		 	<option value="new">New</option>
					                		 	<option value="progress">Progress</option>
					                		 	<option value="rejected">Rejected</option>
					                		 	<option value="completed">Completed</option>
					                		 </select>
					                	</div>
					                	<div class="form-group  col-lg-3">
					                		 <button class="btn btn-success" type="button" id="searchBtn">Search</button>
					                		 <button class="btn btn-danger" type="button" id="clearBtn">Clear</button>
					                	</div>
					                </div>
				                </form>
		                	</div>
		                </div>
		                <div class="example table-responsive" id="schoolTableName">
		                  <table class="table" id="school-table" style="width: 100%;">
		                    <thead>
		                      <tr>
		                     	<th>Ticket ID</th>
		                        <th>School</th>
		                        <th>Category</th>
		                        <th>Sub Category</th>
		                        <th>Generated Date</th>
		                        <th>Description</th>
		                        <th>Status</th>
		                        <th>View</th>
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
  <script src="${url}global/js/Plugin/bootstrap-select.min599c.js?v4.0.2"></script>
  <script src="${url}global/vendor/select2/select2.min.js"></script>
  <script type="text/javascript" src="${url}pageJs/ticket/TicketList.js"></script>
  <script type="text/javascript">
  	$('.select2').select2({ width: '100%' });
  </script>
</body>
</html>