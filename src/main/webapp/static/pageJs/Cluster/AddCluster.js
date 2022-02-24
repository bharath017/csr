$(document).ready(function(){
	
	//for getting district list for option
	let fetcDistrictByStateMethod = (state_id) => {
		return fetch(`${ctx}/Region/stateWiseDistrictList/${state_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		});
	}
	
	let fetchDistrictByState = () => {
		let state_id = $('#state_id').val();
		if(state_id==null)
			return false;
		fetcDistrictByStateMethod(state_id).then(response => response.json())
		  .then(data => {
			$('#district_id').empty();
			$('#district_id').append(`<option value="" selected disabled label="--Select Dist--">Select District</option>`);
			Object.entries(data).forEach(function(value,key){
				$('#district_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	$('#state_id').on('change',function(){
		fetchDistrictByState();
	});
	
	
	//for getting taluk list for option
	let fetchDistrictWiseTalukMethod = (district_id) => {
		return fetch(`${ctx}/Region/getDistrictWiseTalukForOption/${district_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		});
	}
	
	let fetchDistrictWiseTalukForOption = () => {
		let district_id = $('#district_id').val();
		if(district_id==null)
			return false;
		fetchDistrictWiseTalukMethod(district_id).then(response => response.json())
		  .then(data => {
			$('#taluk_id').empty();
			$('#taluk_id').append(`<option value="" selected disabled label="--Select Taluk--">Select Taluk</option>`);
			Object.entries(data).forEach(function(value) {
				$('#taluk_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	$('#district_id').on('change',function(){
		fetchDistrictWiseTalukForOption();
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
			$('#hobli_id').append(`<option value="" selected disabled label="--Select Hobli--">Select Hobli</option>`);
			Object.entries(data).forEach(function(value) {
				$('#hobli_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	$('#taluk_id').on('change',function(){
		fetchTalukWiseHobliForOption();
	});
	
	
	//for getting village list for option
	let fetchHobliWiseVillageListMethod = (hobli_id) => {
		return fetch(`${ctx}/Region/getHobliWiseVillageListOption/${hobli_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		});
	}
	
	let getHobliWieVillageListForOption = () => {
		let hobli_id = $('#hobli_id').val();
		if(hobli_id==null)
			return false;
		fetchHobliWiseVillageListMethod(hobli_id).then(response => response.json())
		.then(data => {
			$('#village_id').empty();
			$('#village_id').append(`<option value="" selected disabled label="--Select Village--">Select</option>`);
			Object.entries(data).forEach(function(value) {
				$('#village_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	// $('#hobli_id').on('change',function(){
	// 	getHobliWieVillageListForOption();
	// });
	
	$('#sales-document-form').validate({
		debug:false,
		rules:{
			plant_id:"required",
			invoice_no:"required",
			plant_id:"required",
			invoice_date:"required",
			invoice_time:"required",
			customer_id:"required",
			site_id:"required",
			vehicle_no:"required"
		},
		messages:{
			plant_id:"Select Plant",
			invoice_no:"Enter Invoice No",
			invoice_date:"Select Invoice Date",
			invoice_time:"Select Invoice Item",
			customer_id:"Select Customer",
			site_id:"Select Site",
			vehicle_no:"Select Vehicle No"
		},
		submitHandler:function(form){
			if(submited==false){
				submited=true;
				form.submit();
			}
		}
	});
	
	
	
	
	
});