$(document).ready(function(){
	//reset the state form
	$('#resetButton').on('click',function(){
		$('#submitButton').text('Add District');
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
        	url:`${ctx}/School/getSchoolList`,
        	type:"get",
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			},
        	data : function ( d ) {
	                  return $.extend( {}, d, {
                        school_name:$('#school_name').val()
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
        		return '<b><a href="#">'+data.school_name+'</a></b>'
        	}},
        	{"targets":[1],"data":"school_address"},
        	{"targets":[2],"data":"hm_phone"},
            {"targets":[3],"data":"school_strength"},
            {"targets":[4],"data":"state_name"},
            {"targets":[5],"data":"district_name"},
            {"targets":[6],"data":"taluk_name"},
            {"targets":[7],"data":"hobli_name"},
            {"targets":[8],"data":"village_name"},
        	{"targets":[9],"data":function(data){
        		return data;
        	},
        	"render":function(data,row){
        		 let html = `
        		 	<a id="${data.district_id}" name="${data.district_name}" class="update-district" style="cursor:pointer;">
        		 		<span class="text-success"><i class="fa fa-edit"></i></span>
        		 	</a>
        		 	&nbsp;&nbsp;&nbsp;
        		 	<a id="${data.school_id}" name="${data.school_name}" class="delete-school" style="cursor:pointer;">
        		 		<span class="text-danger"><i class="fa fa-trash"></i></span>
        		 	</a>
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
	
	
	
	//for delete option
	$(document).on('click','#school-table .delete-school',function(){
		console.log('cliking');
		let school_id = $(this).attr('id');
		let school_name = $(this).attr('name');
		swal(
				{title:`Are you sure want to delete  ${school_name}?`,
				text:`You can\'t recover this  ${school_name}!`,
				type:"warning",
				showCancelButton:!0,
				confirmButtonClass:"btn-warning",
				confirmButtonText:"Yes, delete it!",
				closeOnConfirm:!1
				},
				function(){
					fetch(`${ctx}/School/deleteSchool/${school_id}`,{
						method:'delete',
						headers:{
							'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
						}
					}).then(response => response.text())
					  .then(data => {
						console.log(data);
						if(data>0){
							 swal("Deleted!","Deleted Successfully","warning");
							 table.draw();
						}else{
							 swal("Failed!","Failed to delete","error");
						}
					}).catch(data => {
						swal("Failed!","School already in use!!","error");
					});
				}
			);
	});
	
	
	
	//geting single state data for editing purpose
	$(document).on('click','#district-table .update-district',function(){
		let district_id = $(this).attr('id');
		fetch(`${ctx}/Region/getSingleDistrict/${district_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
		  .then(data => {
			$('#district-form input,#district-form select').each(function(){
				let inputName = $(this).attr('name');
				if(inputName!='_csrf'){
					$('#'+inputName).val(data[inputName]);
				}
			});
			$('#submitButton').text('Update District');
		});
	});
	
	
	
	
	
	
	
	
	
	
	
});