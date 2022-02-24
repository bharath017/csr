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
  <title>Ticket Category</title>

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
      <h1 class="page-title">Ticket Category &amp; Sub Category</h1>
      <div class="page-header-actions">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="../index.html">Home</a></li>
          <li class="breadcrumb-item"><a href="javascript:void(0)">Ticket</a></li>
          <li class="breadcrumb-item active">Ticket Category &amp; Sub Category</li>
        </ol>
      </div>
    </div>

    <div class="page-content">
      <div class="row row-lg">
      	<div class="col-lg-5">
      		<div class="panel">
        		<div class="panel-body container-fluid">
        			<div class="row">
        				<div class="col-lg-8 align-middle">
        					<h4 class="example-title">Ticket Category</h4>
        				</div>
        				<div class="col-lg-4">
        					<button class="btn btn-success"
        							 type="button" id="addTicketCategoryBtn"
        							 data-target="#exampleNiftyFadeScale" data-toggle="modal">Add Category</button>	
        				</div>
        			</div>
        			<br/>
        			<form id="ticket-category-filter-form">
        			<div class="row">
        					<div class="col-lg-12">
	        					<input type="text" class="form-control" placeholder="Search.." id="categorySearch"/>
	        				</div>
        			</div>
        			</form>
        			<div class="example table-responsive">
		                  <table class="table" id="ticket-category-table">
		                    <thead>
		                      <tr>
		                        <th>S/L No</th>
		                        <th>Category Name</th>
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
      	<div class="col-lg-7">
      		<div class="panel">
        		<div class="panel-body container-fluid">
        			<div class="example-wrap">
		                <div class="row">
	        				<div class="col-lg-8 align-middle">
	        					<h4 class="example-title">Ticket Sub Category</h4>
	        				</div>
	        				<div class="col-lg-4">
	        					<button class="btn btn-success"
	        					 type="button" data-target="#addSubCategoryModal" data-toggle="modal">Add Sub Category</button>	
	        				</div>
	        			</div>
	        			<br/>
	        			<form id="ticket-category-filter-form">
		        			<div class="row">
		        					<div class="col-lg-6">
			        					<input type="text" class="form-control" placeholder="Search.." id="subCategorySearch"/>
			        				</div>
			        				<div class="col-lg-6">
			        					<select id="category_id_search" class="form-control">
			        						<option value="0" selected="selected">All Category</option>
			        					</select>
			        				</div>
		        			</div>
	        			</form>
		                <div class="example table-responsive">
		                  <table class="table" id="ticket-sub-table">
		                    <thead>
		                      <tr>
		                        <th>Sub Category ID</th>
		                        <th>Sub Category Name</th>
		                        <th>Category</th>
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
  
  
  <!-- Modal -->
  <div class="modal fade modal-primary modal-super-scaled" id="exampleNiftyFadeScale" aria-hidden="true"
    aria-labelledby="exampleModalPrimary" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-center">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
          <h6 class="modal-title">Add Ticket Category</h6>
        </div>
        <div class="modal-body">
          <form:form modelAttribute="category" id="ticket-category-form">
          	<div class="form-group">
          		<form:label path="category_name">Category Name <span class="text-danger">*</span></form:label>
          		<form:input path="category_name" cssClass="form-control" id="category_name" placeholder="Enter category.."/>
          	</div>
          </form:form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" id="saveTicketCategoryBtn">Save Category</button>
        </div>
      </div>
    </div>
  </div>
  <!-- End Modal -->
  
  
  <!-- Sub Category Modal -->
  <div class="modal fade modal-primary modal-super-scaled" id="addSubCategoryModal" aria-hidden="true"
    aria-labelledby="exampleModalPrimary" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-center">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
          <h6 class="modal-title">Add Ticket Sub Category</h6>
        </div>
        <div class="modal-body">
          <form:form modelAttribute="subcategory" id="ticket-sub-category-form">
          	<div class="form-group">
          		<form:label path="sub_category_name">Sub Category Name <span class="text-danger">*</span></form:label>
          		<form:input path="sub_category_name" cssClass="form-control" id="sub_category_name" placeholder="Enter Sub category.."/>
          	</div>
          	
          	<div class="form-group">
          		<form:label path="category_id">Category <span class="text-danger">*</span></form:label>
          		<form:select path="category_id" cssClass="form-control">
          			<form:option value="0" disabled="true" label="--Select Category--"/>
          		</form:select>
          	</div>
          </form:form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" id="saveTicketSubCategoryBtn">Save Category</button>
        </div>
      </div>
    </div>
  </div>
  <!-- End of add sub category modal -->


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
  <script type="text/javascript" src="${url}pageJs/ticket/TicketCategory.js"></script>

</body>
</html>