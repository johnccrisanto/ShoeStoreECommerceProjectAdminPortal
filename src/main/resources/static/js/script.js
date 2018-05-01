/**
 * 
 */

$(document).ready(function() {
	$('.delete-shoe').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Are you sure to remove this shoe? It cannot be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
	var shoeIdList=[];
	
//	$('.checkboxShoe').click(function() {
//		var id=$(this).attr('id');
//		if(this.checked){
//			shoeIdList.push(id);
//		} else {
//			shoeIdList.splice(shoeIdList.indexOf(id), 1);
//		}
//	})
	
	$('#deleteSelected').click(function() {
		var idList = $('.checkBoxShoe');
		var shoeIdList = [];
		for(var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				shoeIdList.push(idList[i]['id'])
			}
			}

		
		

		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Are you sure to remove all selected shoes? It cannot be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(shoeIdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res); 
							location.reload()},
						error: function(res){
							console.log(res); 
							location.reload();}
					});
				}
			}
		});
	});
	
	$("#selectAllShoes").click(function() {
		if($(this).prop("checked")==true) {
			$(".checkBoxShoe").prop("checked", true);
		} else if ($(this).prop("checked")==false) {
			$(".checkBoxShoe").prop("checked", false);
		}
	})
});