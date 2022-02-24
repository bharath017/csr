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
	
		
	$(document).on('click','#image-table #addImageBtn',function(){
		let sl_no=$('#image-table tbody tr').length;
		console.log(sl_no);
		let html = `
			<tr>
				<td>${sl_no+1}</td>
				<td>
					<div class="row">
						<div class="col-sm-8">
							<input type="file" class="form-control imageFile" name="imageFile"/>
						</div>
						<div class="col-sm-4">
							<button type="button" class="btn btn-info uploadBtn" >Upload</button>
						</div>
					</div>
				</td>
				<td>
					<input type="hidden" name="school_image[${sl_no}].image_path" class="image_path"/>
					<div class="viewImage">
						
					<div>
				</td>
				<td>
					<a class="text-danger deleteImageBtn">
						<span class="text-danger">
							<i class="fa fa-trash"></i>
						</span>
					</a>
				</td>
			</tr>
		`
		
		$('#image-table tbody').append(html);
	});
	
	
	$(document).on('click','#image-table .uploadBtn',function(){
		let imageFile = $(this).closest('tr').find('.imageFile').val();
		let that = $(this);
		if(imageFile){
  		  	let type='logo';
  			var data = new FormData();
  			let file  = (that.closest('tr').find('.imageFile')[0].files)[0];
  			 console.log(file);
		 	 let fileName = file.name;
	   		 let ext = fileName.substring(fileName.lastIndexOf('.')+1);
			 ext = ext.toLowerCase().trim();
			 if(ext=='jpg' || ext=='png' ||  ext=='jpeg'){
				 data.append('file-'+0, file);	
			 }else{
				alert('Only Image files are allowed');
				return false;
			 }
  	   		 
  	   		 $.ajax({
  	   			    url: `${ctx}/School/upload`,
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
  				                that.closest('tr').find('.viewImage').html(Math.ceil(percentComplete)+"%");
  				            }
  				       }, false);
  				       return xhr;
  				    },
  				    success:function(res){
	 					that.closest('tr').find('.viewImage').html(`<img src="" class="viewPriview" width="50" height="50" alt="Not found"/>`)
  				    	that.closest('tr').find('.viewPriview').attr('src',url+'documents/schoolImage/'+res);
  				    	that.closest('tr').find('.image_path').val(res);
  				   }
  	   		 });
  		}else{
  			alert('Please choose file');
  		} 
	});
	
	
	let adjustAllcolumn = () => {
		let countIndex = 0;
		$('#image-table tbody tr').each(function(){
			$(this).closest('tr').find('.image_path').attr('name','school_image['+countIndex+'].image_path');
			$(this).closest('tr').find('td:eq(0)').text(countIndex+1);
			countIndex++;
		});
	}
	
	$(document).on('click','#image-table .deleteImageBtn',function(){
		$(this).closest('tr').remove();
		adjustAllcolumn();
	});
	
	
	let submitted = false;
	$('#school-form').validate({
		debug:false,
		rules:{
			school_name:"required",
			hm_name:"required",
			hm_phone:"required",
			school_strength:"required",
			no_of_teacher:"required",
			cluster_id:"required"
		},
		messages:{
			school_name:"Enter School Name",
			hm_name:"Enter HM Name",
			hm_phone:"Enter HM Phone",
			school_strength:"Enter School Strength",
			no_of_teacher:"Enter No of teacher",
			cluster_id:"Select Cluster"
		},
		submitHandler:function(form){
			let imageCount = 0;
			$('#image-table tbody tr').each(function(){
				let trData = $(this).find('.image_path').val();
				if(trData!='' && trData!=undefined){
					imageCount++;
				}
			});
			
			if(imageCount==0){
				alert('Please upload image file properly');
			}else{
				if(!submitted){
					form.submit();
					submitted = true;
				}
			}
		}
	});
	
	
	
});