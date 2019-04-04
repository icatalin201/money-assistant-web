$(document).ready(function() {
	console.log("ready");
	$(document).click(function() {
		$(".edit").html('');
	});
	$(".edit").click(function(e){
		e.stopPropagation();
	});
	$("#reset").click(function(event) {
		event.preventDefault();
		$(".error").remove();
		$("#new-form").trigger('reset');
	});
});

function submitAccountForm() {
	$("#submit").click(function(event) {
		event.preventDefault();
		$(".error").remove()
		$(".spinner-grow").removeClass("inactive");
		var form = $('#new-form').serialize();	
		console.log(form)
		$.ajax({
			url: '/api/account/create/form',
			type: 'POST',
			data: form,
			success: function(data) {
				setTimeout(function() {
					if (data.status) {
						location.reload();
					} else {
						$.each(data.errors, function(key, value){
			  	            $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
			            });
					}
					$(".spinner-grow").addClass("inactive");
	            }, 1000);
			},
		});
	});
}

function submitTransactionForm() {
	$("#submit").click(function(event) {
		event.preventDefault();
		$(".error").remove()
		$(".spinner-grow").removeClass("inactive");
		var details = $.trim($("#details").val());
		if (details.length === 0) {
			var category = $("#category option:selected").text();
			$("#details").val(category)
		}
		var form = $('#new-form').serialize();	
		console.log(form)
		$.ajax({
			url: '/api/transaction/create/form',
			type: 'POST',
			data: form,
			success: function(data) {
				setTimeout(function() {
					if (data.status) {
						location.reload();
					} else {
						$.each(data.errors, function(key, value){
			  	            $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
			            });
					}
					$(".spinner-grow").addClass("inactive");
	            }, 1000);
			},
		});
	});
}

function submitCategoryForm() {
	$("#submit").click(function(event) {
		event.preventDefault();
		$(".error").remove()
		$(".spinner-grow").removeClass("inactive");
		var form = $('#new-form').serialize();	
		console.log(form)
		$.ajax({
			url: '/api/category/create/form',
			type: 'POST',
			data: form,
			success: function(data) {
				setTimeout(function() {
					if (data.status) {
						location.reload();
					} else {
						$.each(data.errors, function(key, value){
			  	            $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
			            });
					}
					$(".spinner-grow").addClass("inactive");
	            }, 1000);
			},
		});
	});
}

function setCategoryItemEvent() {
	$(".iterable-item").click(function(event) {
		event.preventDefault();
		var id = $(this).data("id");
		console.log(id);
		$.ajax({
			url: '/categories/' + id,
			type: 'GET',
			success: function(data) {
				$(".edit").html(data)
			},
		});
	});
}

function setAccountItemEvent() {
	$(".iterable-item").click(function(event) {
		event.preventDefault();
		var id = $(this).data("id");
		console.log(id);
		$.ajax({
			url: '/accounts/' + id,
			type: 'GET',
			success: function(data) {
				$(".edit").html(data)
			},
		});
	});
}

function setTransactionItemEvent() {
	$(".iterable-item").click(function(event) {
		event.preventDefault();
		var id = $(this).data("id");
		console.log(id);
		$.ajax({
			url: '/transactions/' + id,
			type: 'GET',
			success: function(data) {
				$(".edit").html(data)
			},
		});
	});
}

function setTransactionIntervalDay() {
	$("#today-t").click(function(e) {
		var today = moment().format("YYYY-MM-DD");
		$.ajax({
			url: '/api/transaction/interval',
			type: 'GET',
			data: {
				date1: today,
				date2: today
			},
			success: function(data) {
				$(".iterable").empty()
				$.each(data, function(key, value){
					populateList(value);
				});
			},
		});
	});
	
}

function setTransactionIntervalWeek() {
	$("#week-t").click(function(e) {
		const today = moment();
		const from_date = today.startOf('week').format("YYYY-MM-DD");
		const to_date = today.endOf('week').format("YYYY-MM-DD");
		$.ajax({
			url: '/api/transaction/interval',
			type: 'GET',
			data: {
				date1: from_date,
				date2: to_date
			},
			success: function(data) {
				$(".iterable").empty()
				$.each(data, function(key, value){
					populateList(value);
				});
			},
		});
	});
}

function setTransactionIntervalMonth() {
	$("#month-t").click(function(e) {
		const today = moment();
		const from_date = today.startOf('month').format("YYYY-MM-DD");
		const to_date = today.endOf('month').format("YYYY-MM-DD");
		$.ajax({
			url: '/api/transaction/interval',
			type: 'GET',
			data: {
				date1: from_date,
				date2: to_date
			},
			success: function(data) {
				$(".iterable").empty()
				$.each(data, function(key, value){
					populateList(value);
				});
			},
		});
	});
}

function setTransactionIntervalAll() {
	$("#all-t").click(function(e) {
		$.ajax({
			url: '/api/transaction/all',
			type: 'GET',
			success: function(data) {
				$(".iterable").empty()
				$.each(data, function(key, value){
					populateList(value);
				});
			},
		});
	});
}

function populateList(item) {
	var cls = item.type == 'income' ? "item-positive" : "item-negative";
	$(".iterable").append(
		"<div class='iterable-item' data-id="+ item.id +">" +
	  		"<div class='iterable-item-start'>"+
	  			"<span class='iterable-item-date'>"+ item.date +"</span> "+
	  			"<span class='iterable-item-name'>"+ item.details +"</span> "+
	  		"</div>"+
  			"<div class='iterable-item-end'>"+
  				"<span class='"+ cls +"'>"+ item.amount +"</span>"+
  				"<span class='"+ cls +"'> RON</span>"+
  			"</div>" +
  		"</div>"
		);
}

function loadAccounts() {
	$.ajax({
	  type: "GET",
	  url: "/api/account/all",
	  success: function(result) {
		  $("#account").html('');
		  $.each(result, function(key, value) {
            $('#account').append('<option value="' + value.id + '">' + value.name + '</option>');
        });
	  }
	});
}
function loadCategories(type) {
	$.ajax({
	  type: "GET",
	  url: "/api/category/all/" + type,
	  success: function(result) {
		  $("#category").html('');
		  $.each(result, function(key, value) {
            $('#category').append('<option value="' + value.id + '">' + value.name + '</option>');
        });
	  }
	});
}