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
	
	
	
	
	var table =   $('#cluster-table').DataTable({
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
        	url:`${ctx}/Cluster/getClusterList`,
        	type:"get",
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			},
        	data : function ( d ) {
	                  return $.extend( {}, d, {
                        state_id:$('#state_id_search').val(),
                        district_id:$('#district_id_search').val(),
                        taluk_id:$('#taluk_id_search').val(),
                        hobli_id:$('#hobli_id_search').val(),
                        search:$('#search').val()
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
        		return '<b><a href="#">'+data.cluster_id+'</a></b>'
        	}},
        	{"targets":[1],"data":"state_name"},
        	{"targets":[2],"data":"district_name"},
            {"targets":[3],"data":"cluster_resource_center_name"},
            {"targets":[4],"data":"cluster_resource_center_address"},
            {"targets":[5],"data":"cluster_resource_person_name"},
        	{"targets":[6],"data":function(data){
        		return data;
        	},
        	"render":function(data,row){
        		 let html = `
        		 	<a id="${data.cluster_id}" name="${data.cluster_name}" class="update-district" style="cursor:pointer;">
        		 		<span class="text-success"><i class="fa fa-edit"></i></span>
        		 	</a>
        		 	&nbsp;&nbsp;&nbsp;
        		 	<a id="${data.cluster_id}" name="${data.cluster_name}" class="delete-cluster" style="cursor:pointer;">
        		 		<span class="text-danger"><i class="fa fa-trash"></i></span>
        		 	</a>
        		 `
                 return html;
        	}}  	
        ]
    });
    
    
    $('#state_id_search').on('change',function(){
		table.draw();
	});
	
	$('#search').on('keyup blur',function(){
		table.draw();
	});
	
	
	
	//for delete option
	$(document).on('click','#cluster-table .delete-cluster',function(){
		let cluster_id = $(this).attr('id');
		let cluster_name = $(this).attr('name');
		swal(
				{title:`Are you sure want to delete  ${cluster_name}?`,
				text:`You can\'t recover this  ${cluster_name}!`,
				type:"warning",
				showCancelButton:!0,
				confirmButtonClass:"btn-warning",
				confirmButtonText:"Yes, delete it!",
				closeOnConfirm:!1
				},
				function(){
					fetch(`${ctx}/Cluster/deleteCluster/${cluster_id}`,{
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
						swal("Failed!","Cluster already in use!!","error");
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
	
	
	
	$('#district-form').validate({
		debug:false,
		rules:{
			district_name:"required",
			state_id:"required"
		},
		messages:{
			district_name:"Enter District Name",
			state_id:"Select State"
		},
		submitHandler:function(form){
			let district_id = $('#district_id').val();
			let myForm = document.getElementById('district-form');
			let formData = new FormData(myForm);
			if(district_id==0 || district_id==''){
				fetch(`${ctx}/Region/addDistrict`,{
					method:'POST',
					headers:{
						'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
					},
					body:formData
				}).then(response => response.text())
				  .then(data => {
					if(data>0){
						toastr.success('Success!', 'District added successfully');
						table.draw();
						document.getElementById("district-form").reset();
					}
				});
			}else{
				fetch(`${ctx}/Region/updateDistrict`,{
					method:'put',
					headers:{
						'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
					},
					body:formData
				}).then(response => response.text())
				  .then(data => {
					if(data>0){
						toastr.success('District updated successfully', 'Success!');
						document.getElementById("district-form").reset();
						$('#submitButton').text('Add District');
						table.draw();
					}
				});
			}
		}
	});
	
	
	
	
	
	
});