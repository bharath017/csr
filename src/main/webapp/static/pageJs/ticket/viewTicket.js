$(document).ready(function(){
	$(document).on('click','#acceptTicket',function(){
		let ticket_id = $(this).attr('name');
		swal(
				{title:`Are you sure want to accept this ticket ?`,
				text:`Accept Ticket ID ${ticket_id}`,
				type:"success",
				showCancelButton:!0,
				confirmButtonClass:"btn-success",
				confirmButtonText:"Yes, Accept It!",
				closeOnConfirm:!1
				},
				function(){
					fetch(`${ctx}/Ticket/changeTicketStatus/${ticket_id}/progress`,{
						method:'PUT',
						headers:{
							'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
						}
					}).then(response => response.text())
					  .then(data => {
						if(data>0){
							 swal("Accepted!","Ticket has been accepted","success");
							 checkIFAccepted('progress');
						}else{
							 swal("Failed!","Failed to accept","error");
						}
					});
				}
			);
	});
	
	
	$(document).on('click','#rejectTicket',function(){
		let ticket_id = $(this).attr('name');
		swal(
				{title:`Are you sure want to reject this ticket ?`,
				text:`Accept Ticket ID ${ticket_id}`,
				type:"warning",
				showCancelButton:!0,
				confirmButtonClass:"btn-warning",
				confirmButtonText:"Yes, Reject It!",
				closeOnConfirm:!1
				},
				function(){
					fetch(`${ctx}/Ticket/changeTicketStatus/${ticket_id}/rejected`,{
						method:'PUT',
						headers:{
							'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
						}
					}).then(response => response.text())
					  .then(data => {
						if(data>0){
							 swal("Accepted!","Ticket has been Rejected","warning");
							 checkIFAccepted('rejected');
						}else{
							 swal("Failed!","Failed to accept","error");
						}
					});
				}
			);
	});
	
	
	let checkIFAccepted = (status_value) => {
		$('#ticketSatusTR').empty();
		if(status_value=='progress')
			$('#ticketSatusTR').html(`
				<h6 class="text-success">Ticket in progress</h6>
			`);
		else if(status_value=='rejected'){
			$('#ticketSatusTR').html(`
				<h6 class="text-danger">Ticket Rejected</h6>
			`);
		}else if(status_value=='new'){
			let ticket_id=$('input[name="ticket_id"]').val();
			$('#ticketSatusTR').html(`<button class="btn btn-success" id="acceptTicket" name="${ticket_id}">Accept</button>
		                			<button class="btn btn-danger" id="rejectTicket" name="${ticket_id}">Reject</button>`);
		}else if(status_value=='completed'){
			$('#ticketSatusTR').html(`
				<h3 class="text-info">Completed</h3>
			`);
		}
	}
	
	let ticket_status=$('#ticketStatus').val();
	checkIFAccepted(ticket_status);
	
	
	
	let viewImages = () => {
		let ticket_id = $('input[name="ticket_id"]').val();
		fetch(`${ctx}/Ticket/getTicketImages/${ticket_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
		  .then(data => {
			data.forEach((src) => {
				$('#exampleTabsLineLeftOne').append(`
					<a href="${ticketImagePath}${src}" target="_blank"><img src="${ticketImagePath}${src}" width="60%" height="40%"/></a>
				`)
			});
		});
	}
	
	viewImages();
	
	$('#file-upload').on('change',function(){
		let imageFile = $("#file-upload").val();
		let ticket_id = $('input[name="ticket_id"]').val();
		if(imageFile){
  		  	let type='logo';
  			var data = new FormData();
			jQuery.each(jQuery('#file-upload')[0].files, function(i, file) {
		    	     data.append('files', file);
		   	});				 	
  	   		 $.ajax({
  	   			    url: `${ctx}/Ticket/uploadEstimatedDocument/${ticket_id}`,
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
  				                $('#viewPercent').html(Math.ceil(percentComplete)+"%");
  				            }
  				       }, false);
  				       return xhr;
  				    },
  				    success:function(res){
						viewAllDocuments(res);
						$('#viewPercent').empty();
  				   }
  	   		 });
  		}else{
  			alert('Please choose file');
  		} 
	});
	
	let getAllEstimatedDocs  = () => {
		let ticket_id = $('input[name="ticket_id"]').val();
		fetch(`${ctx}/Ticket/getAllEstimatedDocs/${ticket_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.text())
		  .then(data => {
			viewAllDocuments(data);
		});
	}
	
	getAllEstimatedDocs();
	
	let viewAllDocuments = (docs) => {
		let docsJSON = JSON.parse(docs);
		$('#estimated_document-table tbody').empty();
		docsJSON.forEach((value,index,docs) => {
			$('#estimated_document-table tbody').append(`
				<tr>
					<td>${(index+1)}</td>
					<td>${value}</td>
					<td>
						<a href="${ticketImagePath}${value}" target="_blank">
							<span class="text-success">
								<i class="fa fa-download"></i>
							</span>
						</a>
						&nbsp;&nbsp;
						<a  data="${docs}" class="delete-estimated-document" id="${value}">
							<span class="text-danger">
								<i class="fa fa-trash"></i>
							</span>
						</a>
					</td>
				</tr>
			`)
		});
	}
	
	
	$(document).on('click','#estimated_document-table .delete-estimated-document',function(){
		let document_name = $(this).attr('id');
		let data = $(this).attr('data');
		let ticket_id = $('input[name="ticket_id"]').val();
		swal(
				{title:`Remove Estimated Document`,
				text:`Remove ${document_name}`,
				type:"warning",
				showCancelButton:!0,
				confirmButtonClass:"btn-warning",
				confirmButtonText:"Remove",
				closeOnConfirm:1
				},
				function(){
					let splitData = data.split(',');
					splitData.splice(splitData.indexOf(document_name),1);
					let jsonNewData = JSON.stringify(splitData);
					console.log(jsonNewData);
					fetch(`${ctx}/Ticket/removeEstimatedDocs/${ticket_id}`,{
						method:'post',
						headers:{
							'X-CSRF-TOKEN':$('input[name="_csrf"]').val(),
							'Content-Type': 'application/json'
						},
						body:jsonNewData
					}).then(response => response.text())
					  .then(data => {
						viewAllDocuments(data);
					});
				}
		);
	});
	
	
	$('#enterEstimateAmountBtn').on('click',function(){
		let ticket_id = $('input[name="ticket_id"]').val();
		let estimated_amount = $('input[name="estimated_amount"]').val();
		let estimated_status = $('input[name="estimated_status"]').val();
		if(estimated_amount=='' || isNaN(Number(estimated_amount))){
			alert('Enter Estimated Amount');
			return false;
		}
		fetch(`${ctx}/Ticket/updateEstimatedAmount/${ticket_id}/${estimated_amount}?estimated_status=${estimated_status}`,{
			method:'put',
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
		  .then(data => {
			if(data>0){
				viewApproveBtn();
				toastr.success('Success!', 'Estimated amount added waiting for confirmation');
			}else if(data==-1){
				toastr.error('Error', 'Already estimated amount accepted');
			}
		});
	});
	
	
	$('#aproveEstimateAmountBtn').on('click',function(){
		let ticket_id = $('input[name="ticket_id"]').val();
		fetch(`${ctx}/Ticket/approveEstimatedAmount/${ticket_id}`,{
			method:'put',
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
		  .then(data => {
			if(data>0){
				toastr.success('Success!', 'Estimated amount Approved Successfully');
			}else{
				toastr.error('Failed!', 'Failed!!');
			}
		});
	});
	
	
	//view approve btn
	let viewApproveBtn = () => {
		let estimated_amount = $('input[name="estimated_amount"]').val();
		let estimated_status = $('input[name="estimated_status"]').val();
		console.log(estimated_status);
		if(estimated_amount!='' && (!isNaN(Number(estimated_amount)) && Number(estimated_amount)>0) && estimated_status=='pending'){
			$('#aproveEstimateAmountBtn').show();
		}else{
			$('#aproveEstimateAmountBtn').hide();
		}
	}
	
	viewApproveBtn();
	
	
	$('#donor-form').validate({
		debug:false,
		rules:{
			donor_id:'required',
			amount:'required'
		},
		messages:{
			donor_id:'Select Donor',
			amount:'Enter Amount'
		},
		submitHandler:function(form){
			let myForm = document.getElementById('donor-form');
			let formData = new FormData(myForm);
			fetch(`${ctx}/Ticket/addTicketDonor`,{
				method:'post',
				headers:{
					'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
				},
				body:formData
			}).then(response => response.json())
			  .then(data => {
				if(data>0){
					toastr.success('Success!', 'Donor Assigned Successfully');
					getDonorList();
				}else{
					toastr.error('Failed!', 'Failed to assign');
				}
			});	
		}
	});
	
	
	let getDonorList = () => {
		let ticket_id = $('input[name="ticket_id"]').val();
		fetch(`${ctx}/Ticket/getTicketDonorList/${ticket_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
		  .then(data => {
			$('#donor-table tbody').empty();
			data.forEach((value,index) => {
				$('#donor-table tbody').append(`
					<tr>
						<td>${(index+1)}</td>
						<td>${value.donor_name}</td>
						<td>${value.amount}</td>
						<td class="text-center">
							<a id="${value.ticket_donor_id}" class="delete-ticket-donor">
								<span class="text-danger">
									<i class="fa fa-trash"></i>
								</span>
							</a>
						</td>
					</tr>
				`)
			});
		});
	}
	
	getDonorList();
	
	
	
	$(document).on('click','#donor-table .delete-ticket-donor',function(){
		let ticket_donor_id = $(this).attr('id');
		swal(
				{title:`Remove Donor`,
				text:`Remove `,
				type:"warning",
				showCancelButton:!0,
				confirmButtonClass:"btn-warning",
				confirmButtonText:"Remove",
				closeOnConfirm:1
				},
				function(){
					fetch(`${ctx}/Ticket/removeTicketDonor/${ticket_donor_id}`,{
						method:'delete',
						headers:{
							'X-CSRF-TOKEN':$('input[name="_csrf"]').val(),
							'Content-Type': 'application/json'
						}
					}).then(response => response.text())
					  .then(data => {
						if(data>0){
							toastr.warning('Warning!', 'Donor Deleted');
							getDonorList();
						}else{
							toastr.error('Error!', 'Failed to delete');
						}
					});
				}
		);
	});
	
	
	//upload completion document
	
	$('#completion-image-file').on('change',function(){
		let imageFile = $("#completion-image-file").val();
		let ticket_id = $('input[name="ticket_id"]').val();
		if(imageFile){
  		  	let type='logo';
  			var data = new FormData();
			jQuery.each(jQuery('#completion-image-file')[0].files, function(i, file) {
		    	     data.append('files', file);
		   	});				 	
  	   		 $.ajax({
  	   			    url: `${ctx}/Ticket/uploadCompletionImage/${ticket_id}`,
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
  				                $('#viewCompletionPercent').html(Math.ceil(percentComplete)+"%");
  				            }
  				       }, false);
  				       return xhr;
  				    },
  				    success:function(res){
						viewCompletionDocuments();
						$('#viewCompletionPercent').empty();
  				   }
  	   		 });
  		}else{
  			alert('Please choose file');
  		} 
	});
	
	
	let viewCompletionDocuments = () => {
		let ticket_id = $('input[name="ticket_id"]').val();
		fetch(`${ctx}/Ticket/getCompletonDocuments/${ticket_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
		  .then(data => {
			data.forEach((src) => {
				$('#viewCompletionPercent').append(`
					<a href="${ticketImagePath}${src}" target="_blank"><img src="${ticketImagePath}${src}" width="40%" height="40%"/></a>
				`)
			});
		});
	}
	
	viewCompletionDocuments();
	
	
	
	$('#completeBtn').on('click',function(){
		let completion_description = $('textarea[name="completion_description"]').val();
		let ticket_id = $('input[name="ticket_id"]').val();
		fetch(`${ctx}/Ticket/updateCompletionDetails/${ticket_id}/${completion_description}`,{
			method:'put',
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
		  .then(data => {
			 location.reload()
		});
	});
	
});