<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
  <meta name="description" content="bootstrap admin template">
  <meta name="author" content="">
  <spring:url value="/static/" var="url"/>
  <title>State Master Data</title>

  <jsp:include page="header/plugins/stylePlugins.jsp"/>
  <!-- Page -->
  <link rel="stylesheet" href="${url}assets/examples/css/tables/basic.min599c.css?v4.0.2">
</head>
<body class="animsition ">
  <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

  <jsp:include page="header/header.jsp"/>
  <!-- Page -->
  <div class="page">
    <div class="page-header">
      <h1 class="page-title">State Master Data</h1>
      <div class="page-header-actions">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="../index.html">Home</a></li>
          <li class="breadcrumb-item"><a href="javascript:void(0)">Region</a></li>
          <li class="breadcrumb-item active">State</li>
        </ol>
      </div>
    </div>

    <div class="page-content">
      <div class="row row-lg">
      	<div class="col-lg-5">
      		<div class="panel">
        		<div class="panel-body container-fluid">
        			<h4 class="example-title">Add/Update State Data</h4>
        			<form:form modelAttribute="state" id="state-form">
						<div class="form-group">
							<label for="state_name">State Name <span class="text-danger">*</span></label>
							<form:hidden path="state_id" id="state_id" />
							<form:input path="state_name" id="state_name" cssClass="form-control"/>
						</div>
						
						<div class="form-group">
							<label for="region_name">Region Name <span class="text-danger">*</span></label>
							<form:input path="region_name" id="region_name" cssClass="form-control"/>
						</div>
						
						<div class="form-group">
							<label for="state_code">State Code <span class="text-danger">*</span></label>
							<form:input path="state_code" id="state_code" cssClass="form-control"/>
						</div>
					
						<div class="col-lg-12">
							<br/>
							<button type="submit" class="btn btn-success" id="submitButton">Add State</button>&nbsp;
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
		                <h4 class="example-title">State List</h4>
		                <div class="example table-responsive">
		                  <table class="table" id="state-table">
		                    <thead>
		                      <tr>
		                        <th>State ID</th>
		                        <th>State Name</th>
		                        <th>Region Name</th>
		                        <th>State Code</th>
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
  <jsp:include page="header/footer.jsp"/>
  
  <jsp:include page="header/plugins/jsPlugins.jsp"></jsp:include>
  <!-- Plugins For This Page -->
  <script src="${url}global/vendor/peity/jquery.peity.min599c.js?v4.0.2"></script>
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
  <script type="text/javascript" src="${url}pageJs/region/State.js"></script>

</body>
</html>