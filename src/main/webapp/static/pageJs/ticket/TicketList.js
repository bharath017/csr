$(document).ready(function(){
	//get all school
	let fetchAllSchoolOption = () => {
		return fetch(`${ctx}/Ticket/getAllSchoolForOption`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		});
	}
	
	let getAllSchoolForOption = () => {
		fetchAllSchoolOption().then(response => response.json())
		.then(data => {
			$('#school_id').empty();
			$('#school_id').append(`<option value="0" selected  label="--All School--">All School</option>`);
			Object.entries(data).forEach(function(value) {
				$('#school_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	getAllSchoolForOption();
	
	
	//for getting village list for option
	let fetchTicketCategoryForOption = () => {
		return fetch(`${ctx}/Ticket/getTicketCategoryForOption`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		});
	}
	
	let getTicketCategoryForOptionView = () => {
		fetchTicketCategoryForOption().then(response => response.json())
		.then(data => {
			$('#category_id').empty();
			$('#category_id').append(`<option value="0" selected  label="--All Category--">All Category</option>`);
			Object.entries(data).forEach(function(value) {
				$('#category_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	
	getTicketCategoryForOptionView();
	
	//get ticket sub category option
	
	let fetchTicketSubCategoryOption = (category_id) => {
		return fetch(`${ctx}/Ticket/getTicketSubCategoryForOption/${category_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		});
	}
	
	let getSubCategoryForOption = () => {
		let category_id = $('#category_id').val();
		if(category_id==null)
			return false;
		fetchTicketSubCategoryOption(category_id).then(response => response.json())
		.then(data => {
			$('#sub_category_id').empty();
			$('#sub_category_id').append(`<option value="0" selected  label="--All Sub Category--">All Sub Category</option>`);
			Object.entries(data).forEach(function(value) {
				$('#sub_category_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	$('#category_id').on('change',function(){
		getSubCategoryForOption();
	});
	
	
	
	//proper proper option visibility
	$('.table-responsive').on('shown.bs.dropdown', function (e) {
	  		var $table = $(this),
	        $menu = $(e.target).find('.dropdown-menu'),
	        tableOffsetHeight = $table.offset().top + $table.height(),
	        menuOffsetHeight = $menu.offset().top + $menu.outerHeight(true);
	    if (menuOffsetHeight+100 > tableOffsetHeight)
	      $('.dataTables_scrollBody').css('padding-bottom',(menuOffsetHeight - tableOffsetHeight)+50);
    });

	$('.table-responsive').on('hide.bs.dropdown', function () {
	    $('.dataTables_scrollBody').css("padding-bottom", 0);
	});
	
	
	
	
	var table =   $('#school-table').DataTable({
		"order": [],
        "info":true,
        "scrollX":true,
        "processing":true,
        "serverSide" : true,
        "iDisplayLength":10,
        "lengthMenu":[10,20,25,50,75,100],
        "ordering":false,
        "searching":false,
        "dom": 'Blfrtip',
        "buttons": [
            'copyHtml5',
            'excelHtml5',
            'csvHtml5',
            'pdfHtml5'
        ],
        lengthChange: true,
        "ajax":{
        	url:`${ctx}/Ticket/getAllTicketList`,
        	type:"get",
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			},
        	data : function ( d ) {
	                  return $.extend( {}, d, {
                        school_id:$('#school_id').val(),
                        category_id:$('#category_id').val(),
                        sub_category_id:$('#sub_category_id').val(),
                        ticket_status:$('#ticket_status').val()
	                  });
	         },
        	error:function(){
        	}
        },
        "columnDefs":[
        	{"targets":[0],"data":function(data){
        		return data;
        	},
        	"render":function(data){
        		return '<b><a href="#">'+data.ticket_id+'</a></b>'
        	}},
        	{"targets":[1],"data":"school_name"},
        	{"targets":[2],"data":"category_name"},
        	{"targets":[3],"data":"sub_category_name"},
            {"targets":[4],"data":"ticket_date"},
            {"targets":[5],"data":"description"},
            {"targets":[6],"data":"ticket_status"},
        	{"targets":[7],"data":function(data){
        		return data;
        	},
        	"render":function(data,row){
        		 let html = `
        		 	<a class="btn btn-info text-white" href="${ctx}/Ticket/viewTicket/${data.ticket_id}">View</a>
        		 `
                 return html;
        	}}  	
        ]
    });
    
    
    $('#searchBtn').on('click',function(){
		table.draw();
	});
	
	$('#clearBtn').on('click',function(){
		document.getElementById("search-form").reset();
		table.draw();
	});
	
	
	
	
	
});