$(document).ready(function(){
	
	//reset the state form
	$('#resetButton').on('click',function(){
		$('#submitButton').text('Add State');
	});
	
	
	
	let getAllStateList = () => {
		fetch(`${ctx}/Region/stateList`,{
			method:'GET',
			headers :{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
		  .then(data => {
				$('#state-table tbody').empty();
				data.forEach(function(value,index){
					$('#state-table tbody').append(`
						<tr>
							<td>${value.state_id}</td>
							<td>${value.state_name}</td>
							<td>${value.region_name}</td>
							<td>${value.state_code}</td>
							<td>
								<a id="${value.state_id}" class="edit-state">
									<span class="text-success"><i class="icon fa-edit"></i></span>
								</a>
								&nbsp;&nbsp;&nbsp;
								<a id="${value.state_id}" name="${value.state_name}" class="delete-state">
									<span class="text-danger"><i class="fa fa-trash"></i></span>
								</a>
							</td>
						</tr>
					`)
				});
		});
	}
	
	
	getAllStateList();
	
	
	//geting single state data for editing purpose
	$(document).on('click','#state-table .edit-state',function(){
		let state_id = $(this).attr('id');
		fetch(`${ctx}/Region/getSingleState/${state_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
		  .then(data => {
			$('#state-form input,#state-form select').each(function(){
				let inputName = $(this).attr('name');
				if(inputName!='_csrf'){
					$('#'+inputName).val(data[inputName]);
				}
			});
			$('#submitButton').text('Update Sate');
		});
	});
	
	
	
	//for delete option
	$(document).on('click','#state-table .delete-state',function(){
		let state_id = $(this).attr('id');
		let state_name = $(this).attr('name');
		swal(
				{title:`Are you sure want to delete state ${state_name}?`,
				text:`You can\'t recover this state ${state_name}!`,
				type:"warning",
				showCancelButton:!0,
				confirmButtonClass:"btn-warning",
				confirmButtonText:"Yes, delete it!",
				closeOnConfirm:!1
				},
				function(){
					fetch(`${ctx}/Region/deleteState/${state_id}`,{
						method:'delete',
						headers:{
							'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
						}
					}).then(response => response.text())
					  .then(data => {
						if(data>0){
							 swal("Deleted!","Deleted Successfully","warning");
							 getAllStateList();
						}else{
							 swal("Failed!","Failed to delete","error");
						}
					}).catch(data => {
						swal("Failed!","State already in use!!","error");
					});
				}
			);
	});
	
	
	$('#state-form').validate({
		debug:false,
		rules:{
			state_name:"required",
			region_name:"required"
		},
		messages:{
			state_name:"Enter State Name",
			region_name:"Enter region name"
		},
		submitHandler:function(form){
			let state_id = $('#state_id').val();
			let myForm = document.getElementById('state-form');
			let formData = new FormData(myForm);
			if(state_id==0 || state_id==''){
				fetch(`${ctx}/Region/addState`,{
					method:'POST',
					headers:{
						'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
					},
					body:formData
				}).then(response => response.text())
				  .then(data => {
					if(data>0){
						toastr.success('Success!', 'State added successfully');
						getAllStateList();
						document.getElementById("state-form").reset();
					}
				});
			}else{
				fetch(`${ctx}/Region/updateState`,{
					method:'put',
					headers:{
						'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
					},
					body:formData
				}).then(response => response.text())
				  .then(data => {
					if(data>0){
						toastr.success('State updated successfully', 'Success!');
						document.getElementById("state-form").reset();
						$('#submitButton').text('Add State');
						getAllStateList();
					}
				});
			}
		}
	});
	
	
	
	
	
	
});