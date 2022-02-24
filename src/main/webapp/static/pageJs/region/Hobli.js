$(document).ready(function(){
	
	
	
	let fetcDistrictByStateMethod = (state_id) => {
		return fetch(`${ctx}/Region/stateWiseDistrictList/${state_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		});
	}
	
	
	let fetchDistrictByState = () => {
		let state_id = $('#state_id').val();
		fetcDistrictByStateMethod(state_id).then(response => response.json())
		  .then(data => {
			$('#district_id').empty();
			$('#district_id').append(`<option value="" selected disabled label="--Select Dist--">Select</option>`);
			Object.entries(data).forEach(function(value,key){
				$('#district_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	$('#state_id').on('change',function(){
		fetchDistrictByState();
	});
	
	
	let fetchDistrictWiseTalukMethod = (district_id) => {
		return fetch(`${ctx}/Region/getDistrictWiseTalukForOption/${district_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		});
	}
	
	let fetchDistrictWiseTalukForOption = () => {
		let district_id = $('#district_id').val();
		fetchDistrictWiseTalukMethod(district_id).then(response => response.json())
		  .then(data => {
			$('#taluk_id').empty();
			$('#taluk_id').append(`<option value="" selected disabled label="--Select Taluk--">Select</option>`);
			Object.entries(data).forEach(function(value) {
				$('#taluk_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	$('#district_id').on('change',function(){
		fetchDistrictWiseTalukForOption();
	});
	
	
	
	
	$('#hobli-form').validate({
		debug:false,
		rules:{
			district_name:"required",
			state_id:"required",
			district_id:"required",
			taluk_id:"required"
		},
		messages:{
			district_name:"Enter District Name",
			state_id:"Select State",
			district_id:"Select District",
			taluk_id:"Select Taluk"
		},
		submitHandler:function(form){
			let hobli_id = $('#hobli_id').val();
			let myForm = document.getElementById('hobli-form');
			let formData = new FormData(myForm);
			if(hobli_id==0 || hobli_id==''){
				fetch(`${ctx}/Region/addHobli`,{
					method:'POST',
					headers:{
						'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
					},
					body:formData
				}).then(response => response.text())
				  .then(data => {
					if(data>0){
						toastr.success('Success!', 'Hobli added successfully');
						table.draw();
						document.getElementById("district-form").reset();
					}
				});
			}else{
				fetch(`${ctx}/Region/updateHobli`,{
					method:'put',
					headers:{
						'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
					},
					body:formData
				}).then(response => response.text())
				  .then(data => {
					if(data>0){
						toastr.success('District updated successfully', 'Success!');
						document.getElementById("hobli-form").reset();
						$('#submitButton').text('Add District');
						table.draw();
					}
				});
			}
		}
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
	
	
	
	
	var table =   $('#hobli-table').DataTable({
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
        	url:`${ctx}/Region/getHobliList`,
        	type:"get",
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			},
        	data : function ( d ) {
	                  return $.extend( {}, d, {
							state_id:$('#state_id_search').val(),
							district_id:$('#district_id_search').val(),
							taluk_id:$('#taluk_id_search').val(),
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
        		return '<b><a href="#">'+data.hobli_id+'</a></b>'
        	}},
        	{"targets":[1],"data":"hobli_name"},
        	{"targets":[2],"data":"taluk_name"},
        	{"targets":[3],"data":"district_name"},
        	{"targets":[4],"data":"state_name"},
        	{"targets":[5],"data":function(data){
        		return data;
        	},
        	"render":function(data,row){
        		 let html = `
        		 	<a id="${data.hobli_id}" name="${data.hobli_name}" class="update-hobli" style="cursor:pointer;">
        		 		<span class="text-success"><i class="fa fa-edit"></i></span>
        		 	</a>
        		 	&nbsp;&nbsp;&nbsp;
        		 	<a id="${data.hobli_id}" name="${data.hobli_name}" class="delete-hobli" style="cursor:pointer;">
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
	
	let fetchDistrictOptionForFilter = () => {
		let state_id = $('#state_id_search').val();
		fetcDistrictByStateMethod(state_id).then(response => response.json())
		  .then(data => {
			$('#district_id_search').empty();
			$('#district_id_search').append(`<option value="0" selected label="Select Dist">Select</option>`);
			Object.entries(data).forEach(function(value,key){
				$('#district_id_search').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	$('#state_id_search').on('change',function(){
		fetchDistrictOptionForFilter();
	});
	
	
	let fetchTaluOptionForFilter = () => {
		let district_id = $('#district_id_search').val();
		fetchDistrictWiseTalukMethod(district_id).then(response => response.json())
		  .then(data => {
			$('#taluk_id_search').empty();
			$('#taluk_id_search').append(`<option value="0" selected label="--Select Taluk--">Select</option>`);
			Object.entries(data).forEach(function(value) {
				$('#taluk_id_search').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	$('#district_id_search').on('change',function(){
		fetchTaluOptionForFilter();
	});
	
	
	
	//for delete option
	$(document).on('click','#hobli-table .delete-hobli',function(){
		let hobli_id = $(this).attr('id');
		let hobli_name = $(this).attr('name');
		swal(
				{title:`Are you sure want to delete District ${hobli_name}?`,
				text:`You can\'t recover this District ${hobli_name}!`,
				type:"warning",
				showCancelButton:!0,
				confirmButtonClass:"btn-warning",
				confirmButtonText:"Yes, delete it!",
				closeOnConfirm:!1
				},
				function(){
					fetch(`${ctx}/Region/deleteHobli/${hobli_id}`,{
						method:'delete',
						headers:{
							'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
						}
					}).then(response => response.text())
					  .then(data => {
						if(data>0){
							 swal("Deleted!","Deleted Successfully","warning");
							 table.draw();
						}else{
							 swal("Failed!","Failed to delete","error");
						}
					}).catch(data => {
						swal("Failed!","District already in use!!","error");
					});
				}
			);
	});	
	
	
	//geting single state data for editing purpose
	$(document).on('click','#districtTableNAme .update-hobli',function(){
		let hobli_id = $(this).attr('id');
		fetch(`${ctx}/Region/getSingleHobliData/${hobli_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
		  .then(data => {
			document.getElementById("hobli-form").reset();
			$('#state_id').val(data.state_id);
			//first path
			let state_id = $('#state_id').val();
			fetcDistrictByStateMethod(state_id).then(response => response.json())
			  .then(districtData => {
				$('#district_id').empty();
				$('#district_id').append(`<option value=""  disabled label="--Select Dist--">Select</option>`);
				Object.entries(districtData).forEach(function(value){
					$('#district_id').append(`<option value="${value[0]}" ${(value[0]==data.district_id)?'selected':''}>${value[1]}</option>`);
				});
				//second path
				let district_id = $('#district_id').val();
				fetchDistrictWiseTalukMethod(district_id).then(response => response.json())
				  .then(talukData => {
					$('#taluk_id').empty();
					$('#taluk_id').append(`<option value=""  disabled label="--Select Taluk--">Select</option>`);
					Object.entries(talukData).forEach(function(value) {
						$('#taluk_id').append(`<option value="${value[0]}" ${(value[0]==data.taluk_id)?'selected':''}>${value[1]}</option>`);
					});
					//third path
					$('#hobli_id').val(data.hobli_id);
					$('#hobli_name').val(data.hobli_name);
				});
				
			});
		});
	});
	
	
	
	
	
});