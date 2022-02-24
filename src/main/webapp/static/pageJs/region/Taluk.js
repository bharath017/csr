$(document).ready(function(){
	//reset the state form
	
	$('#resetButton').on('click',function(){
		$('#submitButton').text('Add Taluk');
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
	
	
	
	
	var table =   $('#taluk-table').DataTable({
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
        	url:`${ctx}/Region/talukList`,
        	type:"get",
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			},
        	data : function ( d ) {
	                  return $.extend( {}, d, {
							state_id:$('#state_id_search').val(),
							district_id:$('#district_id_search').val(),
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
        		return '<b><a href="#">'+data.taluk_id+'</a></b>'
        	}},
        	{"targets":[1],"data":"state_name"},
        	{"targets":[2],"data":"district_name"},
        	{"targets":[3],"data":"taluk_name"},
			{"targets":[4],"data":function(data){
        		return data;
        	},
        	"render":function(data,row){
        		 let html = `
				 <a id="${data.taluk_id}" class="edit-taluk">
				 <span class="text-success"><i class="icon fa-edit"></i></span>
			 </a>
			 &nbsp;&nbsp;&nbsp;
			 <a id="${data.taluk_id}" name="${data.taluk_name}" class="delete-taluk">
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
	
	$('#search').on('change',function(){
		table.draw();
	});
	
	let fetchDistrictByDistrictMethod=(state_id)=>{
		return	fetch(`${ctx}/Region/stateWiseDistrictList/${state_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
	}

	let fetchDistrictByState = () => {
		let state_id = $('#state_id').val();
		console.log(state_id);
		fetchDistrictByDistrictMethod(state_id)
		  .then(data => {
			$('#district_id').empty();
			$('#district_id').append(`<option value="" selected disabled label="--Select Dist--">Select District</option>`);
			 Object.entries(data).forEach(function(value,key){
				$('#district_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}

	let fetchDistrictByStateSearch = () => {
		let state_id = $('#state_id_search').val();
		console.log(state_id);
		fetchDistrictByDistrictMethod(state_id)
		  .then(data => {
			$('#district_id_search').empty();
			$('#district_id_search').append(`<option value="0" selected  label="--Select Dist--">Select District</option>`);
			 Object.entries(data).forEach(function(value,key){
				$('#district_id_search').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	$('#state_id').on('change',function(){
		 fetchDistrictByState();
		
	});

	$('#state_id_search').on('change',function(){
		fetchDistrictByStateSearch();
		table.draw();
	   
   });

   
	$('#district_id_search').on('change',function(){
		table.draw();
	   
   });
   $('#search').on('keyup',function(){
	table.draw();
   
});


	//geting single state data for editing purpose
	$(document).on('click','#taluk-table .edit-taluk',function(){
		let taluk_id = $(this).attr('id');
		fetch(`${ctx}/Region/getSingleTaluk/${taluk_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
		  .then(talukData => {
			fetchDistrictByDistrictMethod(talukData.state_id)
			.then(districtData => {
			  $('#district_id').empty();
			  $('#district_id').append(`<option value="0" selected  label="--Select Dist--">Select</option>`);
			   Object.entries(districtData).forEach(function(value,key){
				  $('#district_id').append(`<option value="${value[0]}" ${(value[0]==talukData.district_id)?'selected':''}>${value[1]}</option>`);
			  });
			  
		  });
		  $('#taluk_id').val(talukData.taluk_id);
		  $('#taluk_name').val(talukData.taluk_name);
		  $('#state_id').val(talukData.state_id);
		});
		$('#submitButton').text('Update Sate');
	});

	$(document).on('click','#taluk-table .delete-taluk',function(){
		let taluk_id = $(this).attr('id');
		let taluk_name = $(this).attr('name');
		swal(
				{title:`Are you sure want to delete Taluk ${taluk_name}?`,
				text:`You can\'t recover this Taluk ${taluk_name}!`,
				type:"warning",
				showCancelButton:!0,
				confirmButtonClass:"btn-warning",
				confirmButtonText:"Yes, delete it!",
				closeOnConfirm:!1
				},
				function(){
					fetch(`${ctx}/Region/deleteTaluk/${taluk_id}`,{
						method:'delete',
						headers:{
							'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
						}
					}).then(response => response.text())
					  .then(data => {
						if(data>0){
							$('#district_id').empty();
							$('#district_id').append(`<option value="" selected disabled label="--Select Dist--">Select District</option>`);
							 swal("Deleted!","Deleted Successfully","warning");
							 table.draw();
						}else{
							 swal("Failed!","Failed to delete","error");
						}
					}).catch(data => {
						swal("Failed!","Taluk already in use!!","error");
					});
				}
			);
	});

	$('#taluk-form').validate({
		debug:false,
		rules:{
			district_id:"required",
			taluk_name:"required"
		},
		messages:{
			district_id:"Select District",
			taluk_name:"Enter Taluk name"
		},
		submitHandler:function(form){
			let taluk_id = $('#taluk_id').val();
			let myForm = document.getElementById('taluk-form');
			let formData = new FormData(myForm);
			console.log(formData);
			if(taluk_id==0 || taluk_id==''){
				fetch(`${ctx}/Region/addTaluk`,{
					method:'POST',
					headers:{
						'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
					},
					body:formData
				}).then(response => response.text())
				  .then(data => {
					if(data>0){
						toastr.success('Success!', 'Taluk added successfully');
						document.getElementById("taluk-form").reset();
						table.draw();
					}
				});
			}else{
				fetch(`${ctx}/Region/updateTaluk`,{
					method:'put',
					headers:{
						'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
					},
					body:formData
				}).then(response => response.text())
				  .then(data => {
					if(data>0){
						toastr.success('Taluk updated successfully', 'Success!');
						document.getElementById("taluk-form").reset();
						$('#submitButton').text('Add Taluk');
						table.draw();
					}
				});
			}
		}
	});
	
});