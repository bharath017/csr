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
  <title>Add School</title>
  <link rel="stylesheet" href="${url}global/vendor/select2/select2.min.css">
  <link rel="stylesheet" href="${url}global/vendor/bootstrap-select/bootstrap-select.min599c.css?v4.0.2">
  <link rel="stylesheet" href="${url}global/vendor/bootstrap-datepicker/bootstrap-datepicker.min599c.css?v4.0.2">
  <link rel="stylesheet" href="https://blueimp.github.io/Gallery/css/blueimp-gallery.min.css"/>
  
  <jsp:include page="header/plugins/stylePlugins.jsp"/>
  <!-- Page -->
</head>
<body class="animsition ">
  <jsp:include page="header/header.jsp"/>
  <!-- Page -->
  <div class="page">
    <div class="page-header">
      <h1 class="page-title">Generate Ticket</h1>
      <div class="page-header-actions">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="../index.html">Home</a></li>
          <li class="breadcrumb-item"><a href="javascript:void(0)">School</a></li>
          <li class="breadcrumb-item active">Add School</li>
        </ol>
      </div>
    </div>

    <div class="page-content">
      <div class="panel">
        <div class="panel-body container-fluid">
          <spring:url value="/Ticket/saveTicket" var="formUrl"/>
          <form:form modelAttribute="ticket" action="${formUrl}" method="post" id="ticket-form">
          	<div class="row row-lg">
          		<div class="col-lg-12">
          			<h6 style="text-decoration: underline;">Select School </h6>
          		</div>
          		<div class="form-group col-lg-4">
          			<label for="state">State <span class="text-danger">*</span></label>
          			<select class="select2 form-control" name="state_id" id="state_id">
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
          		<div class="form-group col-lg-4">
          			<label for="village_id">Village <span class="text-danger">*</span></label>
          			<select name="village_id" class="select2 form-control" id="village_id">
          			</select>
          		</div>
          		<div class="form-group col-lg-4">
          			<form:label path="school_id">School <span class="text-danger">*</span></form:label>
          			<form:select path="school_id" cssClass="select2" >
          				<form:option value="0" label="--Select School--" disabled="disabled"/>
          			</form:select>
          		</div>
          		<div class="col-lg-12">
          			<h6 style="text-decoration: underline;">Ticket Issue</h6>
          		</div>
          		<div class="form-group col-lg-4">
          			<form:label path="category_id">Category <span class="text-danger">*</span></form:label>
          			<form:select path="category_id" cssClass="select2" >
          				<form:option value="0" label="--Select Category--" disabled="disabled"/>
          			</form:select>
          		</div>
          		<div class="form-group col-lg-4">
          			<form:label path="sub_category_id">Sub Category <span class="text-danger">*</span></form:label>
          			<form:select path="sub_category_id" cssClass="select2" >
          				<form:option value="0" label="--Select Category--" disabled="disabled"/>
          			</form:select>
          		</div>
          		
          		
          		
          		<div class="form-group col-lg-8">
          			<form:label path="description">Description <span class="text-danger">*</span></form:label>
          			<form:textarea path="description" cssClass="form-control"/>
          		</div>
          		<div class="col-lg-12">
          			<h6 style="text-decoration: underline;">Upload Images </h6>
          		</div>
				
				<div class="col-lg-12">
					<span class="btn btn-success">
						<form:hidden path="images" id="images"/>
						<input type="file" multiple="multiple" class="files" id="files" accept="image/png, image/gif, image/jpeg" />
					</span>
					<button type="button" class="btn btn-info" id="uploadAllFiles">Upload</button>
				</div>  
				<div class="col-lg-12" id="viewImage">
					
				</div> 
				<div class="col-lg-12 text-center">
					<button type="submit" class="btn btn-primary">Submit Ticket</button>  
					<button type="reset" class="btn btn-danger">Clear</button>     
				</div>		
          	</div>
          </form:form>
        </div>
      </div>
    </div>
  </div>
  <!-- End Page -->

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
  <script src="${url}global/js/Plugin/asscrollable.min599c.js?v4.0.2"></script>
  <script src="${url}assets/examples/js/charts/peity.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/bootstrap-select.min599c.js?v4.0.2"></script>
  <script src="${url}global/js/Plugin/bootstrap-datepicker.min599c.js?v4.0.2"></script>
  <script type="text/javascript" src="${url}pageJs/ticket/AddTicket.js"></script>
  <script type="text/javascript">
  $('.select2').select2({ width: '100%' });
  </script>
</body>
</html>