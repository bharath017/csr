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
  <jsp:include page="header/plugins/stylePlugins.jsp"/>
  <!-- Page -->
</head>
<body class="animsition ">
  <jsp:include page="header/header.jsp"/>
  <!-- Page -->
  <div class="page">
    <div class="page-header">
      <h1 class="page-title">Add School</h1>
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
        	<spring:url value="/School/saveSchool" var="formUrl"/>
          <form:form modelAttribute="school" action="${formUrl}" method="post" id="school-form">
          	<div class="row row-lg">
          		<div class="col-lg-12">
          			<h6 style="text-decoration: underline;">School Details</h6>
          		</div>
          		<div class="form-group col-lg-4">
          			<form:label path="school_name">School Name <span class="text-danger">*</span></form:label>
          			<form:input path="school_name" cssClass="form-control"  id="school_name"/>
          		</div>
          		<div class="form-group col-lg-4">
          			<form:label path="hm_name">Head Master Name <span class="text-danger">*</span></form:label>
          			<form:input path="hm_name" cssClass="form-control" id="hm_name"/>
          		</div>
          		
          		<div class="form-group col-lg-4">
          			<form:label path="hm_phone">Head Master Phone <span class="text-danger">*</span></form:label>
          			<form:input path="hm_phone" cssClass="form-control" id="hm_phone"/>
          		</div>
          		
          		<div class="form-group col-lg-4">
          			<form:label path="school_strength">School Strength <span class="text-danger">*</span></form:label>
          			<form:input path="school_strength" cssClass="form-control" id="school_strength"/>
          		</div>
          		
          		<div class="form-group col-lg-4">
          			<form:label path="no_of_teacher">No of Teacher <span class="text-danger">*</span></form:label>
          			<form:input path="no_of_teacher" cssClass="form-control" id="no_of_teacher"/>
          		</div>
          		
          		<div class="form-group col-lg-4">
          			<form:label path="sdmc_president_name">SDMC President Name </form:label>
          			<form:input path="sdmc_president_name" cssClass="form-control" id="sdmc_president_name"/>
          		</div>
          		
          		<div class="form-group col-lg-4">
          			<form:label path="sdmc_president_phone">SDMC President Phone </form:label>
          			<form:input path="sdmc_president_phone" cssClass="form-control" id="sdmc_president_phone"/>
          		</div>
          		
          		<div class="form-group col-lg-4">
          			<label for="cluster_id">Cluster <span class="text-danger">*</span></label>
          			<form:select path="cluster_id" class="select2 form-control" id="cluster_id">
          				<form:option value="0" disabled="disabled" selected="selected" label="--Select Cluster--"/>
          			  	<form:options items="${clusterListForOption}"/>
          			</form:select>
          		</div>
          		
          		
          		<div class="col-lg-12">
          			<h6 style="text-decoration: underline;">School Address Details</h6>
          		</div>
          		<div class="form-group col-lg-4">
          			<label for="state">State <span class="text-danger">*</span></label>
          			<select class="select2 form-control" id="state_id">
          				<option value="" disabled="disabled" selected="selected" label="--Select State--">Select State</option>
          			  	<c:forEach items="${stateOption}" var="state">
          			  		<option value="${state.key}">${state.value}</option>
          			  	</c:forEach>
          			</select>
          		</div>
          		<div class="form-group col-lg-4">
          			<label for="state">District <span class="text-danger">*</span></label>
          			<select class="select2 form-control" id="district_id">
          				<option value="" disabled="disabled" selected="selected" label="--Select State--">Select State</option>
          			</select>
          		</div>
          		<div class="form-group col-lg-4">
          			<label for="state">Taluk <span class="text-danger">*</span></label>
          			<select class="select2 form-control" id="taluk_id">
          				<option value="" disabled="disabled" selected="selected" label="--Select State--">Select State</option>
          			</select>
          		</div>
          		<div class="form-group col-lg-4">
          			<label for="state">Hobli <span class="text-danger">*</span></label>
          			<select class="select2 form-control" id="hobli_id">
          				<option value="" disabled="disabled" selected="selected" label="--Select State--">Select State</option>
          			</select>
          		</div>
          		<div class="form-group col-lg-4">
          			<form:label path="village_id">Village <span class="text-danger">*</span></form:label>
          			<form:select path="village_id" cssClass="select2 form-control" id="village_id">
          				<option value="" disabled="disabled" selected="selected" label="--Select State--">Select State</option>
          			</form:select>
          		</div>
          		<div class="form-group col-lg-4">
          			<form:label path="pin_code">Pin Code <span class="text-danger">*</span></form:label>
          			<form:input path="pin_code" id="pin_code" cssClass="form-control"/>
          		</div>
          		
          		<div class="form-group col-lg-12">
          			<form:label path="school_address">School Address <span class="text-danger">*</span></form:label>
          			<form:textarea path="school_address" cssClass="form-control"/>
          		</div>
          		<div class="col-lg-12">
          			<h6 style="text-decoration: underline;">Image Upload</h6>
          		</div>
          		
          		<div class="col-lg-12">
          			<table class="table table-bordered" id="image-table">
          				<thead>
          					<tr>
          						<th style="width: 10%;">S/L No</th>
          						<th>File</th>
          						<th>Image</th>
          						<th style="width: 15%;">
          							Option&nbsp;&nbsp;&nbsp;
          							<a style="cursor: pointer;" id="addImageBtn"><span class="text-info"><i class="fa fa-plus"></i></span></a>
          						</th>
          					</tr>
          				</thead>
          				<tbody></tbody>
          			</table>
          		</div>
          		<div class="col-lg-12 text-center">
					<br/>
					<button type="submit" class="btn btn-success" id="submitButton">Add School</button>&nbsp;
					<button type="reset" class="btn btn-danger" id="resetButton">Clear</button>
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
  <script type="text/javascript" src="${url}pageJs/school/AddSchool.js"></script>
  <script type="text/javascript">
  $('.select2').select2({ width: '100%' });
  </script>
</body>
</html>