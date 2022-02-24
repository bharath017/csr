$(document).ready(function(){
	
	$('.loader').hide();
	
	let fetcDistrictByStateMethod = (state_id) => {
		return fetch(`${ctx}/Region/stateWiseDistrictList/${state_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
	}
	
	
	let fetchDistrictByState = () => {
		let state_id = $('#state_id').val();
		fetcDistrictByStateMethod(state_id)
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
		}).then(response => response.json())
	}
	
	let fetchDistrictWiseTalukForOption = () => {
		let district_id = $('#district_id').val();
		fetchDistrictWiseTalukMethod(district_id)
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
	
	
	
	
	$('#village-form').validate({
		debug:false,
		rules:{
			village_name:"required",
			state_id:"required",
			district_id:"required",
			taluk_id:"required",
            hobli_id:"required"
		},
		messages:{
			village_name:"Enter Village Name",
			state_id:"Select State",
			district_id:"Select District",
			taluk_id:"Select Taluk",
            hobli_id:"Select Hobli"
		},
		submitHandler:function(form){
			let village_id = $('#village_id').val();
			let myForm = document.getElementById('village-form');
			let formData = new FormData(myForm);
			if(village_id==0 || village_id==''){
				fetch(`${ctx}/Region/addVillage`,{
					method:'POST',
					headers:{
						'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
					},
					body:formData
				}).then(response => response.text())
				  .then(data => {
					if(data>0){
						toastr.success('Success!', 'Village added successfully');
						table.draw();
                        resetForm();
						document.getElementById("village-form").reset();
					}
				});
			}else{
				fetch(`${ctx}/Region/updateVillage`,{
					method:'put',
					headers:{
						'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
					},
					body:formData
				}).then(response => response.text())
				  .then(data => {
					if(data>0){
						toastr.success('Village updated successfully', 'Success!');
						document.getElementById("village-form").reset();
						$('#submitButton').text('Add Village');
                        resetForm();
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
	
	
	
	
	var table =   $('#village-table').DataTable({
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
        	url:`${ctx}/Region/getVillageList`,
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
        		return '<b><a href="#">'+data.village_id+'</a></b>'
        	}},
            {"targets":[1],"data":"village_name"},
        	{"targets":[2],"data":"hobli_name"},
        	{"targets":[3],"data":"taluk_name"},
        	{"targets":[4],"data":"district_name"},
        	{"targets":[5],"data":"state_name"},
        	{"targets":[6],"data":function(data){
        		return data;
        	},
        	"render":function(data,row){
        		 let html = `
        		 	<a id="${data.village_id}" name="${data.village_name}" class="update-village" style="cursor:pointer;">
        		 		<span class="text-success"><i class="fa fa-edit"></i></span>
        		 	</a>
        		 	&nbsp;&nbsp;&nbsp;
        		 	<a id="${data.village_id}" name="${data.village_name}" class="delete-village" style="cursor:pointer;">
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

    $('#resetButton').on('click',function(){
        resetForm();
	});
	
	let fetchDistrictOptionForFilter = () => {
		let state_id = $('#state_id_search').val();
		fetcDistrictByStateMethod(state_id)
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


    $('#taluk_id_search').on('change',function(){
		fetchTalukWiseHobliForFilter();
	});
	

    // let fetchTalukWiseHobliForOption = () => {
	// 	let taluk_id = $('#taluk_id').val();
	// 	if(taluk_id==null)
	// 		return false;
	// 	fetchTalukWiseHobliMethod(taluk_id).then(response => response.json())
	// 	.then(data => {
	// 		$('#hobli_id').empty();
	// 		$('#hobli_id').append(`<option value="" selected disabled label="--Select Hobli--">Select</option>`);
	// 		Object.entries(data).forEach(function(value) {
	// 			$('#hobli_id').append(`<option value="${value[0]}">${value[1]}</option>`);
	// 		});
	// 	});
	// }

    let fetchTalukWiseHobliForFilter = () => {
		let taluk_id = $('#taluk_id_search').val();
		if(taluk_id==null)
			return false;
		fetchTalukWiseHobliMethod(taluk_id).then(response => response.json())
		.then(data => {
			$('#hobli_id_search').empty();
			$('#hobli_id_search').append(`<option value="" selected disabled label="--Select Hobli--">Select</option>`);
			Object.entries(data).forEach(function(value) {
				$('#hobli_id_search').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}

	
	let fetchTaluOptionForFilter = () => {
		let district_id = $('#district_id_search').val();
		fetchDistrictWiseTalukMethod(district_id)
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
	

    	//for getting hobli list for opton
	let fetchTalukWiseHobliMethod = (taluk_id) => {
		return fetch(`${ctx}/Region/getTalukWiseHobliListOption/${taluk_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		});
	}
	
	let fetchTalukWiseHobliForOption = () => {
		let taluk_id = $('#taluk_id').val();
		if(taluk_id==null)
			return false;
		fetchTalukWiseHobliMethod(taluk_id).then(response => response.json())
		.then(data => {
			$('#hobli_id').empty();
			$('#hobli_id').append(`<option value="" selected disabled label="--Select Hobli--">Select</option>`);
			Object.entries(data).forEach(function(value) {
				$('#hobli_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	$('#taluk_id').on('change',function(){
		fetchTalukWiseHobliForOption();
	});
	
    let resetForm=()=>{
        $('#taluk_id').empty();
        $('#taluk_id').append(`<option value=""  disabled label="--Select Taluk--">Select</option>`);
        $('#district_id').empty();
        $('#district_id').append(`<option value=""  disabled label="--Select Dist--">Select</option>`);
        $('#hobli_id').empty();
		$('#hobli_id').append(`<option value=""  disabled label="--Select Hobli--">Select Hobli</option>`);
        $('#village_id').val(0);
        $('#village_name').empty();
    }
	
	//for delete option
	$(document).on('click','#village-table .delete-village',function(){
		let village_id = $(this).attr('id');
		let village_name = $(this).attr('name');
		swal(
				{title:`Are you sure want to delete Village : ${village_name}?`,
				text:`You can\'t recover this Village : ${village_name} !`,
				type:"warning",
				showCancelButton:!0,
				confirmButtonClass:"btn-warning",
				confirmButtonText:"Yes, delete it!",
				closeOnConfirm:!1
				},
				function(){
					fetch(`${ctx}/Region/deleteVillage/${village_id}`,{
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
						swal("Failed!","Village already in use!!","error");
					});
				}
			);
	});	
	
	
	//geting single state data for editing purpose
	$(document).on('click','#village-table .update-village',function(){
        $('.loader').show();
		let village_id = $(this).attr('id');
		fetch(`${ctx}/Region/getSingleVillageData/${village_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
		  .then(data => {
			document.getElementById("village-form").reset();
			$('#state_id').val(data.state_id);
            $('#submitButton').text('Update Village');
			//first path
			let state_id = $('#state_id').val();
			fetcDistrictByStateMethod(state_id)
			  .then(districtData => {
				$('#district_id').empty();
				$('#district_id').append(`<option value=""  disabled label="--Select Dist--">Select</option>`);
				Object.entries(districtData).forEach(function(value){
					$('#district_id').append(`<option value="${value[0]}" ${(value[0]==data.district_id)?'selected':''}>${value[1]}</option>`);
				});
				//second path
				let district_id = $('#district_id').val();
				fetchDistrictWiseTalukMethod(district_id)
				  .then(talukData => {
					$('#taluk_id').empty();
					$('#taluk_id').append(`<option value=""  disabled label="--Select Taluk--">Select</option>`);
					Object.entries(talukData).forEach(function(value) {
						$('#taluk_id').append(`<option value="${value[0]}" ${(value[0]==data.taluk_id)?'selected':''}>${value[1]}</option>`);
					});
					//third path
                    let taluk_id = $('#taluk_id').val();
                    fetchTalukWiseHobliMethod(taluk_id).then(response => response.json())
				  .then(hobliData => {
					$('#hobli_id').empty();
					$('#hobli_id').append(`<option value=""  disabled label="--Select Hobli--">Select Hobli</option>`);
					Object.entries(hobliData).forEach(function(value) {
						$('#hobli_id').append(`<option value="${value[0]}" ${(value[0]==data.hobli_id)?'selected':''}>${value[1]}</option>`);
					});
					//third path
					$('#village_id').val(data.village_id);
					$('#village_name').val(data.village_name);
                    
                    $('.loader').hide();
				});
					
				});
				
			});
		});
	});
	
	
	
	
	
});