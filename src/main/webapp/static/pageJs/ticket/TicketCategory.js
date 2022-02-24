$(document).ready(function(){
	
	let getTicketCategoryListForSearchOption = () => {
		fetch(`${ctx}/Ticket/getTicketCategoryForOption`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}
		}).then(response => response.json())
		  .then(data => {
			$('#category_id_search').empty();
			$('#category_id').empty();
			$('#category_id_search').append(`<option value="0">All Category</option>`);
			$('#category_id').append(`<option value="0" disabled selected label="--Select Category--"></option>`);
			Object.entries(data).forEach((value) => {
				$('#category_id_search').append(`<option value="${value[0]}">${value[1]}</option>`);
				$('#category_id').append(`<option value="${value[0]}">${value[1]}</option>`);
			});
		});
	}
	
	getTicketCategoryListForSearchOption();
	
	
	let getAllTicketCategoryList = () => {
		let search = $('#categorySearch').val();
		fetch(`${ctx}/Ticket/getAllCategoryList?search=${search}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}			
		}).then(response => response.json())
		  .then(data => {
			$('#ticket-category-table tbody').empty();
			data.forEach((value,index) => {
				$('#ticket-category-table tbody').append(`
					<tr>
						<td>${value["category_id"]}</td>
						<td>
							<a style="cursor:pointer;" href="#">${value["category_name"]}</a>
						</td>
						<td>
							
						</td>
					</tr>
				`);
			});
		});
	}
	
	getAllTicketCategoryList();
	
	$('#categorySearch').unbind().on('keyup',function(){
		getAllTicketCategoryList();
	});
	
	
	
	$('#ticket-category-form').validate({
		debug:false,
		rules:{
			category_name:'required'
		},
		messages:{
			category_name:'Enter Category Name'
		}
	});
	
	let submited = false;
	$('#saveTicketCategoryBtn').unbind().on('click',function(){
		if($('#ticket-category-form').valid()){
			if(!submited){
				submited = true;
				let myForm = document.getElementById('ticket-category-form');
				let formData = new FormData(myForm);
				fetch(`${ctx}/Ticket/addTicketCategory`,{
					method:'post',
					headers:{
						'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
					},
					body:formData
				}).then(response => response.json())
				  .then(data => {
					$('#exampleNiftyFadeScale').modal('hide');
					if(data)
						submited = false;
					if(data>0){
						toastr.success('Success!', 'Category added successfully');
					}
					getAllTicketCategoryList();
					getTicketCategoryListForSearchOption();
				});
			}
		}
	});
	
	
	//for sub category
	
	let getAllTicketSubCategoryList = () => {
		let search = $('#subCategorySearch').val();
		let category_id=$('#category_id_search').val();
		fetch(`${ctx}/Ticket/getAllSubCategoryList?search=${search}&category_id=${category_id}`,{
			headers:{
				'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
			}			
		}).then(response => response.json())
		  .then(data => {
			$('#ticket-sub-table tbody').empty();
			data.forEach((value) => {
				$('#ticket-sub-table tbody').append(`
					<tr>
						<td>${value["category_id"]}</td>
						<td>
							<a style="cursor:pointer;" href="#">${value["sub_category_name"]}</a>
						</td>
						<td>
							${value["category_name"]}
						</td>
						<td></td>
					</tr>
				`);
			});
		});
	}
	
	
	getAllTicketSubCategoryList();
	
	
	$('#ticket-sub-category-form').validate({
		debug:false,
		rules:{
			sub_category_name:'required',
			category_id:'required'
		},
		messages:{
			sub_category_name:'Enter Category Name',
			category_id:'Select Category'
		}
	});
	
	
	let subCategorySubmited = false;
	$('#saveTicketSubCategoryBtn').unbind().on('click',function(){
		if($('#ticket-sub-category-form').valid()){
			if(!subCategorySubmited){
				subCategorySubmited = true;
				let myForm = document.getElementById('ticket-sub-category-form');
				let formData = new FormData(myForm);
				fetch(`${ctx}/Ticket/addTicketSubCategory`,{
					method:'post',
					headers:{
						'X-CSRF-TOKEN':$('input[name="_csrf"]').val()
					},
					body:formData
				}).then(response => response.json())
				  .then(data => {
					$('#addSubCategoryModal').modal('hide');
					if(data)
						subCategorySubmited = false;
					if(data>0){
						toastr.success('Success!', 'Sub Category added successfully');
					}
					getAllTicketSubCategoryList();
				});
			}
		}
	});
	
	
});