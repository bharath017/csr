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
  <style type="text/css">
  	input[type="file"] {
	    display: none;
	}
	.custom-file-upload {
	    border: 1px solid #ccc;
	    display: inline-block;
	    padding: 6px 12px;
	    cursor: pointer;
	}
	
	.nopadding {
	   padding: 0 !important;
	   margin: 0 !important;
	}
  </style>
</head>
<body class="animsition ">
  <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

  <jsp:include page="header/header.jsp"/>
  <!-- Page -->
  <div class="page">
    <div class="page-header">
      <h1 class="page-title">View Ticket</h1>
      <div class="page-header-actions">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="../index.html">Home</a></li>
          <li class="breadcrumb-item"><a href="javascript:void(0)">View Ticket</a></li>
        </ol>
      </div>
    </div>

    <div class="page-content">
      <div class="row row-lg">
      	<div class="col-lg-4 nopadding">
      		<div class="panel nopadding">
        		<div class="panel-body container-fluid nopadding">
        			<div class="example-wrap nopadding">
		                <div class="row nopadding">
		                	<div class="col-lg-12 nopadding">
		                		<input type="hidden" name="ticket_id" value="${ticket.ticket_id}" id="${ticket.ticket_id}"/>
		                		<table class="table table-bordered table-hover">
		                			<tr>
		                				<th>Ticket No</th>
		                				<td>${ticket.ticket_id}</td>
		                			</tr>
		                			<tr>
		                				<th>School</th>
		                				<td>${ticket.school_name}</td>
		                			</tr>
		                			<tr>
		                				<th>Village</th>
		                				<td>${ticket.village_name}</td>
		                			</tr>
		                			<tr>
		                				<th>Category</th>
		                				<td>${ticket.category_name}</td>
		                			</tr>
		                			<tr>
		                				<th>Sub Category</th>
		                				<td>${ticket.sub_category_name}</td>
		                			</tr>
		                			<tr>
		                				<th>Description</th>
		                				<td>${ticket.description}</td>
		                			</tr>
		                			<tr>
		                				<th>Estimated Status</th>
		                				<td>
		                					<h6 class="${(ticket.estimated_status=='pending')?'text-danger':'text-success'}">${ticket.estimated_status}</h6>
		                				</td>
		                			</tr>
		                			<tr>
		                				<td colspan="2" id="ticketSatusTR">
		                					<input type="hidden" id="ticketStatus" name="${ticket.ticket_id}" value="${ticket.ticket_status}"/>
		                				</td>
		                			</tr>
		                			
		                		</table>
		                	</div>
		                </div>
		              </div>
        		</div>
        	</div>
      	</div>
      	<div class="col-lg-8">
      		<div class="panel">
      			<div class="panel-body container-fluid">
      				<div class="nav-tabs-vertical" data-plugin="tabs">
	                  <ul class="nav nav-tabs nav-tabs-line mr-25" role="tablist">
	                    <li class="nav-item" role="presentation"><a class="nav-link active" data-toggle="tab" href="#exampleTabsLineLeftOne"
	                        aria-controls="exampleTabsLineLeftOne" role="tab">Images</a></li>
	                    <li class="nav-item" role="presentation"><a class="nav-link" data-toggle="tab" href="#estimateTicketCostTab"
	                        aria-controls="estimateTicketCostTab" role="tab">Estimates</a></li>
	                    <li class="nav-item" role="presentation"><a class="nav-link" data-toggle="tab" href="#exampleTabsLineLeftTwo"
	                        aria-controls="exampleTabsLineLeftTwo" role="tab">Assign Donor</a></li>
	                    <li class="nav-item" role="presentation"><a class="nav-link" data-toggle="tab" href="#exampleTabsLineLeftThree"
	                        aria-controls="exampleTabsLineLeftThree" role="tab">Work Progress</a></li>
	                  </ul>
	                  <div class="tab-content py-15">
	                 	<input type="hidden" id="ticketImages" value="${ticket.images}"/>
	                    <div class="tab-pane active" id="exampleTabsLineLeftOne" role="tabpanel">
	                    	
	                    </div>
	                    <div class="tab-pane" id="estimateTicketCostTab" role="tabpanel">
	                    	<div class="row">
	                    		<div class="col-lg-12">
	                    			<div class="form-group">
	                    				<label for="estimated_amount">Estimated Amount <span class="text-danger">*</span></label>
	                    				<input type="text" name="estimated_amount" id="estimated_amount" class="form-control" value="${ticket.estimated_amount}"/>
	                    				<input type="hidden" name="estimated_status" id="estimated_status" value="${ticket.estimated_status}"/>
	                    			</div>
	                    			<button class="btn btn-info" type="button" id="enterEstimateAmountBtn">Enter Estimate</button>
	                    			<button class="btn btn-primary" type="button" id="aproveEstimateAmountBtn">Approve Estimate Amount</button>
	                    			<label for="file-upload" class="btn btn-success custom-file-upload" style="margin-top: 2%;">
									    <i class="fa fa-cloud-upload"></i> Upload Documents
									</label>
									<input id="file-upload" type="file" />
	                    		</div>
	                    		<div class="col-lg-12">
	                    			<h4 class="text-success" id="viewPercent"></h4>
	                    		</div>
	                    		<div class="col-lg-12 table-responsive">
	                    			<table class="table table-bordered table-hover" id="estimated_document-table">
	                    				<thead>
	                    					<tr>
	                    						<th>S/L No</th>
	                    						<th>Document</th>
	                    						<th>Option</th>
	                    					</tr>
	                    				</thead>
	                    				<tbody></tbody>
	                    			</table>
	                    		</div>
	                    	</div>
	                    </div>
	                    <div class="tab-pane" id="exampleTabsLineLeftTwo" role="tabpanel">
	                     	<div class="row">
	                     		<div class="col-lg-12">
	                     			<form:form modelAttribute="ticketdonor" action="#" id="donor-form">
	                     				<div class="row">
	                     					<div class="form-group col-lg-6">
		                     					<form:label path="donor_id">Donor <span class="text-danger">*</span></form:label>
		                     					<form:hidden path="ticket_id" value="${ticket.ticket_id}"/>
		                     					<form:select path="donor_id" cssClass="select2">
		                     						<form:option value="0" disabled="true" label="--Select Donor--"/>
		                     						<form:options items="${donors}"/>
		                     					</form:select>
		                     				</div>
		                     				<div class="form-group col-lg-6">
		                     					<form:label path="amount">Amount <span class="text-danger">*</span></form:label>
		                     					<form:input path="amount" cssClass="form-control"/>
		                     				</div>
		                     				<div class="form-group col-lg-6">
		                     					<button type="submit" name="submit"  class="btn btn-success">Add</button>
		                     				</div>
	                     				</div>
	                     			</form:form>
	                     		</div>
	                     		<div class="col-lg-12">
	                     			<table class="table table-bordered table-hover" id="donor-table">
	                     				<thead>
	                     					<tr>
	                     						<th>S/L No</th>
	                     						<th>Donor</th>
	                     						<th>Amount</th>
	                     						<th>Option</th>
	                     					</tr>
	                     				</thead>
	                     				<tbody></tbody>
	                     			</table>
	                     		</div>
	                     	</div>
	                    </div>
	                    <div class="tab-pane" id="exampleTabsLineLeftThree" role="tabpanel">
	                      	<div class="row">
	                      		<div class="col-lg-12">
	                      			<label for="completion-image-file" class="btn btn-success custom-file-upload" style="margin-top: 2%;">
									    <i class="fa fa-cloud-upload"></i> Upload Completion Images
									</label>
									<input id="completion-image-file" type="file" accept="image/png, image/gif, image/jpeg" multiple="multiple"/>
									
									<div class="form-group">
										<label for="completion_description">Completion Description</label>
										<textarea class="form-control" name="completion_description" id="completion_description">${ticket.completion_description}</textarea>
									</div>
									<button class="btn btn-primary" id="completeBtn">Completed</button>
	                      		</div>
	                      		<div id="viewCompletionPercent" class="col-lg-12">
	                      			
	                      		</div>
	                      	</div>
	                    </div>
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
  <script type="text/javascript" src="${url}pageJs/ticket/viewTicket.js"></script>
  <script type="text/javascript">
  	$('.select2').select2({ width: '100%' });
  </script>
</body>
</html>