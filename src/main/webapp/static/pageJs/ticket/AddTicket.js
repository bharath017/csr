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
			$('#district_id').append(`<option value="" selected disabled label="--Select Dist--">Select</option>`);
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
			$('#taluk_id').append(`<option value="" selected disabled label="--Select Taluk--">Select</option>`);
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
			$('#hobli_id').append(`<option value="" selected disabled label="--Select Hobli--">Select</option>`);
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
	
	$('#hobli_id').on('change',function(){
		getHobliWieVillageListForOption();
	});
	
	
	//for getting village list for option
	let fetchVillageWiseSchoolForMethod = (village_id) => {
		return fetch(`${ctx}/Ticket/getSchoolForOption/${village_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		});
	}
	
	let getVillageWiseSchoolListForOption = () => {
		let village_id = $('#village_id').val();
		if(village_id==null)
			return false;
		fetchVillageWiseSchoolForMethod(village_id).then(response => response.json())
		.then(data => {
			$('#school_id').empty();
			$('#school_id').append(`<option value="" selected disabled label="--Select School--">Select</option>`);
			Object.entries(data).forEach(function(value) {
				$('#school_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	$('#village_id').on('change',function(){
		getVillageWiseSchoolListForOption();
	});
	
	
	//get ticket category details
	
	
	
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
			$('#category_id').append(`<option value="" selected disabled label="--Select Category--">Select</option>`);
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
			$('#sub_category_id').append(`<option value="" selected disabled label="--Select Sub Category--">Select</option>`);
			Object.entries(data).forEach(function(value) {
				$('#sub_category_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	$('#category_id').on('change',function(){
		getSubCategoryForOption();
	});
	
	
	function arrayBufferToHex(buffer) {
	  const view  = new Uint8Array( buffer );
	  return Array.prototype.map.call(view, function(byte) {
	    return ('0' + (byte & 0xFF).toString(16)).slice(-2);
	  }).join('');
	}
	
	document.getElementById("files").onchange = function () {  
	  for (let i = 0; i < this.files.length; i++) {
	    const file = this.files[i];
	    const reader = new FileReader();
	    reader.readAsArrayBuffer(file);
	    reader.onloadend = function (e) { 
		  console.log(e.target.result);    
	      const splits = file.name.split(".");
	      const fileJSON = { 
	        "Filename": splits[0], 
	        "Extension": splits[1], 
	        "DateCreated": new Date(), 
	        "Data": arrayBufferToHex(this.result), 
	        "Sguid": "XYZ" };
	    }
	  }
	}
	
	
	
	$(document).on('click','#uploadAllFiles',function(){
		let imageFile = $("#files").val();
		let that = $(this);
		if(imageFile){
  		  	let type='logo';
  			var data = new FormData();
			jQuery.each(jQuery('#files')[0].files, function(i, file) {
		    	     data.append('files', file);
		   	});				 	
  	   		 $.ajax({
  	   			    url: `${ctx}/Ticket/upload`,
  				    data: data,
  				    cache: false,
  				    contentType: false,
  				    processData: false,
  				    headers:{
						"X-CSRF-TOKEN":$('input[name="_csrf"]').val()
					},
  				    type: 'POST',
  				    target:'#targetLayer',
  				    beforeSubmit:function(){
  				    	$('.progress-bar').width('0%');
  				    },xhr: function() {
  				        var xhr = new window.XMLHttpRequest();
  				        xhr.upload.addEventListener("progress", function(evt) {
  				            if (evt.lengthComputable) {
  				                var percentComplete = (evt.loaded / evt.total) * 100;
  				                $('#viewImage').html(Math.ceil(percentComplete)+"%");
  				            }
  				       }, false);
  				       return xhr;
  				    },
  				    success:function(res){
						$('#viewImage').empty();
						res.forEach((value,index) => {
							$('#viewImage').append(`
								<a href="${url}documents/schoolImage/${value}" target="_blank">
									<img src="${url}documents/schoolImage/${value}" width="100" height="100" alt="Image Not found"/>
								</a>
							`);
						});				
	 					$('#images').val(JSON.stringify(res));
  				   }
  	   		 });
  		}else{
  			alert('Please choose file');
  		} 
	});
		
		
		
	//validate ticke tform
	let submited = false;
	$('#ticket-form').validate({
		debug:false,
		rules:{
			state_id:"required",
			district_id:"required",
			taluk_id:"required",
			hobli_id:"required",
			village_id:"required",
			school_id:"required",
			description:"required",
			category_id:"required",
			sub_category_id:"required"
		},
		messages:{
			state_id:"Select State",
			district_id:"Select District",
			taluk_id:"Select Taluk",
			hobli_id:"Select Hobli",
			village_id:"Select Village",
			school_id:"Select School",
			descriptoin:"Enter Description",
			category_id:"Select Category",
			sub_category_id:"Select Sub Category"
		},
		submitHandler:function(form){
			if(!submited){
				submited = true;
				form.submit();
			}
		}
	});
	

});